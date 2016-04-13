package io.github.froger.instamaterial.ui.parse_backend;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ankit on 13/4/16.
 */
@ParseClassName("Category")
public class Category extends ParseObject {
    public Category(){}
    public String getCategory(){
        return getString("name");
    }
    public void setCategory(String category)
    {
       put("name",category);
    }
}
