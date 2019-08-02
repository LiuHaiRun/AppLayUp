package com.jy.liuhairui.applayup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean.WallData;
import com.jy.liuhairui.applayup.design.RoundOrCircleImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *
 */
public class RlvWellAdapter extends RecyclerView.Adapter<RlvWellAdapter.ViewHolder> {



    private Context mContext;
    private ArrayList<WallData.FeedlistBean> mWallList;

    public RlvWellAdapter(Context context, ArrayList<WallData.FeedlistBean> wallList) {
        mContext = context;
        mWallList = wallList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View view = View.inflate(mContext, R.layout.fragment_homt_item_wall_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //加载数据
        WallData.FeedlistBean feedlistBean = mWallList.get(position);
        //添加头像
        Glide.with(mContext).load(feedlistBean.getAvatar()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.avarter);
        //
        holder.text1.setText(feedlistBean.getAccount());//账号
        holder.text2.setText(feedlistBean.getNote());//名字
        holder.text3.setText(feedlistBean.getPublished_at());//时间
        holder.text4.setText(feedlistBean.getOriginal_text()+"\n"+feedlistBean.getAuto_translation());//英文和中文的内容

        //获取图片
        WallData.FeedlistBean.AlbumBean.PicsBean picsBean = feedlistBean.getAlbum().getPics().get(0);
        //展示网络图片，并获取数据中自带的图片宽高，设置到布局
        Glide.with(mContext).load(picsBean.getUrl()).override(picsBean.getWidth(),picsBean.getHeight()).into(holder.image);
    }

    @Override
    public int getItemCount() {

        return  mWallList != null ? mWallList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avarter)
        RoundOrCircleImage avarter;
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.text2)
        TextView text2;
        @BindView(R.id.text3)
        TextView text3;
        @BindView(R.id.text4)
        TextView text4;
        @BindView(R.id.image)
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
