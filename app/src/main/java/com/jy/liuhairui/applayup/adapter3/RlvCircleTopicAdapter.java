package com.jy.liuhairui.applayup.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean3.CircleTopicData;
import com.jy.liuhairui.applayup.design.RoundOrCircleImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvCircleTopicAdapter extends RecyclerView.Adapter<RlvCircleTopicAdapter.ViewHolder> {



    private Context mContext;
    private ArrayList<CircleTopicData.DataBean.TopicListBean> mList;

    public RlvCircleTopicAdapter(Context context, ArrayList<CircleTopicData.DataBean.TopicListBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View view = View.inflate(mContext, R.layout.fragment_circler_topic_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //加载数据
        CircleTopicData.DataBean.TopicListBean topicListBean = mList.get(position);
        Glide.with(mContext).load(topicListBean.getLogo_url()).into(holder.logo);
        holder.title.setText(topicListBean.getTitle());
        //获取网络布局的列数
        int rowNum = mList.get(position).getType().equals("line_two") ? 2 : 4;
        GridLayoutManager manager = new GridLayoutManager(mContext,rowNum);
        holder.contentRecyclerView.setLayoutManager(manager);
        //创建嵌套的Recycler适配器
        List<CircleTopicData.DataBean.TopicListBean.ListBean> list = topicListBean.getList();
        RlvTopicItemAdapter adapter = new RlvTopicItemAdapter(mContext, rowNum, list);
        holder.contentRecyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo)
        RoundOrCircleImage logo;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content_recyclerView)
        RecyclerView contentRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
