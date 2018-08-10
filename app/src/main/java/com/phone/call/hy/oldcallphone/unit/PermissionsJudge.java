package com.phone.call.hy.oldcallphone.unit;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by hy on 2018/8/10.
 */
public class PermissionsJudge {
    private Activity mActivity;

    public PermissionsJudge(Activity activity) {
        this.mActivity = activity;
    }

    public boolean isPermissions(String permissions) {
        if (ActivityCompat.checkSelfPermission(mActivity, permissions) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public void requestPermissions(String permissions,int requestCode){
        ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.CALL_PHONE},requestCode);
    }

    public void autoPermissions(String permissions){
        if(!isPermissions(permissions)){
            requestPermissions(permissions,0);
        }
    }



}
