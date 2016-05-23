package io.github.froger.instamaterial.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import io.github.froger.instamaterial.ui.parse_backend.Question;

/**
 * Created by ankit on 17/4/16.
 */
public class FeedAdapterParse extends ParseRecyclerQueryAdapter<Question , FeedAdapter.CellFeedViewHolder> {

    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static boolean addNewQuestion = false;
    public static final int IMAGE_AND_TEXT_QUESTION = 0;
    public static final int TEXT_QUESTION = 1;
    public static final int ADD_NEW_QUESTION = 2;

    FeedAdapterParse(boolean  hasStableIds) {
        super(Question.class,hasStableIds);
    }

    @Override
    public void onBindViewHolder(FeedAdapter.CellFeedViewHolder holder, int position) {
        Question model = getItem(position);

    }
    @Override
    public FeedAdapter.CellFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == IMAGE_AND_TEXT_QUESTION){

        }
        else if(viewType == TEXT_QUESTION){

        }
        else if(viewType == ADD_NEW_QUESTION){

        }
        return null;
    }
    public static class CardFeedViewHolder extends RecyclerView.ViewHolder {

        public CardFeedViewHolder(View v){
            super(v);
        }
        public void bindView(int type){

        }
    }
}
