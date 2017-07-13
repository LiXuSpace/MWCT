package com.example.chenhuan.forgraduationdesign2.model.util;

import android.app.Instrumentation;
import android.view.KeyEvent;

/**
 * Created by lixu on 2017/4/17.
 *   模拟系统返回键功能
 */

public class DoBack {
    public static void doBack(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation instrumentation = new Instrumentation();
                instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }
        }).start();
    }
}
