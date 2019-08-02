package com.jy.liuhairui.applayup.adapter4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean4.RankingCubaData;

import java.util.List;

/**
 *
 */
public class CUBASortAdapter extends RecyclerView.Adapter<CUBASortAdapter.ViewHolder> {

    private List<RankingCubaData.ContentBeanX.RoundsBean.ContentBean.DataBeanX> mCubaData;
    private Context mContext;

    public CUBASortAdapter(List<RankingCubaData.ContentBeanX.RoundsBean.ContentBean.DataBeanX> pCubaData, Context pContext) {
        mCubaData = pCubaData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cuba_sort_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("睚眦",position+"");
        holder.mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        holder.mRecyclerView.setLayoutManager(manager);
        RankingCubaData.ContentBeanX.RoundsBean.ContentBean.DataBeanX dataBeanX = mCubaData.get(position);
        //
        CubaSortSonAdapter adapter = new CubaSortSonAdapter(dataBeanX,mContext);
        holder.mRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mCubaData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
