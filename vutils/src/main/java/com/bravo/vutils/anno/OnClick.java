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
@EventBase(listenerSetter = "setOnClickListener",
        listenerType = View.OnClickListener.class,
        callBackMethod = "onClick")
public @interface OnClick {
    /**
     * 哪些控件id设置了点击事件。
     * @return
     */
    int[] value();
}
