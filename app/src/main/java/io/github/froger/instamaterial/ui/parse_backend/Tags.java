package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ankit on 13/4/16.
 */
@ParseClassName("Tags")
public class Tags extends ParseObject {
    public Tags(){}
    public void setCategoty(ParseObject category){
        put("category",ParseObject.createWithoutData("Category",category.getObjectId()));
    }
    public Category getCategoryID(){
        return (Category)getParseObject("category");
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
    public void setTags(String tags){put("name",tags);}
    public String getTags(){return getString("name");}
}
