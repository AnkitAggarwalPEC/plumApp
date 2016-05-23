package io.github.froger.instamaterial.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.parse_backend.Question;
import io.github.froger.instamaterial.ui.parse_backend.Basicfunctionality;

/**
 * Created by ankit on 17/4/16.
 */
public class FeedAdapterParse extends ParseRecyclerQueryAdapter<Question , FeedAdapterParse.CardFeedViewHolder> {

    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static boolean addNewQuestion = false;
    public static final int IMAGE_AND_TEXT_QUESTION = 0;
    public static final int TEXT_QUESTION = 1;
    public static final int ADD_NEW_QUESTION = 2;
    private Context context;
    private static  ParseQueryAdapter.QueryFactory<Question> query;
    public  FeedAdapterParse(ParseQueryAdapter.QueryFactory<Question> query,boolean  hasStableIds) {
        super(query,hasStableIds);
        this.query = query;
    }

    @Override
    public void onBindViewHolder(CardFeedViewHolder holder, int position) {
        Question question = getItem(position);

    }

    @Override
    public CardFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == IMAGE_AND_TEXT_QUESTION){
            View view = LayoutInflater.from(context).inflate(R.layout.item_feed,parent,false);
            CardFeedViewHolder cardFeedViewHolder = new CardFeedViewHolder(view);
            setUpClickabeViews(cardFeedViewHolder,viewType);
            return cardFeedViewHolder;
        }
        else if(viewType == TEXT_QUESTION){

        }
        else if(viewType == ADD_NEW_QUESTION){

        }
        return null;
    }

    private void setUpClickabeViews(CardFeedViewHolder view,
                                    int viewType){


    }
    public static class CardFeedViewHolder extends RecyclerView.ViewHolder {
        //First Text Only
        @Bind(R.id.btnCommentsWithoutImage)
        ImageButton btnCommentsWithoutImage;
        @Bind(R.id.btnLikeWithoutImage)
        ImageButton btnLikeWithoutImage;
        @Bind(R.id.btnMoreWithoutImage)
        ImageButton btnMoreWithoutImage;
        @Bind(R.id.tsLikesCounterWithoutImage)
        TextSwitcher tsLikesCounterWithoutImage;
        @Bind(R.id.ivUserProfileWithoutImage)
        ImageView ivUserProfileWithoutImage;
        @Bind(R.id.questionTittleWithoutImage)
        TextView questionTittleWithoutImage;
        @Bind(R.id.descriptionWithoutImage)
        TextView descriptionWithoutImage;

        //Now Image + Text
        @Bind(R.id.description)
        TextView description;
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

        public CardFeedViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }
        //Question Contents -->
        /*
            Author - Parse User
            QuestionName - String
            QuestionDescription --String
            Number of Comments -- int
            Question Image -- Parse File
            Like Number - int
            Votes Number - int
            tag -- String
        */

        public void bindView(Question question , int type){
            if(type == IMAGE_AND_TEXT_QUESTION){
                //TODO Need to handle images in this
                String desc = question.getDescription();
                if(desc.isEmpty()){
                    description.setVisibility(View.GONE);
                }
                else{
                    description.setText(desc);
                }
                
            }
            else if(type == TEXT_QUESTION){

            }
            else if(type == ADD_NEW_QUESTION){

            }
        }
    }

}
