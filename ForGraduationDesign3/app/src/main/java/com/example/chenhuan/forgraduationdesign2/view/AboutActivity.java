package com.example.chenhuan.forgraduationdesign2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;

public class AboutActivity extends AppCompatActivity {

    private TextView aboutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutContent = ((TextView) findViewById(R.id.about_content));
        aboutContent.setSelected(true);
    }
}
