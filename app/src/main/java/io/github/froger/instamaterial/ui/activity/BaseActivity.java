package io.github.froger.instamaterial.ui.activity;

import android.app.Dialog;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.Bind;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.Utils;
import io.github.froger.instamaterial.ui.adapter.SearchAdapter;

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private MenuItem userProfile;
    private MenuItem notification;
    private MenuItem search;
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }
    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }
    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }
    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        userProfile = menu.findItem(R.id.userProfile);
        userProfile.setActionView(R.layout.menu_item_user_profile);

        View user = userProfile.getActionView();
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int[] startingLocation = new int[2];
                        v.getLocationOnScreen(startingLocation);
                        startingLocation[0] += v.getWidth() / 2;
                        UserProfileActivity.startUserProfileFromLocation(startingLocation, BaseActivity.this);
                        overridePendingTransition(0, 0);
                    }
                }, 200);
            }
        });
        notification = menu.findItem(R.id.notification);
        notification.setActionView(R.layout.menu_item_notification);
        View notf = notification.getActionView();
        notf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(BaseActivity.this, "notification", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        search = menu.findItem(R.id.search);
        search.setActionView(R.layout.menu_item_search);
        View sear = search.getActionView();
        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(BaseActivity.this, "search", Toast.LENGTH_SHORT);
                toast.show();
                loadToolBarSearch();
            }
        });
        return true;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
    public MenuItem getUserProfile() {
        return userProfile;
    }
    public MenuItem getNotification() {
        return notification;
    }
    public MenuItem getSearch() {
        return search;
    }
    public void loadToolBarSearch(){
        ArrayList<String> staticData = new ArrayList<String>(Arrays.asList("abc","ankit"));
        View view = BaseActivity.this.getLayoutInflater().inflate(R.layout.view_toolbar_search,null);
        View imgToolBack = (ImageView)view.findViewById(R.id.img_tool_back);
        final EditText edtToolSearch = (EditText)view.findViewById(R.id.edt_tool_search);
        final ListView listSearch = (ListView)view.findViewById(R.id.list_search);
        TextView txtEmpty = (TextView)view.findViewById(R.id.txt_empty);
        Utils.setListViewHeightBasedOnChildren(listSearch);
        edtToolSearch.setHint("Search");
        final Dialog toolbarSearchDialoh = new Dialog(BaseActivity.this,R.style.MaterialSearch);
        toolbarSearchDialoh.setContentView(view);
        toolbarSearchDialoh.setCancelable(true);
        toolbarSearchDialoh.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        toolbarSearchDialoh.getWindow().setGravity(Gravity.BOTTOM);
        toolbarSearchDialoh.show();
        toolbarSearchDialoh.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        final SearchAdapter searchAdapter = new SearchAdapter(BaseActivity.this,staticData,false);
        listSearch.setVisibility(View.VISIBLE);
        listSearch.setAdapter(searchAdapter);
        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = String.valueOf(adapterView.getItemAtPosition(position));
                edtToolSearch.setText(text);
                listSearch.setVisibility(View.GONE);
            }
        });
        edtToolSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imgToolBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbarSearchDialoh.dismiss();
            }
        });
    }
}
