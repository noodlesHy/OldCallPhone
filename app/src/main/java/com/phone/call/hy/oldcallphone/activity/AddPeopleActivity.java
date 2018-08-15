package com.phone.call.hy.oldcallphone.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.phone.call.hy.oldcallphone.R;
import com.phone.call.hy.oldcallphone.db.DbHelpManager;
import com.phone.call.hy.oldcallphone.fragment.SelectPhotoFragment;
import com.phone.call.hy.oldcallphone.javabean.PeopleInfo;
import com.phone.call.hy.oldcallphone.unit.FileUtil;

public class AddPeopleActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_ALBUM_OK = 101;
    private ImageView iv_peple_icon;
    private EditText et_user_name,et_user_phone;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_peo_ple);
        initView();
    }

    private void initView(){
        findViewById(R.id.iv_add_photo).setOnClickListener(this);
        findViewById(R.id.btn_confirm).setOnClickListener(this);
        et_user_name = findViewById(R.id.et_user_name);
        et_user_phone = findViewById(R.id.et_user_phone);
        iv_peple_icon = findViewById(R.id.iv_peple_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_photo:
                initDialog();
                break;
            case R.id.btn_confirm:
                confim();
                break;
        }
    }

    private void confim(){
        String number = et_user_phone.getText().toString();
        if(TextUtils.isEmpty(number)){
            Toast.makeText(this,"请输入手机号码",Toast.LENGTH_LONG);
            return;
        }
        String name = et_user_name.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"请输入姓名",Toast.LENGTH_LONG);
            return;
        }
        if(iv_peple_icon.getVisibility()==View.INVISIBLE){
            Toast.makeText(this,"请设置头像",Toast.LENGTH_LONG);
            return;
        }
        String imgurl = FileUtil.getNowTimeMS()+".png";
        boolean isOk = FileUtil.saveBitmapToSDCardPrivateCacheDir(mBitmap,imgurl,AddPeopleActivity.this);
        if(isOk){
            Log.i(TAG, "onActivityResult: 保存成功");
        }else {
            Log.i(TAG, "onActivityResult: 保存失败");
        }
        PeopleInfo info = new PeopleInfo();
        info.setPhone(number);
        info.setName(name);
        info.setImgurl(imgurl);
        addPeople(info);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBitmap!=null||mBitmap.isRecycled()){
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    private void addPeople(PeopleInfo info){
        DbHelpManager dbHelpManager = new DbHelpManager(this);
        dbHelpManager.addPeople(info);
    }


    private void openCreame(){
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, REQUEST_ALBUM_OK);
    }

    private static final String TAG = "AddPeopleActivity";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_ALBUM_OK:
                    Uri uri = data.getData();
//                Log.i("图片的uri",uri.get)
                iv_peple_icon.setVisibility(View.VISIBLE);
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.ic_launcher);
                options.diskCacheStrategy(DiskCacheStrategy.NONE);
                options.error(R.mipmap.ic_launcher);
//                options.override(400,600);
                options.centerCrop();
                SimpleTarget target = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        mBitmap = resource;
                        iv_peple_icon.setImageBitmap(resource);

                    }

                };

                Glide.with(this).asBitmap().load(uri).apply(options).into(target);

//                    if(uri!=null){
//                        iv_peple_icon.setVisibility(View.VISIBLE);
//                        iv_peple_icon.setImageURI(uri);
//                    }
                break;
        }
    }

    private void initDialog(){
        SelectPhotoFragment selectPhotoFragment = new SelectPhotoFragment();
        selectPhotoFragment.show(getSupportFragmentManager(),"photo");
        selectPhotoFragment.setCallback(new SelectPhotoFragment.Callback() {
            @Override
            public void onPhoto() {
                openCreame();
            }

            @Override
            public void onCamera() {

            }
        });
    }
}
