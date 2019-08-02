package com.jy.liuhairui.applayup.adapter4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean4.TeamPlayeData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *    球员和球队点击左侧展示的右侧数据
 */
public class TeamerResultAdapter extends RecyclerView.Adapter<TeamerResultAdapter.ViewHolder> {

    private Context mContext;
    private List<TeamPlayeData.ContentBean.DataBean> teamerResult;
    private String label;

    public TeamerResultAdapter(Context pContext, List<TeamPlayeData.ContentBean.DataBean> pTeamerResult, String label) {
        mContext = pContext;
        teamerResult = pTeamerResult;
        this.label = label;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.teamer_result_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeamPlayeData.ContentBean.DataBean bean = teamerResult.get(position);
        holder.lineNum.setText(bean.getRank());
        Glide.with(mContext).load(label.equals("球员")?bean.getPerson_logo():bean.getTeam_logo()).into(holder.avarter);
        holder.name.setText(label.equals("球员")?bean.getPerson_name():bean.getTeam_name());

        if (label.equals("球员")){
            holder.team.setText(bean.getTeam_name());
        } else {
            holder.team.setVisibility(View.INVISIBLE);
        }
        holder.data.setText(bean.getValue());
    }

    @Override
    public int getItemCount() {
        return teamerResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.line_num)
        TextView lineNum;
        @BindView(R.id.avarter)
        ImageView avarter;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.team)
        TextView team;
        @BindView(R.id.data)
        TextView data;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
