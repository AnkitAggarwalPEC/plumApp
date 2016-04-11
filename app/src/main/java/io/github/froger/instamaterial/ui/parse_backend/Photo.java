package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ankit on 21/2/16.
 */

@ParseClassName("Photo")
public class Photo extends ParseObject{


    public Photo(){

    }
    public ParseFile getImage(){
        return getParseFile("image");
    }
    public void setImage(ParseFile file){
        put("image",file);
    }
    public ParseUser getUser(){
        return getParseUser("user");
    }
    public void setUser(ParseUser user){
        put("user",user);
    }
    public ParseFile getThumbnail(){
        return getParseFile("thumbnail");
    }
    public void setThumbnail(ParseFile file){
        put("thumbnail",file);
    }
}
