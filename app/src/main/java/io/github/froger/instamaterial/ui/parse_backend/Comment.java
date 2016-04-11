package io.github.froger.instamaterial.ui.parse_backend;

/**
 * Created by ankit on 21/2/16.
 */
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Comment")
public class Comment extends ParseObject{
    public Comment() {
    }
    public String getComment() {
        return getString("comment");
    }
    public void setComment(String c){
        put("comment",c);
    }
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
}
