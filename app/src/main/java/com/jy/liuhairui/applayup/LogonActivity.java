package com.jy.liuhairui.applayup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jy.liuhairui.applayup.bean.LogonData;
import com.jy.liuhairui.applayup.frame.BaseMvpActivity;
import com.jy.liuhairui.applayup.mvp.DataModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.jy.liuhairui.applayup.utils.SharedPrefrenceUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogonActivity extends BaseMvpActivity<DataModel> {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText et_psw;
    @BindView(R.id.btn_dl)
    Button btnDl;
    @BindView(R.id.btn_zc)
    TextView btnZc;
    @BindView(R.id.tba_img)
    ImageView tbaImg;
    @BindView(R.id.tablayout)
    Toolbar tablayout;

    private String dlUrl = "http://yun918.cn/study/public/index.php/login";

    @Override
    public int getLayoutId() {
        return R.layout.activity_logon;
    }

    @Override
    public void initView() {

    }

    //重写方法，获取回传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == 200) {
            String name = data.getStringExtra("name");
            String sjh = data.getStringExtra("sjh");
            String psw = data.getStringExtra("psw");
            etName.setText(name);
            etName.setText(sjh);
            et_psw.setText(psw);

        }
    }

    @Override
    public void initData() {
    }

    @Override
    public DataModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
    }

    @OnClick({R.id.tba_img, R.id.btn_dl, R.id.btn_zc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tba_img: //返回首页
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_dl://登录
                okHttpDl();
                Toast.makeText(mApplication, "登录成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_zc://注册
                Intent intent2 = new Intent(this, EnrollActivity.class);
                startActivityForResult(intent2, 100);
                break;
        }
    }

    private void okHttpDl() {
        String username = etName.getText().toString();
        String password = et_psw.getText().toString();

        OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .addEncoded("username", username)
                .addEncoded("password", password)
                .build();

        Request request = new Request.Builder()
                .url(dlUrl)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.logD("TAG", "登录失败: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Logger.logD("TAG", "登录成功: " + string);
                //获取登录后返回的uid
                Gson gson = new Gson();
                LogonData logonData = gson.fromJson(string, LogonData.class);
                List<LogonData.DataBean> data = logonData.getData();
                for (int i = 0; i < data.size(); i++) {
                    LogonData.DataBean dataBean = data.get(i);
                    String uid = dataBean.getUid();
                    String name = dataBean.getName();
                    mApplication.mToKen = uid;
                    mApplication.zcName = name;
                    SharedPrefrenceUtils.saveString(LogonActivity.this,AppConstants.USER_NAME,name);
                    SharedPrefrenceUtils.saveString(LogonActivity.this,AppConstants.USER_TOKEN,uid);
                    Logger.logD("TAG", "登录成功返回的uid: " + uid);
                }

            }
        });
    }
}
