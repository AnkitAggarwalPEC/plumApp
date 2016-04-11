package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Question")
public class Question extends ParseObject{

    public Question(){}
    public void setAuthor(ParseUser user){
        put("author",user);
    }
    public ParseUser getAuthor(){
        return getParseUser("author");
    }
    public void setQuestionName(String name){
        put("questionName",name);
    }
    public String getQuestionName(){
        return getString("questionName");
    }
    public void setDescription(String description){
        put("description",description);
    }
    public String getDescription(){
        return getString("description");
    }
    public void setCommentNumber(int t){
        put("comment",t);
    }
    public int getCommentNumber(){
        return getInt("comment");
    }
    public ParseFile getImage(){
        return getParseFile("image");
    }
    public void setImage(ParseFile file){
        put("image",file);
    }
    public void setLikeNumber(int t){
        put("likes",t);
    }
    public int getLikeNumber(){
        return getInt("likes");
    }
    public void setVotesNumber(int t){
        put("votes",t);
    }
    public int getVotesNumber(){
        return getInt("votes");
    }
    public void setTag(String tag){
        put("tag",tag);
    }
    public String getTag(){
        return getString("tag");
    }
}
