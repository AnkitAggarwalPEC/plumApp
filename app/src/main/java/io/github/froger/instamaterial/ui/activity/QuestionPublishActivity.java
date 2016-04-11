package io.github.froger.instamaterial.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.Utils;
import io.github.froger.instamaterial.ui.adapter.CommentsAdapter;
import io.github.froger.instamaterial.ui.adapter.PublishAdapter;
import io.github.froger.instamaterial.ui.view.SendCommentButton;

public class QuestionPublishActivity extends BaseDrawerActivity implements SendCommentButton.OnSendClickListener{

    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    @Bind(R.id.rvAddOptions)
    RecyclerView rvAddOptions;
    @Bind(R.id.btnAddOption)
    SendCommentButton btnAddOption;

    private CommentsAdapter commentsAdapter;
    private int drawingStartLocation;
    private PublishAdapter publishAdapter;

    public static void startPostingFromLocation(int[] startingLocation, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, QuestionPublishActivity.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        setupOptions();
        setupSendCommentButton();
        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);

    }

    private void setupOptions() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvAddOptions.setLayoutManager(linearLayoutManager);
        rvAddOptions.setHasFixedSize(true);
        publishAdapter = new PublishAdapter(this);
        rvAddOptions.setAdapter(publishAdapter);

        rvAddOptions.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rvAddOptions.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    publishAdapter.setAnimationsLocked(true);
                }
            }
        });
    }
    private void setupSendCommentButton() {
        btnAddOption.setOnSendClickListener(this);
    }

    @Override
    public void onBackPressed() {
        ViewCompat.setElevation(getToolbar(), 0);
    }
    public void onSendClickListener(View v) {
        publishAdapter.addItem();
        publishAdapter.setAnimationsLocked(false);
        publishAdapter.setDelayEnterAnimation(false);
        rvAddOptions.smoothScrollBy(0,  publishAdapter.getItemCount());
        btnAddOption.setCurrentState(SendCommentButton.STATE_DONE);
    }
}
