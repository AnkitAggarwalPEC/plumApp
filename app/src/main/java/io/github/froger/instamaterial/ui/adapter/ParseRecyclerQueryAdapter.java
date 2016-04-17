package io.github.froger.instamaterial.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 17/4/16.
 */
public abstract class ParseRecyclerQueryAdapter <T extends ParseObject,U extends RecyclerView.ViewHolder> extends  RecyclerView.Adapter<U> {

    private final QueryFactory<T> parseQuery;
    private final boolean hasStableIds;
    private final List<T> items;
    private final List<OnDataSetChangedListener> mDataSetListeners;
    private final List<OnQueryLoadListener<T>> mQueryListeners;

    public ParseRecyclerQueryAdapter(final QueryFactory<T> parseQuery,final  boolean hasStableIds){
        this.parseQuery = parseQuery;
        this.hasStableIds = hasStableIds;
        items = new ArrayList<>();
        mQueryListeners = new ArrayList<OnQueryLoadListener<T>>();
        mDataSetListeners = new ArrayList<OnDataSetChangedListener>();
    }
    public ParseRecyclerQueryAdapter(final String className, final boolean hasStableIds) {
        this(new QueryFactory<T>() {

            @Override public ParseQuery<T> create() {
                return ParseQuery.getQuery(className);
            }
        }, hasStableIds);
    }
    public ParseRecyclerQueryAdapter(final Class<T> clazz, final boolean hasStableIds) {
        this(new QueryFactory<T>() {

            @Override public ParseQuery<T> create() {
                return ParseQuery.getQuery(clazz);
            }
        }, hasStableIds);
    }
    @Override
    public long getItemId(int position) {
        if (hasStableIds) {
            return position;
        }
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public T getItem(int position) { return items.get(position); }

    public List<T> getItems() { return items; }

    protected void onFilterQuery(ParseQuery<T> query) {
        // provide override for filtering query
    }
    private void dispatchOnLoading() {
        for (OnQueryLoadListener<T> l : mQueryListeners) {
            l.onLoading();
        }
    }
    private void dispatchOnLoaded(List<T> objects, com.parse.ParseException e) {
        for (OnQueryLoadListener<T> l : mQueryListeners) {
            l.onLoaded(objects, e);
        }
    }
    protected void fireOnDataSetChanged() {
        for (int i = 0; i < mDataSetListeners.size(); i++) {
            mDataSetListeners.get(i).onDataSetChanged();
        }
    }
    public void removeOnDataSetChangedListener(OnDataSetChangedListener listener) {
        if (mDataSetListeners.contains(listener)) {
            mDataSetListeners.remove(listener);
        }
    }
    public void addOnDataSetChangedListener(OnDataSetChangedListener listener) {
        mDataSetListeners.add(listener);
    }
    public interface OnDataSetChangedListener {
        public void onDataSetChanged();
    }
    public interface OnQueryLoadListener<T> {

        public void onLoaded(
                List<T> objects, Exception e);

        public void onLoading();
    }
    public void addOnQueryLoadListener(
            OnQueryLoadListener<T> listener) {
        if (!(mQueryListeners.contains(listener))) {
            mQueryListeners.add(listener);
        }
    }
    public void removeOnQueryLoadListener(
            OnQueryLoadListener<T> listener) {
        if (mQueryListeners.contains(listener)) {
            mQueryListeners.remove(listener);
        }
    }
    public void loadObjects() {
        dispatchOnLoading();
        final ParseQuery<T> query = parseQuery.create();
        onFilterQuery(query);
        query.findInBackground(new FindCallback<T>() {;

            @Override public void done(
                    List<T> queriedItems,
                    @Nullable com.parse.ParseException e) {
                if (e == null) {
                    items.clear();
                    items.addAll(queriedItems);
                    dispatchOnLoaded(queriedItems, e);
                    notifyDataSetChanged();
                    fireOnDataSetChanged();
                }
            }
        });
    }


}
