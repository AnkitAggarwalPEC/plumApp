package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 21/2/16.
 */
@ParseClassName("QuestionOption")
public class QuestionOption extends ParseObject{
    public QuestionOption(){
    }
    public void setQuestion(ParseObject question){
        put("question",ParseObject.createWithoutData("Question",question.getObjectId()));
    }
    public Question getQuestion(){
        return (Question)getParseObject("question");
    }
    public void setOptions(ArrayList<String> options) {
        put("options",options);
    }
    public ArrayList<String> getOptions(){
        List<String> l = getList("options");
        return new ArrayList<String>(l);
    }
    public void setType(String type){
        put("type",type);
    }
    public String getType(){
        return getString("type");
    }
    public void setStats(ArrayList<Integer> stats) {
        put("stats",stats);
    }
    public ArrayList<Integer> getStats(){
        List<Integer> l = getList("stats");
        return new ArrayList<Integer>(l);
    }
}
