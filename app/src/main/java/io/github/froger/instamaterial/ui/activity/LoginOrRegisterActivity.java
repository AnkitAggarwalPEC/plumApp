package io.github.froger.instamaterial.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import io.github.froger.instamaterial.ui.parse_backend.Activity;
import io.github.froger.instamaterial.ui.parse_backend.Category;
import io.github.froger.instamaterial.ui.parse_backend.Comment;
import io.github.froger.instamaterial.ui.parse_backend.Like;
import io.github.froger.instamaterial.ui.parse_backend.Photo;
import io.github.froger.instamaterial.ui.parse_backend.Question;
import io.github.froger.instamaterial.ui.parse_backend.QuestionOption;
import io.github.froger.instamaterial.ui.parse_backend.Tags;

/**
 * Created by ankit on 13/4/16.
 */
public class LoginOrRegisterActivity extends AppCompatActivity{

    //Implementing WHELTHER FIRST RUN OR NOT
    SharedPreferences preferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("com.plum",MODE_PRIVATE);
        ParseObject.registerSubclass(Activity.class);
        ParseObject.registerSubclass(Comment.class);
        ParseObject.registerSubclass(Like.class);
        ParseObject.registerSubclass(Photo.class);
        ParseObject.registerSubclass(Question.class);
        ParseObject.registerSubclass(QuestionOption.class);
        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(Tags.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(preferences.getBoolean("FIRST_TIME" , true)){
            // launched for first time
            preferences.edit().putBoolean("FIRST_TIME",false).commit();
        }
        else{
            if(currentUser != null){
                //load the main activity
            }
        }

    }


}
