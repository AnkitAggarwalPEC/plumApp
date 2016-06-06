package io.github.froger.instamaterial.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.adapter.UserProfileAdapter;
import io.github.froger.instamaterial.ui.adapter.ViewPagerAdapter;
import io.github.froger.instamaterial.ui.utils.CircleTransformation;
import io.github.froger.instamaterial.ui.view.RevealBackgroundView;

public class UserProfileActivity extends BaseDrawerActivity {

    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";

    private static final int USER_OPTIONS_ANIMATION_DELAY = 300;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();

    @Bind(R.id.vRevealBackground)
    RevealBackgroundView vRevealBackground;
    /*@Bind(R.id.rvUserProfile)
    RecyclerView rvUserProfile;*/


    /*@Bind(R.id.tabLayout)
    TabLayout tabLayout;*/

    @Bind(R.id.ivUserProfilePhoto)
    ImageView ivUserProfilePhoto;
    @Bind(R.id.vUserDetails)
    View vUserDetails;
    @Bind(R.id.btnFollow)
    Button btnFollow;
    @Bind(R.id.vUserStats)
    View vUserStats;
    @Bind(R.id.vUserProfileRoot)
    View vUserProfileRoot;
    @Bind(R.id.Layout1)
    LinearLayout tvfollowers;
    @Bind(R.id.Layout2)
    LinearLayout tvfollowing;

    private int avatarSize;
    private String profilePhoto;
    private UserProfileAdapter userPhotosAdapter;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public static void startUserProfileFromLocation(int[] startingLocation, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, UserProfileActivity.class);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
        startingActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        /*IconData[] data = new IconData[] {
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert),
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert),
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert),
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert),
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert),
                new IconData("Delete", android.R.drawable.ic_delete),
                new IconData("Alert", android.R.drawable.ic_dialog_alert)
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvUserProfile);
        IconAdapter adapter = new IconAdapter(data);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/

        this.avatarSize = getResources().getDimensionPixelSize(R.dimen.user_profile_avatar_size);
        this.profilePhoto = getString(R.string.user_profile_photo);

        Picasso.with(this)
                .load(profilePhoto)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivUserProfilePhoto);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        final TabLayout.Tab questions = tabLayout.newTab();
        final TabLayout.Tab answered = tabLayout.newTab();

        questions.setText("Questions");
        answered.setText("Answered");

        tabLayout.addTab(questions, 0);
        tabLayout.addTab(answered, 1);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tvfollowers.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        followers(v);
                    }
                }
        );
        tvfollowing.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        following(v);
                    }
                }
        );
        btnFollow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit(v);
                    }
                }
        );

    }
    public void followers(View v) {
        Intent i = new Intent(this,Followers.class);
        this.startActivity(i);
    }
    public void following(View v) {
        Intent i = new Intent(this,Following.class);
        this.startActivity(i);
    }
    public void edit(View v) {
        Intent i = new Intent(this,EditProfileActivity.class);
        this.startActivity(i);
    }
}
