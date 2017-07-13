package com.example.chenhuan.forgraduationdesign2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;

public class ServiceActivity extends AppCompatActivity {

    private TextView serviceContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        serviceContent = ((TextView) findViewById(R.id.service_content));
        serviceContent.setSelected(true);
    }
}
