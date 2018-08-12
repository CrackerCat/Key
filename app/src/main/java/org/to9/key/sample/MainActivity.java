package org.to9.key.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.to9.key.sdk.Key;
import org.to9.key.sdk.bean.BeanBindMachine;
import org.to9.key.sdk.callback.IBindMachine;

public class MainActivity extends AppCompatActivity implements IBindMachine {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Key.getInstance().init("f434b3bbdd4b297d");
        Key.getInstance().setMachineIdentification("asdfasdfasdfasdf");
        Key.getInstance().setLastCode("d6efbebcfe97d5e6");

        Key.getInstance().bindMachine(this);
    }

    @Override
    public void onBindMachineFailure(String message) {
        Log.d(TAG, "onBindMachineFailure() called with: message = [" + message + "]");
    }

    @Override
    public void onBindMachineSuccess(BeanBindMachine beanBindMachine) {
        Log.d(TAG, "onBindMachineSuccess() called with: beanBindMachine = [" + beanBindMachine + "]");
    }
}
