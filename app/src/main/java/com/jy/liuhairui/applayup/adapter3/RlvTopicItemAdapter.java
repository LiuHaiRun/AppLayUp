package com.jy.liuhairui.applayup.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean3.CircleTopicData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvTopicItemAdapter extends RecyclerView.Adapter<RlvTopicItemAdapter.ViewHolder> {



    private Context mContext;
    private int rowNum;
    private List<CircleTopicData.DataBean.TopicListBean.ListBean> mList;

    public RlvTopicItemAdapter(Context context, int rowNum, List<CircleTopicData.DataBean.TopicListBean.ListBean> list) {
        mContext = context;
        this.rowNum = rowNum;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View view = View.inflate(mContext, R.layout.fragment_circler_topic_item_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //加载数据
        CircleTopicData.DataBean.TopicListBean.ListBean listBean = mList.get(position);
        if (rowNum == 2) {
            holder.contentNobg.setVisibility(View.VISIBLE);
            holder.content.setVisibility(View.GONE);
            holder.contentNobg.setText(listBean.getContent());
        } else {
            holder.content.setVisibility(View.VISIBLE);
            holder.contentNobg.setVisibility(View.GONE);
            holder.content.setText(listBean.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.content_nobg)
        TextView contentNobg;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
