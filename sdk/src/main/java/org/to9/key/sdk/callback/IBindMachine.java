package org.to9.key.sdk.callback;

import org.to9.key.sdk.bean.BeanBindMachine;

public interface IBindMachine {
    void onBindMachineFailure(String message);

    void onBindMachineSuccess(BeanBindMachine beanBindMachine);
}
