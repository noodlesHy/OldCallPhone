package com.phone.call.hy.oldcallphone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.phone.call.hy.oldcallphone.R;
import com.phone.call.hy.oldcallphone.fragment.SelectPhotoFragment;

public class AddPeopleActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_ALBUM_OK = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_peo_ple);
        initView();
    }

    private void initView(){
        findViewById(R.id.iv_add_photo).setOnClickListener(this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_ALBUM_OK:

                break;
        }
    }

    private void initDialog(){
        SelectPhotoFragment selectPhotoFragment = new SelectPhotoFragment();
        selectPhotoFragment.show(getSupportFragmentManager(),"photo");
    }
}
