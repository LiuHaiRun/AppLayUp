package com.jy.liuhairui.applayup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.bean.VideoData;
import com.jy.liuhairui.applayup.design.RoundOrCircleImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *   //左文本右图
 * */
public class RlvVideoAdapter extends RecyclerView.Adapter<RlvVideoAdapter.ViewHolder> {



    private Context mContext;
    private ArrayList<NormalData.ArticlesBean> mVideoList;

    public RlvVideoAdapter(Context context, ArrayList<NormalData.ArticlesBean> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.fragment_video_item_video_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //加载数据
        NormalData.ArticlesBean articlesBean = mVideoList.get(position);

        Glide.with(mContext).load(articlesBean.getThumb()).centerCrop().into(holder.roundImage);
        holder.title.setText(articlesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.round_image)
        RoundOrCircleImage roundImage;
        @BindView(R.id.title)
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
