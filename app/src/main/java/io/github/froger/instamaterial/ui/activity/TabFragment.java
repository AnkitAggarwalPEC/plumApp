package io.github.froger.instamaterial.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.froger.instamaterial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    private LinearLayoutManager mLinearLayoutManager;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tabs, container, false);

        return null;
    }
}
