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
import com.jy.liuhairui.applayup.bean.NavigationViewData;

import java.util.ArrayList;

public class RlvNavigationViewAdapter extends RecyclerView.Adapter<RlvNavigationViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<NavigationViewData.ModulesBean> mModulesBeans;

    public RlvNavigationViewAdapter(Context context, ArrayList<NavigationViewData.ModulesBean> modulesBeans) {
        mContext = context;
        mModulesBeans = modulesBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View inflate = View.inflate(mContext, R.layout.fragment_navigationview_rlv_item_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       //加载数据
        NavigationViewData.ModulesBean modulesBean = mModulesBeans.get(position);
        Glide.with(mContext)
                .load(modulesBean.getImage())
                .into(holder.img);

        holder.tv.setText(modulesBean.getName());
        //
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnItem.OnItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModulesBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img);
            tv=itemView.findViewById(R.id.tv);
        }
    }


    //
    private OnItem mOnItem;

    public void setOnItem(OnItem onItem) {
        mOnItem = onItem;
    }

    public  interface OnItem{
        void OnItemClick(View view,int position);
    }
}
