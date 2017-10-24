package com.bravo.viewutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bravo.vutils.ViewUtils;
import com.bravo.vutils.anno.ContentView;
import com.bravo.vutils.anno.OnClick;
import com.bravo.vutils.anno.OnLongClick;
import com.bravo.vutils.anno.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tv)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        tv.setText("你好，你好，你好！");
    }
    @OnClick(value = R.id.btn_click)
    public void click(View v){
        tv.setText("你好，你点击了一下");
        Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
    }
    @OnLongClick(value = R.id.btn_long_click)
    public boolean longClick(View v){
        tv.setText("你好，你   长——按  了一下");
        Toast.makeText(this,"  长    按  ",Toast.LENGTH_SHORT).show();
        return true;
    }
}
