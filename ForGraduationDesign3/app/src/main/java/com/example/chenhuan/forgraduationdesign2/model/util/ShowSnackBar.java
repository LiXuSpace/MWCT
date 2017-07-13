package com.example.chenhuan.forgraduationdesign2.model.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.chenhuan.forgraduationdesign2.R;

/**
 * Created by lixu on 2017/4/15.
 */

public class ShowSnackBar  {
    public static void showSnackBar(View view,String s) {
        Snackbar snackbar = Snackbar.make(view, s, 2000);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(view.getResources().getColor(R.color.textcolor));
        snackbar.show();
    }
}
