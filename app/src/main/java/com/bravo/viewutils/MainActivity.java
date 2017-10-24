package com.bravo.viewutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bravo.vutils.ViewUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }

    public void click(View v){}
    public void longClick(View v){}
}