package com.phone.call.hy.oldcallphone.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone.call.hy.oldcallphone.R;

public class SelectPhotoFragment extends DialogFragment {
    Callback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_photo,container);
    }

    public  interface Callback{
        void onPhoto();
        void onCamera();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext())
                .setTitle("选择头像")
                .setPositiveButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callback!=null){
                            callback.onPhoto();
                        }
                    }
                })
                .setNegativeButton("相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callback!=null){
                            callback.onCamera();
                        }
                    }
                });
        return builder.create();
    }
}
