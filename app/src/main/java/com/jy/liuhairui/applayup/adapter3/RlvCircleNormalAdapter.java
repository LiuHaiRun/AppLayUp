package com.jy.liuhairui.applayup.adapter3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.bean3.CircleNormalData;
import com.jy.liuhairui.applayup.design.RoundImage;
import com.jy.liuhairui.applayup.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class RlvCircleNormalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<CircleNormalData.DataBean.FeedsListBean> mList;
    private String mLabel;

    private final int BANNER = 0;
    private final int IMAGE = 1;
    private final int TEXT = 2;
    private final int VIDEO = 3;
    private final int mPxWidth;


    public RlvCircleNormalAdapter(Context context, List<CircleNormalData.DataBean.FeedsListBean> list, String label) {
        mContext = context;
        mList = list;
        mLabel = label;
        mPxWidth = ScreenUtil.getPxWidth(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        if (viewType == BANNER) {//头部
            return new TopViewHolder(View.inflate(mContext, R.layout.fragment_circle_normal_item_top, null));
        } else if (viewType == IMAGE) {//图片
            return new ImageViewHolder(View.inflate(mContext, R.layout.fragment_circle_normal_item_image, null));
        } else if (viewType == TEXT) {//图片集
            return new TextViewHolder(View.inflate(mContext, R.layout.fragment_circle_normal_item_text, null));
        } else if (viewType == VIDEO) {//视频
            return new VideoViewHolder(View.inflate(mContext, R.layout.fragment_circle_normal_item_video, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == BANNER) {//头部
            initBannerData(holder,position);
        } else if (type == IMAGE) {//图片
            initImage(holder,position);
        } else if (type == TEXT) {//图片集
            initText(holder,position);
        } else if (type == VIDEO) {//视频
            initVideo(holder,position);
        }
    }

    //头部数据
    private void initBannerData(RecyclerView.ViewHolder holder, int position) {
        TopViewHolder holder1 = (TopViewHolder) holder;

        CircleNormalData.DataBean.FeedsListBean feedsListBean = mList.get(position);
        if (feedsListBean != null){
            //获取Banner外层数据
            CircleNormalData.DataBean.TopicBannerBean topic_banner = feedsListBean.topic_banner;
            //获取Banner里层数据
            List<CircleNormalData.DataBean.TopicBannerBean.TopicListBean> topic_list = topic_banner.getTopic_list();
            //头像+文本
            Glide.with(mContext).load(topic_banner.getIcon()).centerCrop().into(holder1.logoImage);
            holder1.bannerText.setText(topic_banner.getTitle());
            //布局管理器
            LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder1.bannerRecyclerView.setLayoutManager(manager);
            RlvNormalItemBannerAdapter adapter = new RlvNormalItemBannerAdapter(mContext, topic_list);
            holder1.bannerRecyclerView.setAdapter(adapter);
        }

    }

    //视频数据
    private void initVideo(RecyclerView.ViewHolder holder, int position) {
        VideoViewHolder viewHolder = (VideoViewHolder) holder;
        //
        commonContent(viewHolder, position);
        if (mList.get(position).getVideo_info() == null) return;
        //获取视频的宽高按比例缩放
        CircleNormalData.DataBean.FeedsListBean.VideoInfoBean video_info = mList.get(position).getVideo_info();
        int height = Integer.parseInt(video_info.getHeight());
        int width = Integer.parseInt(video_info.getWidth());
        int resultPercent = height * viewHolder.videoPlayer.widthRatio / width;
        viewHolder.videoPlayer.heightRatio = resultPercent < viewHolder.videoPlayer.widthRatio ? resultPercent : viewHolder.videoPlayer.widthRatio;
        viewHolder.videoPlayer.backButton.setVisibility(View.GONE);
        viewHolder.videoPlayer.tinyBackImageView.setVisibility(View.GONE);
        viewHolder.videoPlayer.setUp(video_info.getUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST);
        Glide.with(mContext).load(video_info.getThumb()).centerCrop().into(viewHolder.videoPlayer.thumbImageView);
    }

    //图片集数据
    private void initText(RecyclerView.ViewHolder holder, int position) {
        TextViewHolder viewHolder = (TextViewHolder) holder;
        //
        commonContent(viewHolder, position);
        //获取布局管理器，设置网格布局并绑定到recyclerView
        GridLayoutManager manager;
        if (mList.get(position).getAttachments_total() > 2) {
            manager = new GridLayoutManager(mContext, 3);
        } else {
            manager = new GridLayoutManager(mContext, 2);
        }
        viewHolder.recyclerView.setLayoutManager(manager);
        List<CircleNormalData.DataBean.FeedsListBean.AttachmentsBean> attachments = mList.get(position).getAttachments();
        //创建适配器并绑定
        RlvNormalItemImagesAdapter adapter = new RlvNormalItemImagesAdapter(mContext, attachments);
        viewHolder.recyclerView.setAdapter(adapter);
    }

    //图片数据
    private void initImage(RecyclerView.ViewHolder holder,int position) {
        ImageViewHolder viewHolder = (ImageViewHolder) holder;
        CircleNormalData.DataBean.FeedsListBean feedsListBean = mList.get(position);
        //
        commonContent(viewHolder, position);
        //获取数据中规定的宽高按比例缩放
        if (feedsListBean.getAttachments() != null && feedsListBean.getAttachments().size() != 0) {
            CircleNormalData.DataBean.FeedsListBean.AttachmentsBean attachmentsBean = feedsListBean.getAttachments().get(0);
            int imageWitdh = Integer.parseInt(attachmentsBean.getWidth());
            int imageHeight = Integer.parseInt(attachmentsBean.getHeight());
            if (imageWitdh > mPxWidth) {
                imageHeight = mPxWidth * imageHeight / imageWitdh;
                imageWitdh = mPxWidth;
            }
            if (imageHeight >= imageWitdh) {
                imageHeight = imageHeight * 2 / 3;
                imageWitdh = imageWitdh * 2 / 3;
            }
            Glide.with(mContext)
                    .load(attachmentsBean.getUrl())
                    .override(imageWitdh, imageHeight)
                    .centerCrop()
                    .into(viewHolder.onlyImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = mList.get(position).getThread_type();
        if (mLabel.equals("视频")) return VIDEO;//视频
        if (position == 0) return BANNER; //头数据
        else if (type != null && type.equals("video")) return VIDEO;//视频
        else if (type != null && type.equals("image")) return IMAGE;//图片
        else return TEXT;//图片集
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    //头布局的
    class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.banner_text)
        TextView bannerText;
        @BindView(R.id.banner_recyclerView)
        RecyclerView bannerRecyclerView;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //视频
    class VideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.follow)
        TextView follow;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.second_logo)
        ImageView secondLogo;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.center_rl)
        RelativeLayout centerRl;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;
        @BindView(R.id.bottom_tab_text)
        TextView bottomTabText;
        @BindView(R.id.share_image)
        ImageView shareImage;
        @BindView(R.id.share_rl)
        RelativeLayout shareRl;
        @BindView(R.id.comment_image)
        ImageView commentImage;
        @BindView(R.id.comment_rl)
        RelativeLayout commentRl;
        @BindView(R.id.thumbs_image)
        ImageView thumbsImage;
        @BindView(R.id.thumbs_rl)
        RelativeLayout thumbsRl;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //图片集
    class TextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.follow)
        TextView follow;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.second_logo)
        ImageView secondLogo;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.center_rl)
        RelativeLayout centerRl;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.bottom_tab_text)
        TextView bottomTabText;
        @BindView(R.id.share_image)
        ImageView shareImage;
        @BindView(R.id.share_rl)
        RelativeLayout shareRl;
        @BindView(R.id.comment_image)
        ImageView commentImage;
        @BindView(R.id.comment_rl)
        RelativeLayout commentRl;
        @BindView(R.id.thumbs_image)
        ImageView thumbsImage;
        @BindView(R.id.thumbs_rl)
        RelativeLayout thumbsRl;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //图片
    class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logo_image)
        RoundImage logoImage;
        @BindView(R.id.follow)
        TextView follow;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.second_logo)
        ImageView secondLogo;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.center_rl)
        RelativeLayout centerRl;
        @BindView(R.id.title_des)
        TextView titleDes;
        @BindView(R.id.only_image)
        ImageView onlyImage;
        @BindView(R.id.bottom_tab_text)
        TextView bottomTabText;
        @BindView(R.id.share_image)
        ImageView shareImage;
        @BindView(R.id.share_rl)
        RelativeLayout shareRl;
        @BindView(R.id.comment_image)
        ImageView commentImage;
        @BindView(R.id.comment_rl)
        RelativeLayout commentRl;
        @BindView(R.id.thumbs_image)
        ImageView thumbsImage;
        @BindView(R.id.thumbs_rl)
        RelativeLayout thumbsRl;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private void commonContent(RecyclerView.ViewHolder pHolder, int pPosition) {
        //图片集
        if (pHolder instanceof TextViewHolder) {
            TextViewHolder holder = (TextViewHolder) pHolder;
            if (mList.get(pPosition).getAuthor() != null) {
                CircleNormalData.DataBean.FeedsListBean.AuthorBean author = mList.get(pPosition).getAuthor();
                Glide.with(mContext).load(author.getAvatar()).into(holder.logoImage);
                holder.nick.setText(author.getUsername());
                if (author.getPendant() != null && author.getPendant().size() != 0) {
                    Glide.with(mContext).load(author.getPendant().get(0).getUrl())
                            .override(author.getPendant().get(0).getWidth(), author.getPendant().get(0).getHeight())
                            .into(holder.secondLogo);
                    if (author.getPendant().size() > 1) {
                        Glide.with(mContext).load(author.getPendant().get(1).getUrl())
                                .override(author.getPendant().get(1).getWidth(), author.getPendant().get(1).getHeight())
                                .into(holder.vip);
                    }
                }
                holder.time.setText(mList.get(pPosition).getCreated_at().substring(5, mList.get(pPosition).getCreated_at().length() - 3));
                String content = mList.get(pPosition).getContent();
                holder.titleDes.setText(content/*.contains("[") ? EmojiUtils.replaceEmojiTextView(content.trim(),12):content*/);
            }
            if (mList.get(pPosition).getTopic() != null && !TextUtils.isEmpty(mList.get(pPosition).getTopic().getContent())) {
                holder.bottomTabText.setVisibility(View.VISIBLE);
                holder.bottomTabText.setText(mList.get(pPosition).getTopic().getContent());
            } else {
                holder.bottomTabText.setVisibility(View.GONE);
            }
            //图片
        } else if (pHolder instanceof ImageViewHolder) {
            ImageViewHolder holder = (ImageViewHolder) pHolder;
            if (mList.get(pPosition).getAuthor() != null) {
                CircleNormalData.DataBean.FeedsListBean.AuthorBean author = mList.get(pPosition).getAuthor();
                Glide.with(mContext).load(author.getAvatar()).into(holder.logoImage);
                holder.nick.setText(author.getUsername());
                if (author.getPendant() != null && author.getPendant().size() != 0) {
                    Glide.with(mContext).load(author.getPendant().get(0).getUrl())
                            .override(author.getPendant().get(0).getWidth(), author.getPendant().get(0).getHeight())
                            .into(holder.secondLogo);
                    if (author.getPendant().size() > 1) {
                        Glide.with(mContext).load(author.getPendant().get(1).getUrl())
                                .override(author.getPendant().get(1).getWidth(), author.getPendant().get(1).getHeight())
                                .into(holder.vip);
                    }
                }
                holder.time.setText(mList.get(pPosition).getCreated_at().substring(5, mList.get(pPosition).getCreated_at().length() - 3));
                String content = mList.get(pPosition).getContent();
                holder.titleDes.setText(content/*.contains("[") ? EmojiUtils.replaceEmojiTextView(content.trim(),12):content*/);
            }
            if (mList.get(pPosition).getTopic() != null && !TextUtils.isEmpty(mList.get(pPosition).getTopic().getContent())) {
                holder.bottomTabText.setVisibility(View.VISIBLE);
                holder.bottomTabText.setText(mList.get(pPosition).getTopic().getContent());
            } else {
                holder.bottomTabText.setVisibility(View.GONE);
            }
            //视频
        } else if (pHolder instanceof VideoViewHolder) {
            VideoViewHolder holder = (VideoViewHolder) pHolder;
            if (mList.get(pPosition).getAuthor() != null) {
                CircleNormalData.DataBean.FeedsListBean.AuthorBean author = mList.get(pPosition).getAuthor();
                Glide.with(mContext).load(author.getAvatar()).into(holder.logoImage);
                holder.nick.setText(author.getUsername());
                if (author.getPendant() != null && author.getPendant().size() != 0) {
                    Glide.with(mContext).load(author.getPendant().get(0).getUrl())
                            .override(author.getPendant().get(0).getWidth(), author.getPendant().get(0).getHeight())
                            .into(holder.secondLogo);
                    if (author.getPendant().size() > 1) {
                        Glide.with(mContext).load(author.getPendant().get(1).getUrl())
                                .override(author.getPendant().get(1).getWidth(), author.getPendant().get(1).getHeight())
                                .into(holder.vip);
                    }
                }
                holder.time.setText(mList.get(pPosition).getCreated_at().substring(5, mList.get(pPosition).getCreated_at().length() - 3));
                holder.titleDes.setText(mList.get(pPosition).getContent());
            }
            if (mList.get(pPosition).getTopic() != null && !TextUtils.isEmpty(mList.get(pPosition).getTopic().getContent())) {
                holder.bottomTabText.setVisibility(View.VISIBLE);
                holder.bottomTabText.setText(mList.get(pPosition).getTopic().getContent());
            } else {
                holder.bottomTabText.setVisibility(View.GONE);
            }
        }
    }

}
