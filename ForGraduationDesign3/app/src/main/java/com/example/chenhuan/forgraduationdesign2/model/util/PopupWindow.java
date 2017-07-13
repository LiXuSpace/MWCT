package com.example.chenhuan.forgraduationdesign2.model.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.OnOrderInfoListener;

/**
 * Created by lixu on 2017/5/18.
 */

public class PopupWindow extends Dialog {
    private Context context;
    public OnOrderInfoListener listener;
    private EditText PpAddress;
    private EditText PpName;
    private Button ppBtn;
    private EditText PpPhone;

    public PopupWindow(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public PopupWindow(@NonNull Context context, @StyleRes int themeResId, OnOrderInfoListener listener) {
        super(context, themeResId);
        this.context = context;
        this.listener = listener;
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.popup_window_layout);
        PpAddress = ((EditText) findViewById(R.id.address_pp));
        PpName = ((EditText) findViewById(R.id.name_pp));
        ppBtn = ((Button) findViewById(R.id.pp_btn));
        PpPhone = ((EditText) findViewById(R.id.phone_pp));
        Window PpWindow = this.getWindow();
//        Display d = PpWindow.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = PpWindow.getAttributes();
        PpWindow.setAttributes(p);
        ppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = PpAddress.getText().toString();
                String name = PpName.getText().toString();
                String phone = PpPhone.getText().toString();
                if (addr == null||addr=="" || name == null ||name==""|| phone == null||phone=="") {
                    Log.d("test", "onClick: ---------------111111");
                    listener.error("不能有空项，请填写完整！");
                } else {
                    listener.success(addr, name, phone);
                    Log.d("test", "onClick: ---------------------");
                }


            }
        });
        this.setCancelable(true);

    }
}
