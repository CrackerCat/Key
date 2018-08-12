package org.to9.key.sdk.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class BeanServerResponse {
    @JSONField(name = "ret")
    private int retCode;

    @JSONField(name = "msg")
    private String retMessage;

    @JSONField(name = "data")
    private Object data;

    @Override
    @JSONField(serialize = false)
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
