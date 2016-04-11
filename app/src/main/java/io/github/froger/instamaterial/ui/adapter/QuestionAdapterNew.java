package io.github.froger.instamaterial.ui.adapter;

/**
 * Created by ankit on 9/2/16.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.Utils;
public class QuestionAdapterNew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private int itemsCount = 0;
    private int lastAnimatedPosition = -1;
    private boolean lockedAnimations = false;
    private boolean delayEnterAnimation = true;
    public QuestionAdapterNew(Context context) {
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_question_feed, parent, false);
        return new OptionViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        OptionViewHolder viewholder = (OptionViewHolder)holder;
        switch (position%2){
            case 0:
                viewholder.tvOption.setText("Option A ?");
                break;
            case 1:
                viewholder.tvOption.setText("Option B ?");
                break;
        }
    }
    @Override
    public int getItemCount() {
        return 7;
    }
    static class OptionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvOption)
        TextView tvOption;
        public OptionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void setLockedAnimations(boolean lockedAnimations) {
        this.lockedAnimations = lockedAnimations;
    }
    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }
    private void runEnterAnimation(View view, int position) {
        if (lockedAnimations) return;
        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(100);
            view.setAlpha(0.f);
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * (position) : 0)
                    .setInterpolator(new DecelerateInterpolator(2.f))
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            lockedAnimations = true;
                        }
                    })
                    .start();
        }
    }
}
