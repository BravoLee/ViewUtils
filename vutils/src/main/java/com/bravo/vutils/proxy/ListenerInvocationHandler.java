package com.bravo.vutils.proxy;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by bravo.lee on 2017/10/24.
 */

public class ListenerInvocationHandler implements InvocationHandler {

    private Context context;
    private Map<String, Method> methodMap;

    public ListenerInvocationHandler(Context context, Map<String, Method> methodMap) {
        this.context = context;
        this.methodMap = methodMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Method metf = methodMap.get(methodName);
        if (metf != null) {
            return metf.invoke(context, args);
        } else {
            return method.invoke(proxy, args);
        }
    }
}
