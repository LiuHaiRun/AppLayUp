package com.jy.liuhairui.applayup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jy.liuhairui.applayup.design.RoundImage;
import com.jy.liuhairui.applayup.frame.BaseMvpActivity;
import com.jy.liuhairui.applayup.mvp.DataModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.jy.liuhairui.applayup.utils.SharedPrefrenceUtils;
import com.jy.liuhairui.applayup.utils_dlu.DialogFromBottom;
import com.jy.liuhairui.applayup.utils_dlu.FileProviderUtils;
import com.jy.liuhairui.applayup.utils_dlu.PhotosUtils;
import com.jy.liuhairui.applayup.utils_dlu.YanZhuang;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EnrollActivity extends BaseMvpActivity<DataModel> implements View.OnClickListener {

    @BindView(R.id.tba_img)
    ImageView tbaImg;
    @BindView(R.id.tablayout)
    Toolbar tablayout;
    @BindView(R.id.img)
    RoundImage img;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.et_sjh)
    EditText etSjh;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.btn_yzm)
    TextView btnYzm;
    @BindView(R.id.btn_zc)
    Button btnZc;
    private DialogFromBottom mDialogFromBottom;
    private File mCropFile;
    private File mCameraFile;
    String imgUrl = "http://yun918.cn/study/public/file_upload.php";
    String zcUrl = "http://yun918.cn/study/public/index.php/register";
    private String mImgUrl;


    @Override
    public int getLayoutId() {
        return R.layout.activity_enroll;
    }

    @Override
    public void initView() {
        //点击头像弹出相机相册
        View dialogContent = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null, false);
        mDialogFromBottom = new DialogFromBottom(this);
        mDialogFromBottom.setContentView(dialogContent);
        View camera = dialogContent.findViewById(R.id.open_from_camera);
        View photos = dialogContent.findViewById(R.id.open_album);
        camera.setOnClickListener(this);
        photos.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_from_camera://相机
                //获取SD卡路径
                String filePath = Environment.getExternalStorageDirectory() + File.separator;
                //裁切后输出的图片
                mCropFile = new File(filePath + System.currentTimeMillis() + ".jpg");
                mCameraFile = new File(filePath + System.currentTimeMillis() + ".jpg");
                try {
                    if (!mCameraFile.exists()) {
                        mCameraFile.createNewFile();
                    }
                    if (!mCropFile.exists()) {
                        mCropFile.createNewFile();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //工具类
                PhotosUtils.goCamera(this, mCameraFile);
                mDialogFromBottom.dismiss();
                break;
            case R.id.open_album://相册
                PhotosUtils.selectPhoto(this);
                mDialogFromBottom.dismiss();
                break;
        }
    }

    @OnClick({R.id.tba_img, R.id.img, R.id.btn_yzm, R.id.btn_zc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tba_img://返回首页
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.img://设置头像
                mDialogFromBottom.show();
                break;
            case R.id.btn_yzm://验证码
                yanZhangMa();
                break;
            case R.id.btn_zc://注册
                zhuCe();
                break;
        }
    }

    private void yanZhangMa() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://yun918.cn/study/public/index.php/verify")
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "获取验证码失败: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                YanZhuang yanZhuang = new Gson().fromJson(string, YanZhuang.class);
                final String data = yanZhuang.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etYzm.setText(data);
                    }
                });

            }

        });
    }

    private void zhuCe() {
        String name = etName.getText().toString();
        String psw = etPsw.getText().toString();
        String sjh = etSjh.getText().toString();
        String yzm = etYzm.getText().toString();
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(psw)) {
            Toast.makeText(mApplication, "账号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(sjh)) {
            Toast.makeText(mApplication, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(yzm)) {
            Toast.makeText(mApplication, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (!sjh.matches("^1[0-9]{10}$")) {
            Toast.makeText(mApplication, "你输入的手机号不正确", Toast.LENGTH_SHORT).show();
        } else if (name.matches("^[0-9]{6,16}$") && psw.matches("^[a-zA-Z0-9]{6,16}$")) {
            //
            okHttpUploadName(name, psw, sjh, yzm);
        } else {
            Toast.makeText(mApplication, "账号密码不能少于6位", Toast.LENGTH_SHORT).show();
        }

    }

    private void okHttpUploadName(final String name, final String psw, final String sjh, String yzm) {
        OkHttpClient client = new OkHttpClient();
        //1.
        //请求体
        FormBody formBody = new FormBody.Builder()
                .addEncoded("username", name)
                .addEncoded("password", psw)
                .addEncoded("phone", sjh)
                .addEncoded("verify", yzm)
                .build();

        Request request = new Request.Builder()
                .url(zcUrl)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "注册失败: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Log.d("TAG", "注册成功: " + string);

                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("sjh", sjh);
                intent.putExtra("psw", psw);
                setResult(200, intent);
                finish();
            }
        });


    }


    //方法重写，获取照片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri fileUrl;
        switch (requestCode) {
            case PhotosUtils.REQUEST_CODE_PAIZHAO:
                //拍照完成，进行图片裁切
                fileUrl = FileProviderUtils.uriFromFile(this, mCameraFile, PhotosUtils.FILE_PROVIDER_AUTHORITY);
                PhotosUtils.doCrop(this, fileUrl, mCropFile);
                break;
            case PhotosUtils.REQUEST_CODE_ZHAOPIAN:
                //相册选择图片完毕，进行图片裁切
                if (data == null || data.getData() == null) {
                    return;
                }
                fileUrl = data.getData();
                PhotosUtils.doCrop(this, fileUrl, mCropFile);
                break;
            case PhotosUtils.REQUEST_CODE_CAIQIE:
                //图片裁切完成，显示裁切后的图片
                try {
                    //上传
                    okHttpUploadImg(mCropFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    //上传照片
    private void okHttpUploadImg(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);

        MultipartBody build = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "llll")
                .addFormDataPart("file", file.getName(), requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        Request build1 = new Request.Builder()
                .url(imgUrl)
                .post(build)
                .build();

        okHttpClient.newCall(build1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "注册页面onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Logger.logD("TAG", "注册页面成功onResponse: " + string);
                try {
                    //原生解析
                    JSONObject jsonObject = new JSONObject(string);
                    JSONObject obj = jsonObject.getJSONObject("data");
                    mImgUrl = obj.optString("url");
                    mApplication.zcImgUrl = mImgUrl;
                    //保存照片路径
                    SharedPrefrenceUtils.saveString(EnrollActivity.this,AppConstants.LOGON_IMG, mImgUrl);
                    Logger.logD("TAG", "注册页面保存在sp的头像网址: " + mImgUrl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                                    .skipMemoryCache(true)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE);//不做内存缓存
                            Glide.with(EnrollActivity.this)
                                    .load(mImgUrl)
                                    .centerCrop()
                                    .apply(mRequestOptions)
                                    .into(img);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
