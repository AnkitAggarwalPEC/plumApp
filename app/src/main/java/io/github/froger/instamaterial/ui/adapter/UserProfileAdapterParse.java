package io.github.froger.instamaterial.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.Parse;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by ankit on 26/5/16.
 */
public class UserProfileAdapterParse extends ParseRecyclerQueryAdapter<ParseUser,UserProfileAdapter.PhotoViewHolder>{

    public UserProfileAdapterParse(ParseQueryAdapter.QueryFactory<ParseUser> query, boolean hasStableIds) {
        super(query,hasStableIds);

    }
    @Override
    public UserProfileAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserProfileAdapter.PhotoViewHolder holder, int position) {
        return;
    }


}
