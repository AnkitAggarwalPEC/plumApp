package io.github.froger.instamaterial.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.utils.RoundedTransformation;
import io.github.froger.instamaterial.ui.view.SendCommentButton;

/**
 * Created by ankit on 25/3/16.
 */
public class PublishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static boolean ADDING_NEW_OPTION = false;
    private Context context;
    private int itemsCount = 0;
    private int avatarSize;

    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;

    public PublishAdapter(Context context) {
        this.context = context;
        avatarSize = context.getResources().getDimensionPixelSize(R.dimen.comment_avatar_size);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_publish_option, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        OptionViewHolder holder = (OptionViewHolder) viewHolder;
        /*if (ADDING_NEW_OPTION) {
            holder.tvOption.setText("+ Add Text Over Here");
            holder.ivOpenCamera.setVisibility(View.VISIBLE);
            Picasso.with(context)
                .load(R.drawable.ic_launcher)
                .centerCrop()
                .resize(avatarSize, avatarSize)
                .transform(new RoundedTransformation())
                .into(holder.ivOpenCamera);
            ADDING_NEW_OPTION = false;
        } else {*/
            switch (position % 3) {
                case 0:
                    holder.tvOption.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
                    break;
                case 1:
                    holder.tvOption.setText("Cupcake ipsum dolor sit amet bear claw.");
                    break;
                case 2:
                    holder.tvOption.setText("Cupcake ipsum dolor sit. Amet gingerbread cupcake. Gummies ice cream dessert icing marzipan apple pie dessert sugar plum.");
                    break;
            }


    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public void updateItems() {
        itemsCount = 2;

        notifyDataSetChanged();
    }

    public void addItem() {
        itemsCount++;
        ADDING_NEW_OPTION = true;
        notifyItemInserted(itemsCount - 1);
    }

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }

    public static class OptionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivOpenCamera)
        ImageView ivOpenCamera;
        @Bind(R.id.tvOption)
        TextView tvOption;

        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
