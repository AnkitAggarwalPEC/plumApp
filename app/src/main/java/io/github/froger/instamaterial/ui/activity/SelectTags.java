package io.github.froger.instamaterial.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import io.github.froger.instamaterial.R;

/**
 * Created by ankit on 13/4/16.
 */
public class SelectTags extends Fragment implements View.OnClickListener{

    int count = 0,flag1=0,flag2=0,flag3=0,
            flag4=0,flag5=0,flag6=0,
            flag7=0,flag8=0,flag9=0,
            flag10=0,flag11=0,flag12=0,
            flag13=0,flag14=0,flag15=0,flag16=0,flag17=0,
            flag18=0,flag19=0,flag20=0,
            flag21=0,flag22=0,flag23=0,flag24=0;
    Button btnanimals,btnautomobiles,btncelebs,btnbusiness,
            btnentertainment,btnfood,btnhealth,btnlifestyle,
            btnhistory,btnpolitics,btnhobbies,btncomedy,
            btneducation,btnjobs,btnlaw,btnbooks,
            btnrelationships,btnreligion,btnscience,
            btnshopping,btncricket,btnsports,
            btntechnology,btntravel;
    Button loginFacebook;
    TextView textviewsignin;
    View view;
    RelativeLayout signinarea;

    public SelectTags(){
    }
    private void onLoginButtonClicked(){
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Loading...", "Please wait...", true);
        List<String> permission = Arrays.asList("public_profile","user_about_me","user_friends");
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permission, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                            showFeedActivity();
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                            showFeedActivity();
                        }
                    }
                }
        );
    }
    private void showFeedActivity(){
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tags, container, false);
        ImageView img = (ImageView)view.findViewById(R.id.imageView);
        signinarea = (RelativeLayout)view.findViewById(R.id.signinarea);
        img.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.main_back, 400, 400));
        loginFacebook = (Button)view.findViewById(R.id.LoginFacebook);
        loginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOGGING", "Login Button Clicked");
                onLoginButtonClicked();
            }
        });
        ParseUser currentUser = ParseUser.getCurrentUser();
        if((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)){
            showFeedActivity();
        }
        check();
        btnanimals = (Button)view.findViewById(R.id.btnanimals);
        btnautomobiles = (Button)view.findViewById(R.id.btnautomobiles);
        btncelebs = (Button)view.findViewById(R.id.btncelebs);
        btnanimals.setOnClickListener(this);
        btnautomobiles.setOnClickListener(this);
        btncelebs.setOnClickListener(this);

        btnbusiness = (Button)view.findViewById(R.id.btnbusiness);
        btnentertainment = (Button)view.findViewById(R.id.btnentertainment);
        btnfood = (Button)view.findViewById(R.id.btnfood);
        btnbusiness.setOnClickListener(this);
        btnentertainment.setOnClickListener(this);
        btnfood.setOnClickListener(this);

        btnhealth = (Button)view.findViewById(R.id.btnhealth);
        btnlifestyle = (Button)view.findViewById(R.id.btnlifestyle);
        btnhistory = (Button)view.findViewById(R.id.btnhistory);
        btnhealth.setOnClickListener(this);
        btnlifestyle.setOnClickListener(this);
        btnhistory.setOnClickListener(this);

        btnpolitics = (Button)view.findViewById(R.id.btnpolitics);
        btnhobbies = (Button)view.findViewById(R.id.btnhobbies);
        btncomedy = (Button)view.findViewById(R.id.btncomedy);
        btnpolitics.setOnClickListener(this);
        btnhobbies.setOnClickListener(this);
        btncomedy.setOnClickListener(this);

        btneducation = (Button)view.findViewById(R.id.btneducation);
        btnjobs = (Button)view.findViewById(R.id.btnjobs);
        btnlaw = (Button)view.findViewById(R.id.btnlaw);
        btneducation.setOnClickListener(this);
        btnjobs.setOnClickListener(this);
        btnlaw.setOnClickListener(this);

        btnbooks = (Button)view.findViewById(R.id.btnbooks);
        btnrelationships = (Button)view.findViewById(R.id.btnrelationships);
        btnreligion = (Button)view.findViewById(R.id.btnreligion);
        btnbooks.setOnClickListener(this);
        btnrelationships.setOnClickListener(this);
        btnreligion.setOnClickListener(this);

        btnscience = (Button)view.findViewById(R.id.btnscience);
        btnshopping = (Button)view.findViewById(R.id.btnshopping);
        btncricket = (Button)view.findViewById(R.id.btncricket);
        btnscience.setOnClickListener(this);
        btnshopping.setOnClickListener(this);
        btncricket.setOnClickListener(this);

        btnsports = (Button)view.findViewById(R.id.btnsports);
        btntechnology = (Button)view.findViewById(R.id.btntechnology);
        btntravel = (Button)view.findViewById(R.id.btntravel);
        btnsports.setOnClickListener(this);
        btntravel.setOnClickListener(this);
        btntechnology.setOnClickListener(this);
        return view;
    }

    public void check(){
        if(count >=3){
            signinarea.setVisibility(View.VISIBLE);
        }
        else {
            signinarea.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnanimals:
                if(flag1 == 0){
                    btnanimals.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnanimals.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag1=1;
                    check();}
                else
                {
                    btnanimals.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnanimals.setTextColor(Color.WHITE);
                    count--;
                    flag1=0;
                    check();
                }
                break;

            case R.id.btnautomobiles:
                if(flag2 == 0) {
                    btnautomobiles.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnautomobiles.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag2=1;
                    check();

                }
                else
                {
                    btnautomobiles.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnautomobiles.setTextColor(Color.WHITE);
                    count--;
                    flag2=0;
                    check();
                }
                break;

            case R.id.btncelebs:
                if(flag3 == 0) {
                    btncelebs.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btncelebs.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag3=1;
                    check();
                }
                else{
                    btncelebs.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btncelebs.setTextColor(Color.WHITE);
                    count--;
                    flag3=0;
                    check();
                }
                break;
            case R.id.btnbusiness:
                if(flag4 == 0){
                    btnbusiness.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnbusiness.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag4=1;
                    check();}
                else
                {
                    btnbusiness.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnbusiness.setTextColor(Color.WHITE);
                    count--;
                    flag4=0;
                    check();
                }
                break;

            case R.id.btnentertainment:
                if(flag5 == 0) {
                    btnentertainment.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnentertainment.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag5=1;
                    check();

                }
                else
                {
                    btnentertainment.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnentertainment.setTextColor(Color.WHITE);
                    count--;
                    flag5=0;
                    check();
                }
                break;

            case R.id.btnfood:
                if(flag6 == 0) {
                    btnfood.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnfood.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag6=1;
                    check();
                }
                else{
                    btnfood.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnfood.setTextColor(Color.WHITE);
                    count--;
                    flag6=0;
                    check();
                }
                break;

            case R.id.btnhealth:
                if(flag7 == 0){
                    btnhealth.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnhealth.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag7=1;
                    check();}
                else
                {
                    btnhealth.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnhealth.setTextColor(Color.WHITE);
                    count--;
                    flag7=0;
                    check();
                }
                break;

            case R.id.btnlifestyle:
                if(flag8 == 0) {
                    btnlifestyle.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnlifestyle.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag8=1;
                    check();

                }
                else
                {
                    btnlifestyle.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnlifestyle.setTextColor(Color.WHITE);
                    count--;
                    flag8=0;
                    check();
                }
                break;

            case R.id.btnhistory:
                if(flag9 == 0) {
                    btnhistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnhistory.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag9=1;
                    check();
                }
                else{
                    btnhistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnhistory.setTextColor(Color.WHITE);
                    count--;
                    flag9=0;
                    check();
                }
                break;
            case R.id.btnpolitics:
                if(flag10 == 0){
                    btnpolitics.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnpolitics.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag10=1;
                    check();}
                else
                {
                    btnpolitics.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnpolitics.setTextColor(Color.WHITE);
                    count--;
                    flag10=0;
                    check();
                }
                break;

            case R.id.btnhobbies:
                if(flag11 == 0) {
                    btnhobbies.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnhobbies.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag11=1;
                    check();

                }
                else
                {
                    btnhobbies.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnhobbies.setTextColor(Color.WHITE);
                    count--;
                    flag11=0;
                    check();
                }
                break;

            case R.id.btncomedy:
                if(flag12 == 0) {
                    btncomedy.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btncomedy.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag12=1;
                    check();
                }
                else{
                    btncomedy.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btncomedy.setTextColor(Color.WHITE);
                    count--;
                    flag12=0;
                    check();
                }
                break;
            case R.id.btneducation:
                if(flag13 == 0){
                    btneducation.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btneducation.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag13=1;
                    check();}
                else
                {
                    btneducation.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btneducation.setTextColor(Color.WHITE);
                    count--;
                    flag13=0;
                    check();
                }
                break;

            case R.id.btnjobs:
                if(flag14 == 0) {
                    btnjobs.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnjobs.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag14=1;
                    check();

                }
                else
                {
                    btnjobs.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnjobs.setTextColor(Color.WHITE);
                    count--;
                    flag14=0;
                    check();
                }
                break;

            case R.id.btnlaw:
                if(flag15 == 0) {
                    btnlaw.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnlaw.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag15=1;
                    check();
                }
                else{
                    btnlaw.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnlaw.setTextColor(Color.WHITE);
                    count--;
                    flag15=0;
                    check();
                }
                break;
            case R.id.btnbooks:
                if(flag16 == 0){
                    btnbooks.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnbooks.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag16=1;
                    check();}
                else
                {
                    btnbooks.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnbooks.setTextColor(Color.WHITE);
                    count--;
                    flag16=0;
                    check();
                }
                break;

            case R.id.btnrelationships:
                if(flag17 == 0) {
                    btnrelationships.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnrelationships.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag17=1;
                    check();

                }
                else
                {
                    btnrelationships.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnrelationships.setTextColor(Color.WHITE);
                    count--;
                    flag17=0;
                    check();
                }
                break;

            case R.id.btnreligion:
                if(flag18 == 0) {
                    btnreligion.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnreligion.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag18=1;
                    check();
                }
                else{
                    btnreligion.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnreligion.setTextColor(Color.WHITE);
                    count--;
                    flag18=0;
                    check();
                }
                break;

            case R.id.btnscience:
                if(flag19 == 0){
                    btnscience.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnscience.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag19=1;
                    check();}
                else
                {
                    btnscience.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnscience.setTextColor(Color.WHITE);
                    count--;
                    flag19=0;
                    check();
                }
                break;

            case R.id.btnshopping:
                if(flag20 == 0) {
                    btnshopping.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnshopping.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag20=1;
                    check();

                }
                else
                {
                    btnshopping.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnshopping.setTextColor(Color.WHITE);
                    count--;
                    flag20=0;
                    check();
                }
                break;

            case R.id.btncricket:
                if(flag21 == 0) {
                    btncricket.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btncricket.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag21=1;
                    check();
                }
                else{
                    btncricket.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btncricket.setTextColor(Color.WHITE);
                    count--;
                    flag21=0;
                    check();
                }
                break;
            case R.id.btnsports:
                if(flag22 == 0){
                    btnsports.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btnsports.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag22=1;
                    check();}
                else
                {
                    btnsports.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btnsports.setTextColor(Color.WHITE);
                    count--;
                    flag22=0;
                    check();
                }
                break;

            case R.id.btntechnology:
                if(flag23 == 0) {
                    btntechnology.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btntechnology.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag23=1;
                    check();

                }
                else
                {
                    btntechnology.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btntechnology.setTextColor(Color.WHITE);
                    count--;
                    flag23=0;
                    check();
                }
                break;

            case R.id.btntravel:
                if(flag24 == 0) {
                    btntravel.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_selected));
                    btntravel.setTextColor(getResources().getColor(R.color.textcolor));
                    count++;
                    flag24=1;
                    check();
                }
                else{
                    btntravel.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_white));
                    btntravel.setTextColor(Color.WHITE);
                    count--;
                    flag24=0;
                    check();
                }
                break;
            default:
                break;
        }

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }
}

