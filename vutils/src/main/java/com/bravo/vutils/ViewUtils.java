package com.bravo.vutils;

import android.content.Context;
import android.view.View;

import com.bravo.vutils.anno.ContentView;
import com.bravo.vutils.anno.EventBase;
import com.bravo.vutils.anno.ViewInject;
import com.bravo.vutils.proxy.ListenerInvocationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bravo.lee on 2017/10/24.
 */

public class ViewUtils {

    public static void inject(Context context) {
        injectLayout(context);
        injectView(context);
        injectEvents(context);
    }

    private static void injectLayout(Context context) {
        int layoutId = 0;
        Class<?> clazz = context.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            layoutId = contentView.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.invoke(context, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectView(Context context) {
        Class<?> aClass = context.getClass();
        //获取到MainActivity里面所有的成员变量 包含 textView
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            //得到成员变量的注解
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject != null) {
                //拿到id  R.id.text
                int valueId = viewInject.value();
                try {
                    //View view=activity.findViewById()
                    Method method = aClass.getMethod("findViewById", int.class);
                    //反射调用方法
                    View view = (View) method.invoke(context, valueId);
                    field.setAccessible(true);
                    field.set(context, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectEvents(Context context) {
        Class<?> clazz = context.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }

                String listenerSetter = eventBase.listenerSetter();
                Class<?> listenerType = eventBase.listenerType();
                String callBackMethod = eventBase.callBackMethod();

                Map<String, Method> methodMap = new HashMap<>();
                methodMap.put(callBackMethod, method);

                try {
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    int[] viewIds = (int[]) valueMethod.invoke(annotation);
                    for (int viewId : viewIds) {
                        Method findViewById = clazz.getMethod("findViewById", int.class);
                        View view = (View) findViewById.invoke(context,viewId);

                        if (view == null){
                            continue;
                        }
                        //动态代理
                        Method setOnClickListener = view.getClass().getMethod(listenerSetter, listenerType);
                        ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(context,methodMap);
                        Object proxyInstance = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, listenerInvocationHandler);
                        setOnClickListener.invoke(view,proxyInstance);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
