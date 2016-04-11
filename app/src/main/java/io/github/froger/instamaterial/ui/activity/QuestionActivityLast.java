package io.github.froger.instamaterial.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import butterknife.Bind;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.adapter.FeedItemAnimator;
import io.github.froger.instamaterial.ui.adapter.QuestionAdapterLast;
import io.github.froger.instamaterial.ui.view.FeedContextMenuManager;

/**
 * Created by ankit on 17/3/16.
 */
public class QuestionActivityLast extends BaseDrawerActivity {
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    @Bind(R.id.rvQuestion)
    RecyclerView rvQuestion;

    private QuestionAdapterLast questionAdapter;

    private boolean pendingIntroAnimation;

    public static void startQuestionFromLocation(int[] startingLocation, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, QuestionActivityLast.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_last);
        setupQuestion();

    }

    private void setupQuestion() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvQuestion.setLayoutManager(linearLayoutManager);

        questionAdapter = new QuestionAdapterLast(this);
        rvQuestion.setAdapter(questionAdapter);
        rvQuestion.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvQuestion.setItemAnimator(new FeedItemAnimator());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
        }
        return true;
    }

}
