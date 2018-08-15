package com.phone.call.hy.oldcallphone.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.phone.call.hy.oldcallphone.R;
import com.phone.call.hy.oldcallphone.fragment.SelectPhotoFragment;
import com.phone.call.hy.oldcallphone.unit.FileUtil;

public class AddPeopleActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_ALBUM_OK = 101;
    private ImageView iv_peple_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_peo_ple);
        initView();
    }

    private void initView(){
        findViewById(R.id.iv_add_photo).setOnClickListener(this);
        iv_peple_icon = findViewById(R.id.iv_peple_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_photo:
                initDialog();

                break;
        }
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
                        iv_peple_icon.setImageBitmap(resource);
                        boolean isOk = FileUtil.saveBitmapToSDCardPrivateCacheDir(resource,FileUtil.getNowTimeMS()+".png",AddPeopleActivity.this);
                        if(isOk){
                            Log.i(TAG, "onActivityResult: 保存成功");
                        }else {
                            Log.i(TAG, "onActivityResult: 保存失败");
                        }
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
