package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ankit on 21/2/16.
 */
@ParseClassName("Like")
public class Like extends ParseObject {

    public Like(){}
    public void setAuthor(ParseUser user){
        put("author",user);
    }
    public ParseUser getAuthor(){
        return getParseUser("author");
    }
    public void setQuestion(ParseObject question){
        put("question",ParseObject.createWithoutData("Question",question.getObjectId()));
    }
    public Question getQuestion(){
        return (Question)getParseObject("question");
    }
    public String getQuestionId(){
        return getParseObject("question").getObjectId();
    }
    public void setLikedBy(ParseUser user){put("likedBy",user);}
    public ParseUser getLikedBy(){return getParseUser("likedBy");}

}
