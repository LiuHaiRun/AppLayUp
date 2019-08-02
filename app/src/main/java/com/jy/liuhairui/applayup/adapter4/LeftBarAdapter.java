package com.jy.liuhairui.applayup.adapter4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean4.NbaData;
import com.jy.liuhairui.applayup.frame.OnRecyclerItemClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *   球员和球队点击侧面在主页展示数据
 */
public class LeftBarAdapter extends RecyclerView.Adapter<LeftBarAdapter.ViewHolder> {
    private Context mContext;
    private List<NbaData.ContentBean.DataBean> mListData;
    private List<String> tempList;
    private OnRecyclerItemClick mOnRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick){
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    public LeftBarAdapter(Context pContext, List<NbaData.ContentBean.DataBean> pListData, List<String> tempList) {
        mContext = pContext;
        mListData = pListData;
        this.tempList = tempList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.left_bar_adapter_layou, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        NbaData.ContentBean.DataBean bean = mListData.get(position);
        holder.text.setText(bean.getName());
        if (!tempList.get(0).equals(bean.getName())){
            holder.item.setBackgroundColor(ContextCompat.getColor(mContext,R.color.dark_grey));
            holder.cursor.setVisibility(View.GONE);
            holder.text.setTextColor(ContextCompat.getColor(mContext,R.color.grey_first_222222));
        } else {
            holder.item.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
            holder.cursor.setVisibility(View.VISIBLE);
            holder.text.setTextColor(ContextCompat.getColor(mContext,R.color.app_theme_E45040));
        }
        if (mOnRecyclerItemClick != null){
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRecyclerItemClick.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cursor)
        View cursor;
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.item)
        RelativeLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
