package io.github.froger.instamaterial.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.LinkedHashMap;

import butterknife.Bind;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.adapter.QuestionAdapterNew;
import io.github.froger.instamaterial.ui.adapter.UserProfileAdapter;
import io.github.froger.instamaterial.ui.utils.CircleTransformation;
import io.github.froger.instamaterial.ui.view.RevealBackgroundView;

/**
 * Created by ankit on 8/2/16.  
 */
public class QuestionActivityNew extends BaseDrawerActivity {

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    private static final int USER_OPTIONS_ANIMATION_DELAY = 300;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

   // @Bind(R.id.vRevealBackgroundQuestion)
   // RevealBackgroundView vRevealBackgroundQuestion;
    @Bind(R.id.rvOption)
    RecyclerView rvOption;

    @Bind(R.id.ivPhotoUserQuestion)
    ImageView ivPhotoUserQueston;
    @Bind(R.id.vImageAndQuestion)
    View vImageAndQuestion;
    @Bind(R.id.vlikeCommentQuestion)
    View vlikeCommentQuestion;
    private int avatarSize;
    private String profilePhoto;
    private QuestionAdapterNew questionAdapterNew;
    public static void startUserProfileFromLocation(int[] startingLocation, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, QuestionActivityNew.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_simple);
        this.avatarSize = getResources().getDimensionPixelSize(R.dimen.question_user_photo_size);
        this.profilePhoto = getString(R.string.user_profile_photo);

        Picasso.with(this)
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivPhotoUserQueston);

        setUpOptions();
       // setupRevealBackground(savedInstanceState);
    }

    private void setUpOptions()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvOption.setLayoutManager(linearLayoutManager);
        rvOption.setHasFixedSize(true);
        questionAdapterNew = new QuestionAdapterNew(this);
        rvOption.setAdapter(questionAdapterNew);
        //rvOption.setOverScrollMode(View.OVER_SCROLL_NEVER);
        /*rvOption.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    questionAdapterNew.setLockedAnimations(true);
                }
            }
        });*/

    }

    /*private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackgroundQuestion.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            vRevealBackgroundQuestion.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackgroundQuestion.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackgroundQuestion.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackgroundQuestion.setToFinishedFrame();
            questionAdapterNew.setLockedAnimations(true);
        }
    }
    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            rvOption.setVisibility(View.VISIBLE);
            vImageAndQuestion.setVisibility(View.VISIBLE);
            questionAdapterNew = new QuestionAdapterNew(this);
            rvOption.setAdapter(questionAdapterNew);
            animateUserProfileHeader();
        } else {
            rvOption.setVisibility(View.INVISIBLE);
            vImageAndQuestion.setVisibility(View.INVISIBLE);
        }

    }
    private void animateUserProfileHeader() {
        vImageAndQuestion.setTranslationY(-vImageAndQuestion.getHeight());
        ivPhotoUserQueston.setTranslationY(-ivPhotoUserQueston.getHeight());
        vlikeCommentQuestion.setTranslationY(-vlikeCommentQuestion.getHeight());

        vImageAndQuestion.animate().translationY(0).setDuration(300).setInterpolator(INTERPOLATOR);
        ivPhotoUserQueston.animate().translationY(0).setDuration(300).setStartDelay(100).setInterpolator(INTERPOLATOR);
        vlikeCommentQuestion.animate().translationY(0).setDuration(300).setStartDelay(200).setInterpolator(INTERPOLATOR);
    }*/
}
