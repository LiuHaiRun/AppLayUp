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
import com.jy.liuhairui.applayup.bean.ClassificationData;

import java.util.ArrayList;

/*
*     网格
* */
public class RlvClassificationAdapter extends RecyclerView.Adapter<RlvClassificationAdapter.ViewHolder> {


    private Context mContext;
    private ArrayList<ClassificationData.ArticlesBean> mClassificationList;

    public RlvClassificationAdapter(Context context, ArrayList<ClassificationData.ArticlesBean> classificationList) {
        mContext = context;
        mClassificationList = classificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.fragment_homt_item_gridding_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //加载数据
        ClassificationData.ArticlesBean articlesBean = mClassificationList.get(position);

        holder.tv.setText(articlesBean.getTitle());

        Glide.with(mContext)
                .load(articlesBean.getThumb())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mClassificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
