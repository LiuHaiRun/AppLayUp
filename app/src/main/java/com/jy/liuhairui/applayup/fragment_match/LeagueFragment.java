package com.jy.liuhairui.applayup.fragment_match;



import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter2.RlvLeagueAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean2.LeagueData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.jy.liuhairui.applayup.utils.TimeChangeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeagueFragment extends BaseMvpFragment<NavigationViewModel> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private String mApi;
    private LeagueData mLeagueData;
    private List<LeagueData.ListBean> mDataList;
    private RlvLeagueAdapter mAdapter;
    private String mType;
    private String mLabel;
    private int mMyid;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_league;
    }

    @Override
    public void initView() {
        mApi = getArguments().getString("api");
        mType = getArguments().getString("type");
        mLabel = getArguments().getString("label");
        String id = getArguments().getString("id");
        mMyid = Integer.parseInt(id);
        //绑定刷新
        initRecycleView(recyclerView,smartrefreshlayout);
        mDataList = new ArrayList<>();
        //布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //适配器
        mAdapter = new RlvLeagueAdapter(getContext(), mDataList,mLabel);
        recyclerView.setAdapter(mAdapter);
        /**
         * 实现粘性头部
         */
        NormalDecoration normalDecoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                //获取头数据
                String prevDate = mDataList.get(i).getStart_play();
                String[] strings = prevDate.split(" ");
                String tag = strings[0];

                return tag;
            }
        };
        //使用头标题来设置分割线
        recyclerView.addItemDecoration(normalDecoration);
        normalDecoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(int i) {
                View view = View.inflate(getContext(), R.layout.fragment_macth_league_layout_titile, null);
                TextView tv_title = view.findViewById(R.id.tv_title);

                String prevDate = mDataList.get(i).getStart_play();
                String[] strings = prevDate.split(" ");
                String tag = strings[0];
                tv_title.setText(tag+" "+TimeChangeUtils.dayForWeek(tag));
                return view;
            }
        });
    }
    //下拉刷新
    @Override
    public void refresh() {
        super.refresh();
          if (mLeagueData == null)return;
        mPresenter.getData(ApiConfig.MATCH_TAB_LEAGUE12,mApi,AppConstants.REFRESH_LOAD);

    }

    //上拉加载更多
    @Override
    public void loadMore() {
        super.loadMore();
        if (mLeagueData == null)return;
        mPresenter.getData(ApiConfig.MATCH_TAB_LEAGUE12,mApi,AppConstants.MORE_LOAD);
    }

    @Override
    public void initData() {
        if (mApi != null){
            mPresenter.getData(ApiConfig.MATCH_TAB_LEAGUE12,mApi,AppConstants.NORMAL_LOAD);
        }
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG2","LeagueFragment失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.MATCH_TAB_LEAGUE12:
                mLeagueData = (LeagueData) t[0];
                Logger.logD("TAG2","LeagueFragment成功："+mLeagueData.toString());
                //
                int loadType = getLoadType(t);
                if (loadType == AppConstants.MORE_LOAD){
                    smartrefreshlayout.finishLoadMore();
                } else if (loadType == AppConstants.REFRESH_LOAD){
                    mDataList.clear();
                    smartrefreshlayout.finishRefresh();
                }
                mDataList.addAll(mLeagueData.getList());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

}
