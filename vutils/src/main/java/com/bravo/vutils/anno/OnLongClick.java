package com.bravo.vutils.anno;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bravo.lee on 2017/10/24.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnLongClickListener",
        listenerType = View.OnLongClickListener.class,callBackMethod = "onLongClick")
public @interface OnLongClick {
    int[] value() default -1;
}
