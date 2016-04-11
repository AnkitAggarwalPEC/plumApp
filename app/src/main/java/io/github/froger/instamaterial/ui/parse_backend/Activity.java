package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ankit on 21/2/16.
 */
@ParseClassName("Activity")
public class Activity extends ParseObject {
    public Activity() {
    }

    public ParseUser getFromUser() {
        return getParseUser("fromUser");
    }

    public void setFromUser(ParseUser user) {
        put("fromUser", user);
    }

    public ParseUser getToUser() {
        return getParseUser("toUser");
    }

    public void setToUser(ParseUser user) {
        put("toUser", user);
    }

    public String getType() {
        return getString("type");
    }
    public void setType(String t){
        put("type",t);
    }
    public void setContent(String t){
        put("content",t);
    }
    public void setQuestion(ParseObject question){
        put("question",ParseObject.createWithoutData("Question",question.getObjectId()));
    }
    public String getQuestionId(){
        return getParseObject("Question").getObjectId();
    }
    public Question getQuestionObject() {
        return (Question)getParseObject("Question");
    }
}