package io.github.froger.instamaterial.ui.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import io.github.froger.instamaterial.R;

public class Followers extends ListActivity{

    String[] itemname ={
            "Safari",
            "Camera",
            "Global",
            "FireFox",
            "UC Browser",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followers_listview);

        this.setListAdapter(new ArrayAdapter<String>(
                this, R.layout.list_followers,
                R.id.Itemname,itemname));
    }
}
