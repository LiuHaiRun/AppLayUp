package com.jy.liuhairui.applayup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean.HotData;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/*
 *   //粘性头部局
 * */
public class RlvHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<HotData.ContentsBean.ArticlesBean> mHotList;

    public RlvHotAdapter(Context context, List<HotData.ContentsBean.ArticlesBean> hotList) {
        mContext = context;
        mHotList = hotList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        if (viewType == 0) {
            View view = View.inflate(mContext, R.layout.hot_adapter_layout, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else {
            View view = View.inflate(mContext, R.layout.hot_video_adapter_layout, null);
            VideoHolder videoHolder = new VideoHolder(view);
            return videoHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //加载数据
        int type = getItemViewType(position);
        HotData.ContentsBean.ArticlesBean articlesBean = mHotList.get(position);
        if (type == 0){
            ViewHolder viewHolder = (ViewHolder) holder;
            //
            Glide.with(mContext).load(articlesBean.getThumb()).into(viewHolder.leftImage);
            //
            viewHolder.title.setText(articlesBean.getTitle());
            viewHolder.commentsTotal.setText(articlesBean.getComments_total() + "评论");

            if (!TextUtils.isEmpty(mHotList.get(position).label)) {
                viewHolder.deep.setText(articlesBean.label);
                viewHolder.deep.setVisibility(View.VISIBLE);
            } else{
                viewHolder.deep.setVisibility(View.GONE);
                viewHolder.top.setVisibility(articlesBean.isTop() ? View.VISIBLE : View.GONE);
            }
        }else {
            VideoHolder videoHolder = (VideoHolder) holder;
            videoHolder.title.setText(articlesBean.getTitle());
            if (articlesBean.video_info != null){
                videoHolder.videoPlayer.setUp(articlesBean.video_info.video_src, JCVideoPlayer.SCREEN_LAYOUT_LIST);
                videoHolder.time.setText(articlesBean.video_info.video_time);
            }
            videoHolder.videoPlayer.backButton.setVisibility(View.GONE);
            videoHolder.videoPlayer.tinyBackImageView.setVisibility(View.GONE);
            Glide.with(mContext).load(articlesBean.getThumb()).centerCrop().dontAnimate().into(videoHolder.videoPlayer.thumbImageView);
            videoHolder.commentsTotal.setText(articlesBean.getComments_total() + "评论");
            if (!TextUtils.isEmpty(articlesBean.label)) {
                videoHolder.deep.setText(articlesBean.label);
                videoHolder.deep.setVisibility(View.VISIBLE);
            } else {
                videoHolder.deep.setVisibility(View.GONE);
                videoHolder.top.setVisibility(articlesBean.isTop() ? View.VISIBLE : View.GONE);
            }
        }
    }


    @Override
    public int getItemViewType(int position) {

        return !mHotList.get(position).isIs_video() ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mHotList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_image)
        ImageView leftImage;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comments_total)
        TextView commentsTotal;
        @BindView(R.id.deep)
        TextView deep;
        @BindView(R.id.top)
        TextView top;
        @BindView(R.id.top_rl)
        RelativeLayout topRl;

        public ViewHolder(View itemView) {
            super(itemView);
            //
            ButterKnife.bind(this, itemView);
        }
    }


    public class VideoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.comments_total)
        TextView commentsTotal;
        @BindView(R.id.deep)
        TextView deep;
        @BindView(R.id.top)
        TextView top;
        public VideoHolder(View itemView) {
            super(itemView);
            //
            ButterKnife.bind(this, itemView);
        }
    }

}
