package com.jy.liuhairui.applayup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean.FavTeamEntity;
import com.jy.liuhairui.applayup.bean.MatchData;
import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.design.RoundImage;
import com.jy.liuhairui.applayup.design.RoundOrCircleImage;
import com.jy.liuhairui.applayup.frame.OnRecyclerItemClick;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RlvNormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<NormalData.ArticlesBean> mRlvList;
    private List<NormalData.RecommendBean> mBannList;
    private List<MatchData> mMatchList;
    private String mLabel;


    private final int BANNER = 1;
    private final int NORMAL = 3;
    private final int VIDEO = 4;
    private final int FAVOR = 5;

    public RlvNormalAdapter(Context context,
                            List<NormalData.ArticlesBean> rlvList,
                            List<NormalData.RecommendBean> bannList,
                            List<MatchData> matchList, String label) {
        mContext = context;
        mRlvList = rlvList;
        mBannList = bannList;
        mMatchList = matchList;
        mLabel = label;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        //加载布局
        if (viewType == NORMAL)//文本
            return new ViewHolder3(View.inflate(mContext, R.layout.fragment_homt_item_normal_layout, null));
        if (viewType == VIDEO)//视频
            return new ViewHolder2(View.inflate(mContext, R.layout.fragment_homt_item_video_layout, null));
        if (viewType == BANNER)//banner
            return new BanViewHolder(View.inflate(mContext, R.layout.fragment_homt_item_banner_layout, null));
        if (viewType == FAVOR)//关注
            return new ViewHolder1(View.inflate(mContext, R.layout.fragment_homt_item_guanzhu_layout, null));
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //加载数据
        int type = getItemViewType(position);
        if (type == NORMAL) {
            //1.文本
            setNormalData(holder, position);
        } else if (type == VIDEO) {
            //2.视频
            setVideoData(holder, position);
        } else if (type == BANNER) {
            //3.banner
            setBandata(holder, position);
        } else if (type == FAVOR) {
            //4.关注
            setFavorData(holder, position);
        }

    }

    //normal数据
    private void setNormalData(RecyclerView.ViewHolder holder, int position) {
        int pos = mLabel.equals("头条") ? position - 1 : position;
        ViewHolder3 viewHolder3 = (ViewHolder3) holder;
        NormalData.ArticlesBean articlesBean = mRlvList.get(pos);

        Glide.with(mContext).load(articlesBean.getThumb()).into(viewHolder3.rightImage);
        viewHolder3.title.setText(articlesBean.getTitle());
        if (articlesBean.isTop()) viewHolder3.isTop.setVisibility(View.VISIBLE);
        else viewHolder3.isTop.setVisibility(View.GONE);
        viewHolder3.isDeep.setVisibility(TextUtils.isEmpty(articlesBean.getLabel()) ? View.GONE : View.VISIBLE);
        viewHolder3.comment.setText(articlesBean.getComments_total() + "评论");
        //调用接口
        onItemClick(viewHolder3.item,position);
    }

    private void onItemClick(View item, final int position) {
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用接口
                if (mClick != null){
                    if (mLabel.equals("头条"))mClick.onItemClick(position-1);
                    else mClick.onItemClick(position);
                }
            }
        });
    }

    //关注
    private void setFavorData(RecyclerView.ViewHolder holder, int position) {
        int colume = 1;
        int pos = position - 1;
        //
        FavTeamEntity favTeamEntity = mRlvList.get(pos).mFavTeamEntity;
        //
        ViewHolder1 viewHolder1 = (ViewHolder1) holder;
        if (favTeamEntity.getSelected_teams() != null && favTeamEntity.getSelected_teams().size() != 0) {
            if (favTeamEntity.getSelected_teams().size() >= 4) {
                colume = 5;
            } else {
                colume = favTeamEntity.getSelected_teams().size() + 1;
            }
        }
        ///关注的球队展示列表
        GridLayoutManager manager = new GridLayoutManager(mContext, colume);
        viewHolder1.favorRecyclerview.setLayoutManager(manager);

        ArrayList<String> selected_teams = favTeamEntity.getSelected_teams();
        ArrayList<FavTeamEntity> data = new ArrayList<>();
        if (selected_teams != null) {
            List<String> strings = new ArrayList<>();
            if (selected_teams.size() > 4) {
                strings = selected_teams.subList(0, 4);
            } else {
                strings.addAll(selected_teams);
            }
            ArrayList<FavTeamEntity> team_list = favTeamEntity.getTeam_list().get(0).getList();
            for (int i = 0; i < team_list.size(); i++) {
                if (strings.contains(team_list.get(i).getId())) {
                    data.add(team_list.get(i));
                }
            }
        }

        FavorNewAdapter adapter = new FavorNewAdapter(mContext, data);
        viewHolder1.favorRecyclerview.setAdapter(adapter);
    }

    //视频
    private void setVideoData(RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        if (mLabel.equals("头条")) {
            pos = position - 1;
        }
        NormalData.ArticlesBean articlesBean = mRlvList.get(pos);
        ViewHolder2 viewHolder2 = (ViewHolder2) holder;
        //饺子播放器对象
        JCVideoPlayerStandard videoPlayer = viewHolder2.videoPlayer;
        videoPlayer.tinyBackImageView.setVisibility(View.GONE);
        videoPlayer.backButton.setVisibility(View.GONE);
        videoPlayer.setUp(articlesBean.getVideo_info().getVideo_src(), JCVideoPlayer.SCREEN_LAYOUT_LIST);
        //
        Glide.with(mContext).load(articlesBean.getCover().getPic()).centerCrop().into(videoPlayer.thumbImageView);
        Glide.with(mContext).load(articlesBean.getThumb()).into(viewHolder2.avarter);
        viewHolder2.title.setText(articlesBean.getTitle());
        viewHolder2.time.setText(articlesBean.getVideo_info().getVideo_time());
    }

    ///Banner数据
    private void setBandata(RecyclerView.ViewHolder holder, int position) {

        BanViewHolder banHolder = (BanViewHolder) holder;
        //bann的背景图片
        Glide.with(mContext).load(R.drawable.banner_bg).centerCrop().into(banHolder.redBgImage);
        //
        banHolder.mybanner.setImages(mBannList)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        NormalData.RecommendBean path1 = (NormalData.RecommendBean) path;
                        Glide.with(mContext).load(path1.getThumb()).into(imageView);
                    }
                })
                .start();
        //比赛数据
        if (mMatchList == null || mMatchList.size() == 0) return;

        MatchData matchData = this.mMatchList.get(0);
        //篮球队的图标
        Glide.with(mContext).load(matchData.getTeam_A_logo()).into(banHolder.logo);
        Glide.with(mContext).load(matchData.getTeam_B_logo()).into(banHolder.logoRight);
        //比分
        banHolder.nameLeft.setText(matchData.getTeam_A_name() + "(" + matchData.getPlayoff_fs_A() + ")");
        banHolder.nameRight.setText(matchData.getTeam_B_name() + "(" + matchData.getPlayoff_fs_B() + ")");
        //总结赛
        banHolder.title1.setText(matchData.getMatch_title());
        //比分
        if (matchData.getStatus().equals("Fixture")) {
            banHolder.title2.setText(matchData.getStart_play());
            banHolder.title2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);
        } else {
            banHolder.title2.setText(matchData.getFs_A() + "-" + matchData.getFs_B());
            banHolder.title2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
        }
        //比赛视频
        if (matchData.getStatus().equals("Playing")) banHolder.title3.setText("直播中");
        else if (matchData.getStatus().equals("Played")) banHolder.title3.setText("已结束");
        else if (matchData.getStatus().equals("Fixture")) banHolder.title3.setText("未开始");
        else banHolder.title3.setText("搞鸡毛");
        banHolder.matchRight.setVisibility(View.GONE);

        if (this.mMatchList.size() <= 1) {
        } else {
            MatchData matchData2 = this.mMatchList.get(1);
            Glide.with(mContext).load(matchData2.getTeam_A_logo()).into(banHolder.logo2);
            Glide.with(mContext).load(matchData2.getTeam_B_logo()).into(banHolder.logoRight2);
            banHolder.titleDes2.setText(matchData2.getMatch_title());
            banHolder.teamName2.setText(matchData2.getTeam_A_name() + "(" + matchData2.getPlayoff_fs_A() + ")");
            banHolder.teamNameRight2.setText(matchData2.getTeam_B_name() + "(" + matchData2.getPlayoff_fs_B() + ")");
            banHolder.score2.setText(matchData2.getFs_A() + " - " + matchData2.getFs_B());

            if (matchData.getStatus().equals("Playing")) banHolder.status2.setText("直播中");
            else if (matchData.getStatus().equals("Played")) banHolder.status2.setText("已结束");
            else if (matchData.getStatus().equals("Fixture")) banHolder.status2.setText("未开始");
            else banHolder.status2.setText("搞鸡毛");
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (mLabel.equals("头条")) {
            if (position == 0) return BANNER;//Banner
            else if (mRlvList.size() > 0 && mRlvList.get(position - 1).isIs_video())
                return VIDEO;//视频
            else if (mRlvList.size() > 0 && mRlvList.get(position - 1).mFavTeamEntity != null)
                return FAVOR;//关注
            else return NORMAL;//normal数据

        } else {
            if (mRlvList != null && mRlvList.size() != 0) {
                if (mRlvList.get(position).isIs_video()) return VIDEO;//视频
                else return NORMAL;//文本
            }
        }
        return NORMAL;//文本
    }

    @Override
    public int getItemCount() {
        return mLabel.equals("头条") ? mRlvList.size() + 1 : mRlvList.size();
    }

    class BanViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.red_bg_image)
        ImageView redBgImage;
        @BindView(R.id.mybanner)
        Banner mybanner;
        @BindView(R.id.logo)
        ImageView logo;
        @BindView(R.id.name_left)
        TextView nameLeft;
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
        @BindView(R.id.logo2)
        ImageView logo2;
        @BindView(R.id.team_name2)
        TextView teamName2;
        @BindView(R.id.title_des2)
        TextView titleDes2;
        @BindView(R.id.score2)
        TextView score2;
        @BindView(R.id.status2)
        TextView status2;
        @BindView(R.id.logo_right2)
        ImageView logoRight2;
        @BindView(R.id.team_name_right2)
        TextView teamNameRight2;
        @BindView(R.id.match_right)
        LinearLayout matchRight;
        @BindView(R.id.center_ll)
        LinearLayout centerLl;

        public BanViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    //关注
    class ViewHolder1 extends RecyclerView.ViewHolder {

        @BindView(R.id.favor_title)
        TextView favorTitle;
        @BindView(R.id.favor_recyclerview)
        RecyclerView favorRecyclerview;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //视频
    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.avarter)
        RoundImage avarter;//
        @BindView(R.id.title)
        TextView title;//
        @BindView(R.id.name)
        TextView name;//
        @BindView(R.id.from)
        TextView from;//
        @BindView(R.id.top_rl)
        RelativeLayout topRl;//
        @BindView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;//
        @BindView(R.id.time)
        TextView time;//
        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //文本
    class ViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.right_image)
        RoundOrCircleImage rightImage;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.is_top)
        TextView isTop;
        @BindView(R.id.is_deep)
        TextView isDeep;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.item)
        RelativeLayout item;

        public ViewHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //---------------------------------------定义接口 -------------------------------------

    public OnRecyclerItemClick  mClick;
    public void setClick(OnRecyclerItemClick click) {
        mClick = click;
    }


}
