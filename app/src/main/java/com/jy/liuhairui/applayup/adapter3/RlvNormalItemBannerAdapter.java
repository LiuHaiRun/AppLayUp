package com.jy.liuhairui.applayup.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean3.CircleNormalData;
import com.jy.liuhairui.applayup.design.RoundOrCircleImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *图片集的适配器
 **/
public class RlvNormalItemBannerAdapter extends RecyclerView.Adapter<RlvNormalItemBannerAdapter.ViewHolder> {


    private Context mContext;
    List<CircleNormalData.DataBean.TopicBannerBean.TopicListBean> mList;

    public RlvNormalItemBannerAdapter(Context context, List<CircleNormalData.DataBean.TopicBannerBean.TopicListBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.fragment_circle_item_banner_adapter_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CircleNormalData.DataBean.TopicBannerBean.TopicListBean topicListBean = mList.get(position);

        Glide.with(mContext).load(topicListBean.getBg_url()).into(holder.imageBg);
        holder.title.setText(topicListBean.getTitle());
        holder.commentNum.setText(topicListBean.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_bg)
        RoundOrCircleImage imageBg;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.comment_num)
        TextView commentNum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
