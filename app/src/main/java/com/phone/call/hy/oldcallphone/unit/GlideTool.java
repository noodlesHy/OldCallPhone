package com.phone.call.hy.oldcallphone.unit;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.phone.call.hy.oldcallphone.R;


import java.io.File;

public class GlideTool {

    public static void headerDisplay(Context context, String url, ImageView im){

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);

    }

    public static void headerDisplay(Context context, File file, ImageView im){

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(file)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);

    }

    public static void userIconDisplay(Context context, String url, ImageView im){

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);

    }

//    public static void getBitmap(Context context, File file,boolean isFit,SimpleTarget<Bitmap> target){
//
//        RequestOptions options = new RequestOptions();
//        options.skipMemoryCache(true);
//        options.diskCacheStrategy(DiskCacheStrategy.NONE);
//        options.override(DistanceTool.getScreenWidth(context), DistanceTool.getScreenHeight(context));
//
//        if(isFit){
//
//            options.fitCenter();
//
//        }
//
//        Glide
//                .with(context)
//                .asBitmap()
//                .load(file)
//                .apply(options)
//                .into(target);
//
//    }



    public static void albumAdapterDisplay(Context context, File file, ImageView im){
        RequestOptions options = new RequestOptions();
        options.skipMemoryCache(true);
        options.diskCacheStrategy(DiskCacheStrategy.NONE);
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(file)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);
    }

    public static void feedbackReleaseImgDisplay(Context context, String url, ImageView im){
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);
    }

    public static void originalDisplay(Context context,int id,ImageView im){
        RequestOptions options = new RequestOptions();
        options.skipMemoryCache(true);
        options.diskCacheStrategy(DiskCacheStrategy.NONE);
        options.placeholder(id);
        options.fallback(id);
        options.fitCenter();
        Glide
                .with(context)
                .load(id)
                .apply(options)
                .into(im);
    }

    public static void videoCoverDisplay(Context context, String url, ImageView im){

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .into(im);

    }

    public static void listIconDisplay(Context context, String url, ImageView im){
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        options.fitCenter();
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);
    }

    public static void circularDisplay(Context context,String url ,ImageView im){
        RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(20));
        options.placeholder(R.mipmap.ic_launcher_round);
        options.fallback(R.mipmap.ic_launcher_round);
        Glide
                .with(context)
                .load(url)
                .apply(options)
                .thumbnail(0.1f)
                .into(im);
    }

}
