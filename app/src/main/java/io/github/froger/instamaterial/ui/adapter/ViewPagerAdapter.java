package io.github.froger.instamaterial.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.github.froger.instamaterial.ui.activity.TabFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment questions = new TabFragment();
                return questions;
            case 1:
                TabFragment answered = new TabFragment();
                return answered;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;           // As there are only 3 Tabs
    }
}
