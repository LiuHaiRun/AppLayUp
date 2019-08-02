package com.jy.liuhairui.applayup.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean3.CircleNormalData;

import java.util.List;

/*
 *图片集的适配器
 **/
public class RlvNormalItemImagesAdapter extends RecyclerView.Adapter<RlvNormalItemImagesAdapter.ViewHolder> {

    private Context mContext;
    List<CircleNormalData.DataBean.FeedsListBean.AttachmentsBean> attachments;

    public RlvNormalItemImagesAdapter(Context context, List<CircleNormalData.DataBean.FeedsListBean.AttachmentsBean> attachments) {
        mContext = context;
        this.attachments = attachments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.fragment_circle_item_images_adapter_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CircleNormalData.DataBean.FeedsListBean.AttachmentsBean attachmentsBean = attachments.get(position);
        Glide.with(mContext).load(attachmentsBean.getUrl()).centerCrop().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return attachments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
