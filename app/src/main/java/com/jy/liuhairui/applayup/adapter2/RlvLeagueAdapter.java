package com.jy.liuhairui.applayup.adapter2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean2.LeagueData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvLeagueAdapter extends RecyclerView.Adapter<RlvLeagueAdapter.ViewHolder> {


    private Context mContext;
    private List<LeagueData.ListBean> mDataList;
    private String mLabel;

    public RlvLeagueAdapter(Context context, List<LeagueData.ListBean> dataList, String label) {
        mContext = context;
        mDataList = dataList;
        mLabel = label;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View view = View.inflate(mContext, R.layout.fragment_macth_league_layout_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //
        LeagueData.ListBean listBean = mDataList.get(position);

        if (!mLabel.equals("NBA")){
            Glide.with(mContext).load(listBean.getTeam_A_logo()).into(holder.logo);
            Glide.with(mContext).load(listBean.getTeam_B_logo()).into(holder.logoRight);
            //
            holder.name.setText(listBean.getTeam_A_name());
            holder.nameRight.setText(listBean.getTeam_B_name());
            //分割字符串获取时间
            String start_play = listBean.getStart_play();
            String[] strings = start_play.split(" ");
            String string = strings[1];
            String[] split = string.split(":");
            holder.title1.setText(split[0]+":"+split[1]+" "+listBean.getMatch_title());
            holder.title2.setText(listBean.getFs_A()+"-"+listBean.getFs_B());
            holder.title3.setText("已结束");
        }else {
            Glide.with(mContext).load(listBean.getTeam_A_logo()).into(holder.logo);
            Glide.with(mContext).load(listBean.getTeam_B_logo()).into(holder.logoRight);
            //
            holder.name.setText(listBean.getTeam_A_name()+"("+listBean.getPlayoff_fs_A()+")");
            holder.nameRight.setText(listBean.getTeam_B_name()+"("+listBean.getPlayoff_fs_B()+")");
            //分割字符串获取时间
            String start_play = listBean.getStart_play();
            String[] strings = start_play.split(" ");
            String string = strings[1];
            String[] split = string.split(":");
            holder.title1.setText(split[0]+":"+split[1]+" "+listBean.getMatch_title());
            holder.title2.setText(listBean.getFs_A()+"-"+listBean.getFs_B());
            holder.title3.setText("已结束");
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.logo)
        ImageView logo;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.title1)
        TextView title1;
        @BindView(R.id.title2)
        TextView title2;
        @BindView(R.id.title3)
        TextView title3;
        @BindView(R.id.logo_right)
        ImageView logoRight;
        @BindView(R.id.name_right)
        TextView nameRight;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
