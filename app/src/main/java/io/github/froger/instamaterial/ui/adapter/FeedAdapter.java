package io.github.froger.instamaterial.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.activity.MainActivity;
import io.github.froger.instamaterial.ui.utils.CircleTransformation;
import io.github.froger.instamaterial.ui.utils.RoundedTransformation;
import io.github.froger.instamaterial.ui.view.LoadingFeedItemView;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;
    public static final int VIEW_TYPE_DEFAULT_WITHOUT_IMAGE=0;

    private static int a = 0;
    private final List<FeedItem> feedItems = new ArrayList<>();

    private Context context;
    private OnFeedItemClickListener onFeedItemClickListener;

    private boolean showLoadingView = false;
    private int avatarSize;
    @Bind(R.id.ivUserProfileWithoutImage)
    ImageView ivUserProfile;

    public FeedAdapter(Context context) {

        this.context = context;
        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.card_view_avatar_size);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
            CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view);
            setupClickableViews(view, cellFeedViewHolder);
            return cellFeedViewHolder;
        } else if (viewType == VIEW_TYPE_LOADER) {
            LoadingFeedItemView view = new LoadingFeedItemView(context);
            view.setLayoutParams(new LinearLayoutCompat.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            return new LoadingCellFeedViewHolder(view);
        }else if(viewType == VIEW_TYPE_DEFAULT_WITHOUT_IMAGE){

            View view = LayoutInflater.from(context).inflate(R.layout.item_feed_without_image,parent,false);
            CellFeedViewWithoutImageHolder cellFeedViewWithoutImageHolder = new CellFeedViewWithoutImageHolder(view);
            setupClickableViewsWithoutImage(view,cellFeedViewWithoutImageHolder);
            return cellFeedViewWithoutImageHolder;

        }
        return null;
    }

    private void setupClickableViewsWithoutImage(final View view, final CellFeedViewWithoutImageHolder cellFeedViewWithoutImageHolder) {
        cellFeedViewWithoutImageHolder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onCommentsClick(view, cellFeedViewWithoutImageHolder.getAdapterPosition());
            }
        });
        cellFeedViewWithoutImageHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onMoreClick(v, cellFeedViewWithoutImageHolder.getAdapterPosition());
            }
        });

        cellFeedViewWithoutImageHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewWithoutImageHolder.getAdapterPosition();
                if(feedItems.get(adapterPosition).isLiked)
                {
                    feedItems.get(adapterPosition).likesCount--;
                    feedItems.get(adapterPosition).isLiked = false;
                }else {
                    feedItems.get(adapterPosition).likesCount++;
                    feedItems.get(adapterPosition).isLiked = true;
                }
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);
                if (context instanceof MainActivity) {
                    ((MainActivity) context).showLikedSnackbar();
                }
            }
        });
        cellFeedViewWithoutImageHolder.ivUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onProfileClick(view);
            }
        });
    }
    private void setupClickableViews(final View view, final CellFeedViewHolder cellFeedViewHolder) {
        cellFeedViewHolder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onCommentsClick(view, cellFeedViewHolder.getAdapterPosition());
            }
        });
        cellFeedViewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onMoreClick(v, cellFeedViewHolder.getAdapterPosition());
            }
        });
        cellFeedViewHolder.ivFeedCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                if(feedItems.get(adapterPosition).isLiked)
                {
                    feedItems.get(adapterPosition).likesCount--;
                    feedItems.get(adapterPosition).isLiked = false;
                }else {
                    feedItems.get(adapterPosition).likesCount++;
                    feedItems.get(adapterPosition).isLiked = true;
                }
                notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);
                if (context instanceof MainActivity) {
                    ((MainActivity) context).showLikedSnackbar();
                }
            }
        });
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                if(feedItems.get(adapterPosition).isLiked)
                {
                    feedItems.get(adapterPosition).likesCount--;
                    feedItems.get(adapterPosition).isLiked = false;
                }else {
                    feedItems.get(adapterPosition).likesCount++;
                    feedItems.get(adapterPosition).isLiked = true;
                }
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);
                if (context instanceof MainActivity) {
                    ((MainActivity) context).showLikedSnackbar();
                }
            }
        });
        cellFeedViewHolder.ivUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFeedItemClickListener.onProfileClick(view);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        System.out.print(getItemViewType(position));
        if (getItemViewType(position) == VIEW_TYPE_DEFAULT) {
        ((CellFeedViewHolder) viewHolder).bindView(feedItems.get(position));
        } else if (getItemViewType(position) == VIEW_TYPE_DEFAULT_WITHOUT_IMAGE) {
            ((CellFeedViewWithoutImageHolder) viewHolder).bindView(feedItems.get(position));

        }
        if (getItemViewType(position) == VIEW_TYPE_LOADER) {
            bindLoadingFeedItem((LoadingCellFeedViewHolder) viewHolder);
        }
    }

    private void bindLoadingFeedItem(final LoadingCellFeedViewHolder holder) {
        holder.loadingFeedItemView.setOnLoadingFinishedListener(new LoadingFeedItemView.OnLoadingFinishedListener() {
            @Override
            public void onLoadingFinished() {
                showLoadingView = false;
                notifyItemChanged(0);
            }
        });
        holder.loadingFeedItemView.startLoading();
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            if(a == 0){
                a = 1;
                return  VIEW_TYPE_DEFAULT;
            }
            else {
                a = 0;
                return VIEW_TYPE_DEFAULT_WITHOUT_IMAGE;

            }

        }
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void updateItems(boolean animated) {
        feedItems.clear();
        feedItems.addAll(Arrays.asList(
                new FeedItem(33, false),
                new FeedItem(1, false),
                new FeedItem(223, false),
                new FeedItem(2, false),
                new FeedItem(6, false),
                new FeedItem(8, false),
                new FeedItem(99, false)
        ));
        if (animated) {
            notifyItemRangeInserted(0, feedItems.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    public void showLoadingView() {
        showLoadingView = true;
        notifyItemChanged(0);
    }


    public static class CellFeedViewWithoutImageHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.btnCommentsWithoutImage)
        ImageButton btnComments;
        @Bind(R.id.btnLikeWithoutImage)
        ImageButton btnLike;
        @Bind(R.id.btnMoreWithoutImage)
        ImageButton btnMore;
        @Bind(R.id.tsLikesCounterWithoutImage)
        TextSwitcher tsLikesCounter;
        @Bind(R.id.ivUserProfileWithoutImage)
        ImageView ivUserProfile;

        FeedItem feedItem;
        public CellFeedViewWithoutImageHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        public void bindView(FeedItem feedItem) {
            this.feedItem = feedItem;
            int adapterPosition = getAdapterPosition();
            btnLike.setImageResource(feedItem.isLiked ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);

        }

        public FeedItem getFeedItem() {
            return feedItem;
        }
    }


    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @Bind(R.id.ivFeedBottom)
        ImageView ivFeedBottom;
        @Bind(R.id.btnComments)
        ImageButton btnComments;
        @Bind(R.id.btnLike)
        ImageButton btnLike;
        @Bind(R.id.btnMore)
        ImageButton btnMore;
        @Bind(R.id.vBgLike)
        View vBgLike;
        @Bind(R.id.ivLike)
        ImageView ivLike;
        @Bind(R.id.tsLikesCounter)
        TextSwitcher tsLikesCounter;
        @Bind(R.id.ivUserProfile)
        ImageView ivUserProfile;
        @Bind(R.id.vImageRoot)
        FrameLayout vImageRoot;

        FeedItem feedItem;

        public CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(FeedItem feedItem) {
            this.feedItem = feedItem;
            int adapterPosition = getAdapterPosition();
            ivFeedCenter.setImageResource(adapterPosition % 2 == 0 ? R.drawable.img_feed_center_1 : R.drawable.img_feed_center_2);
            ivFeedBottom.setImageResource(adapterPosition % 2 == 0 ? R.drawable.img_feed_bottom_1 : R.drawable.img_feed_bottom_2);
            btnLike.setImageResource(feedItem.isLiked ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
            tsLikesCounter.setCurrentText(vImageRoot.getResources().getQuantityString(
                    R.plurals.likes_count, feedItem.likesCount, feedItem.likesCount
            ));
        }

        public FeedItem getFeedItem() {
            return feedItem;
        }
    }

    // This is for new post See in view
    public static class LoadingCellFeedViewHolder extends CellFeedViewHolder {

        LoadingFeedItemView loadingFeedItemView;

        public LoadingCellFeedViewHolder(LoadingFeedItemView view) {
            super(view);
            this.loadingFeedItemView = view;
        }

        @Override
        public void bindView(FeedItem feedItem) {
            super.bindView(feedItem);
        }
    }

    public static class FeedItem {
        public int likesCount;
        public boolean isLiked;

        public FeedItem(int likesCount, boolean isLiked) {
            this.likesCount = likesCount;
            this.isLiked = isLiked;
        }
    }

    public interface OnFeedItemClickListener {
        void onCommentsClick(View v, int position);

        void onMoreClick(View v, int position);

        void onProfileClick(View v);
    }
}
