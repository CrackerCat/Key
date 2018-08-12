package org.to9.key.sdk;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.to9.key.sdk.bean.BeanBindMachine;
import org.to9.key.sdk.bean.BeanServerResponse;
import org.to9.key.sdk.callback.IBindMachine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 调用顺序：
 * Key.getInstance().init("");
 * Key.getInstance().setMachineIdentification("");
 * Key.getInstance().setLastCode("");
 */
@SuppressWarnings("WeakerAccess")
public class Key {
    private final static Key instance = new Key();
    private static final String TAG = "MainActivity";

    public static Key getInstance() {
        return instance;
    }

    private String appUnique = null;
    private String lastCode = null;
    private String machineIdentification = null;

    private OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * @param appUnique
     */
    public void init(String appUnique) {
        this.appUnique = appUnique;

        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(15, TimeUnit.SECONDS);
    }


    /**
     * 绑定机器
     *
     * @param iBindMachine
     */
    public void bindMachine(IBindMachine iBindMachine) {
        okHttpClient.newCall(new Request.Builder()
                .url(HttpUrl.parse(BuildConfig.SERVER_URL).newBuilder()
                        .addPathSegment("machine").addPathSegment("bind")
                        .addEncodedQueryParameter("app", getAppUnique()).addEncodedQueryParameter("code", getLastCode()).addEncodedQueryParameter("machine", getMachineIdentification()).build()).build())
                .enqueue(new BindMachineOkHttpCallback(iBindMachine));
    }



    public String getAppUnique() {
        return appUnique;
    }

    public String getLastCode() {
        return lastCode;
    }

    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }

    public String getMachineIdentification() {
        return machineIdentification;
    }

    public void setMachineIdentification(String machineIdentification) {
        this.machineIdentification = machineIdentification;
    }

    //----------------------------------------------------------------------------------------------
    private class BindMachineOkHttpCallback implements Callback {
        private IBindMachine iBindMachine;

        private BindMachineOkHttpCallback(IBindMachine iBindMachine) {
            this.iBindMachine = iBindMachine;
        }

        @Override
        public void onFailure(Request request, IOException e) {
            if (iBindMachine != null)
                iBindMachine.onBindMachineFailure(e.getMessage());
        }

        @Override
        public void onResponse(Response response){
            try {
                BeanServerResponse beanServerResponse = JSON.parseObject(response.body().string()).toJavaObject(BeanServerResponse.class);
                if (beanServerResponse.getRetCode() != 200)
                    throw new RuntimeException(beanServerResponse.getRetMessage());
                if (iBindMachine != null && beanServerResponse.getData() != null && beanServerResponse.getData() instanceof JSONObject)
                    iBindMachine.onBindMachineSuccess(((JSONObject) beanServerResponse.getData()).toJavaObject(BeanBindMachine.class));
                else
                    throw new RuntimeException("无效返回数据");
            } catch (Exception e) {
                if (iBindMachine != null)
                    iBindMachine.onBindMachineFailure(e.getMessage());
            }
        }
    }


}
