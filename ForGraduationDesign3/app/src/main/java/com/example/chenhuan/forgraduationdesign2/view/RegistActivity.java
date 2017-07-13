package com.example.chenhuan.forgraduationdesign2.view;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.chenhuan.forgraduationdesign2.R;
import com.example.chenhuan.forgraduationdesign2.model.bean.User;
import com.example.chenhuan.forgraduationdesign2.model.util.ShowSnackBar;
import com.example.chenhuan.forgraduationdesign2.view.base.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.example.chenhuan.forgraduationdesign2.model.util.DoBack.doBack;

public class RegistActivity extends BaseActivity implements View.OnClickListener {


    private TextInputEditText usernameInput;
    private Button registBtn;
    private TextInputEditText emailEdText;
    private TextInputEditText ensureEdText;
    private TextInputEditText passEdText;
    private TextInputEditText telEdText;
    private TextInputEditText usernameEdText;
    private TextInputLayout emailEdLayout;
    private TextInputLayout ensureEdLayout;
    private TextInputLayout passEdLayout;
    private TextInputLayout telEdLayout;
    private TextInputLayout usernameEdLayout;
    private ProgressBar progressBar;
    private ImageView registBackIv;

    @Override
    protected int getContentId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        //转场动画 渐变
        getWindow().setEnterTransition(new Fade().setDuration(1000));
        getWindow().setExitTransition(new Fade().setDuration(1000));
        usernameInput = ((TextInputEditText) findViewById(R.id.username_ed));
        registBtn = ((Button) findViewById(R.id.regist_btn));
        emailEdText = ((TextInputEditText) findViewById(R.id.email_ed));
        ensureEdText = ((TextInputEditText) findViewById(R.id.ensure_ed));
        passEdText = ((TextInputEditText) findViewById(R.id.pass_ed));
        telEdText = ((TextInputEditText) findViewById(R.id.tel_ed));
        usernameEdText = ((TextInputEditText) findViewById(R.id.username_ed));
        emailEdLayout = ((TextInputLayout) findViewById(R.id.email_ed_layout));
        ensureEdLayout = ((TextInputLayout) findViewById(R.id.ensure_ed_layout));
        passEdLayout = ((TextInputLayout) findViewById(R.id.pass_ed_layout));
        telEdLayout = ((TextInputLayout) findViewById(R.id.tel_ed_layout));
        usernameEdLayout = ((TextInputLayout) findViewById(R.id.username_ed_layout));
        progressBar = ((ProgressBar) findViewById(R.id.progress_bar));
        registBackIv = ((ImageView) findViewById(R.id.regist_back_iv));
        isRegistInfoOk();
    }

    //判断注册信息是否合法
    private boolean isRegistInfoOk() {

        return false;
    }

    private boolean isEmail(String s) {
        String regex = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
        return s.matches(regex);
    }

    @Override
    protected void loadData() {
        
    }

    @Override
    protected void bindListener() {
        registBtn.setOnClickListener(this);
        //邮箱格式验证
        emailEdText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();
                if (email==null||email.equals("")){
                    emailEdText.setError("邮箱地址不能为空！");
                } else if (!isEmail(email)) {
                    emailEdText.setError("错误的邮箱地址！");
                } else {
                    emailEdLayout.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //手机号码格式验证
        telEdText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String tel = s.toString();

                if (isTelNumber(tel)) {
                   telEdLayout.setErrorEnabled(false);
                }  if (tel==null||tel.equals("")) {
                    telEdText.setError("手机号码不能为空！");
                }
                    else if (!isTelNumber(tel)) {
                    telEdText.setError("错误的手机号码！");
                }
            }
        });
        //确认密码验证
        ensureEdText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String enPassword = s.toString();
                if (enPassword.equals(passEdText.getText().toString())) {
                    ensureEdLayout.setErrorEnabled(false);
                }else if (!enPassword.equals(passEdText.getText().toString())) {
                    ensureEdText.setError("密码输入不一致");
                }
            }
        });
        registBackIv.setOnClickListener(this);
    }

    private boolean isTelNumber(String tel) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regex = "[1][358]\\d{9}";
        return tel.matches(regex);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_btn://点击注册
                progressBar.setVisibility(View.VISIBLE);
                doRegist();
                break;
            case R.id.regist_back_iv://点击返回
                doBack();
                break;
        }
    }
    private void doRegist() {
        User user = new User();
        String username = usernameEdText.getText().toString();
        String email = emailEdText.getText().toString();
        String ensurePass = ensureEdText.getText().toString();
        String password = passEdText.getText().toString();
        String telNumber = telEdText.getText().toString();
        user.setUsername(username);
        user.setEmailAddress(email);
        user.setPassword(password);
        user.setTelNumber(telNumber);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    ShowSnackBar.showSnackBar(registBtn,"注册成功，请登陆！");
                    //休眠两秒以显示snackbar
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.currentThread().sleep(2000);
                                doBack();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    if (e.getErrorCode()==401){
                        Log.d("test", "done: -----------" + e.toString());
                        progressBar.setVisibility(View.INVISIBLE);
                        ShowSnackBar.showSnackBar(registBtn,"手机号码已注册，请直接登陆");
                    }
                }
            }
        });
    }
}
