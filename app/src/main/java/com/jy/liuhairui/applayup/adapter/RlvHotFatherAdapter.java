package com.jy.liuhairui.applayup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean.HotData;
import java.util.List;

/**
 *
 */
public class RlvHotFatherAdapter extends RecyclerView.Adapter<RlvHotFatherAdapter.ViewHolder> {

    private Context mContext;
    private List<HotData.ContentsBean> mContentsBeanList;

    public RlvHotFatherAdapter(Context context, List<HotData.ContentsBean> contentsBeanList) {
        mContext = context;
        mContentsBeanList = contentsBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View inflate = View.inflate(mContext, R.layout.hot_father_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //加载数据
        holder.mTextView.setText(mContentsBeanList.get(position).getDay());
        //添加布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        holder.mRecyclerView.setLayoutManager(manager);
        //
        List<HotData.ContentsBean.ArticlesBean> articles = mContentsBeanList.get(position).getArticles();
        RlvHotAdapter adapter = new RlvHotAdapter(mContext, articles);
        holder.mRecyclerView.setAdapter(adapter);
        holder.mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return mContentsBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        RecyclerView mRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);
            mTextView = itemView.findViewById(R.id.title_days);
        }
    }
}
