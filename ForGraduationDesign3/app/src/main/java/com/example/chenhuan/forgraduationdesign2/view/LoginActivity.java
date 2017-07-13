package com.example.chenhuan.forgraduationdesign2.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.bean.User;
import com.example.chenhuan.forgraduationdesign2.model.util.LoginUtil;
import com.example.chenhuan.forgraduationdesign2.model.util.ShowSnackBar;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private TextView registTv;
    private ImageView loginBackIv;
    private Button loginBtn;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private ProgressBar loginProgressBar;

    @Override
    protected int getContentId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        // 专场动画
        getWindow().setEnterTransition(new Fade().setDuration(1000));
        getWindow().setExitTransition(new Fade().setDuration(1000));

        loginBackIv = ((ImageView) findViewById(R.id.login_back_icon));
        loginBtn = ((Button) findViewById(R.id.login_btn));
        registTv = ((TextView) findViewById(R.id.regist_tv));
        usernameInput = ((TextInputEditText) findViewById(R.id.login_username_ed));
        passwordInput = ((TextInputEditText) findViewById(R.id.login_password_ed));
        loginProgressBar = ((ProgressBar) findViewById(R.id.login_progress_bar));
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void bindListener() {
        registTv.setOnClickListener(this);//注册的点击事件
        loginBackIv.setOnClickListener(this);//返回的点击事件
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_tv: //点击注册
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0版本以上支持专场动画
                    //共享元素动画
                    startActivity(new Intent(this, RegistActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                } else {//兼容5.0以下版本
                    startActivity(new Intent(this, RegistActivity.class));
                }
                break;
            case R.id.login_back_icon: //点击返回
                this.finish();
                break;
            case R.id.login_btn://点击登陆
                loginProgressBar.setVisibility(View.VISIBLE);
                doLogin();
                break;

        }
    }

    //点击登陆
    private void doLogin() {
        final String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.addWhereEqualTo("password", password);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null && list.size() == 1) {
                    LoginUtil.setLoginStatus(LoginActivity.this,true);//保存用户状态
                    ShowSnackBar.showSnackBar(registTv,"登陆成功");
                    loginProgressBar.setVisibility(View.INVISIBLE);
                    //休眠两秒以显示snackbar
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.currentThread().sleep(2000);
//                                doBack();//模拟点击返回
                                LoginUtil.setLoginUserName(LoginActivity.this,username);
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();

                } else {
                    ShowSnackBar.showSnackBar(registTv,"用户名或密码错误");
                    loginProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
