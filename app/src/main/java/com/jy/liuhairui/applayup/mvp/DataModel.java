package com.jy.liuhairui.applayup.mvp;

import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.api.NetConfig;
import com.jy.liuhairui.applayup.frame.ICommonModel;
import com.jy.liuhairui.applayup.frame.ICommonView;
import com.jy.liuhairui.applayup.frame.NetManager;

public class DataModel implements ICommonModel {
    NetManager manager = NetManager.getNetManager();
    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.GET_SORT_CUBA_DATA: //CUBA排名数据
                String cubaSortUrl = (String) t[1];
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getCUBAResult(cubaSortUrl), view, whichApi, (int) t[0]);
                break;
            case ApiConfig.GET_MATCH_WITH_DAY:   //
            case ApiConfig.MATCH_PROGRESS_INFO:  //
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getProgress((String) t[0]), view, whichApi, 0);
                break;
            case ApiConfig.DATA_FOUR_TAB_OUT: //
                String url = (String) t[0];
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getTabFather(url), view, whichApi, 0);
                break;
            case ApiConfig.GET_PAI_MING_DATA: //排名
                String pmUrl = (String) t[1];
                manager.method(manager.getNetService(NetConfig.DQD_BASE1).getRankInfo(pmUrl), view, whichApi, (int) t[0]);
                break;
            case ApiConfig.TEAMER_RESULT_INFO://球员和球队的右侧数据recyclerView
                manager.method(manager.getNetService().getTeamerResult((String)t[0]),view,whichApi,0);
                break;
            case ApiConfig.NBA_PERSON_DATA://球员和球队的左侧竖的recyclerView
                manager.method(manager.getNetService().getPersonInfo((String) t[0]), view, whichApi, 0);
                break;
        }
    }
}
