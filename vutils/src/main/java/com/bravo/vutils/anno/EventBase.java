package com.bravo.vutils.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bravo.lee on 2017/10/24.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
    /**
     * 监听的方法名
     * @return 方法名
     */
    String listenerSetter();

    /**
     * 事件类型
     * @return
     */
    Class<?> listenerType();

    /**
     * 回调的方法名
     * @return
     */
    String callBackMethod();
}
