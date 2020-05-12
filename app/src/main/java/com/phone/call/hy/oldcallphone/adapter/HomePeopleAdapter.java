package com.phone.call.hy.oldcallphone.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phone.call.hy.oldcallphone.R;
import com.phone.call.hy.oldcallphone.javabean.PeopleInfo;
import com.phone.call.hy.oldcallphone.unit.FileUtil;
import com.phone.call.hy.oldcallphone.unit.GlideTool;

import java.io.File;
import java.util.List;

public class HomePeopleAdapter extends RecyclerView.Adapter<HomePeopleAdapter.MyViewHolder> {
    private static final String TAG = "HomePeopleAdapter";
    private List<PeopleInfo> peopleInfos;
    private Context mContext;

    public HomePeopleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPeopleInfos(List<PeopleInfo> peopleInfos) {
        this.peopleInfos = peopleInfos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_people,parent ,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_item_name.setText(peopleInfos.get(position).getName());
        String url = FileUtil.getDiskCachePath(mContext)+"/"+peopleInfos.get(position).getImgurl();
        Log.i(TAG, "onBindViewHolder: " + url);
        GlideTool.headerDisplay(mContext,new File(url),holder.iv_item_people);
    }

    @Override
    public int getItemCount() {
        return peopleInfos == null ? 0 : peopleInfos.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item_people;
        TextView tv_item_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_item_people = itemView.findViewById(R.id.iv_item_people);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
