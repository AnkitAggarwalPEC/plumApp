package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ankit on 21/2/16.
 */
@ParseClassName("Nomination")
public class Nomination extends ParseObject {
    public Nomination(){}
    public void setToUser(ParseUser user){
        put("toUser",user);
    }
    public ParseUser getToUser(){
        return getParseUser("toUser");
    }
    public void setFromUser(ParseUser user){
        put("fromUser",user);
    }
    public ParseUser getFromUser(){
        return getParseUser("fromUser");
    }

    public void setGroup(ParseUser group){ put("group",group);}
    public ParseUser getGroup(){ return getParseUser("group");}
    public void setQuestion(ParseObject question){
        put("question",ParseObject.createWithoutData("Question",question.getObjectId()));
    }
    public Question getQuestion(){
        return (Question)getParseObject("question");
    }

    public String getQuestionId(){
        return getParseObject("question").getObjectId();
    }

}
