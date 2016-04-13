package io.github.froger.instamaterial.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.froger.instamaterial.R;
import io.github.froger.instamaterial.ui.activity.MainActivity;
import io.github.froger.instamaterial.ui.activity.QuestionActivityLast;
import io.github.froger.instamaterial.ui.parse_backend.Question;
import io.github.froger.instamaterial.ui.parse_backend.QuestionOption;
import io.github.froger.instamaterial.ui.utils.CircleTransformation;
import io.github.froger.instamaterial.ui.view.LoadingFeedItemView;

/**
 * Created by ankit on 17/3/16.
 */
public class QuestionAdapterLast extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int QUESTION_DESCRIPTION_CARD = 0;
    public static final int QUESTION_OPTION = 1;
    public static final int QUESTION_STATS = 2;
    public static final String ARG_REVEAL_START_LOCATION = "reveal_start_location";
    private static int a = 0;
    private Context context;

    public QuestionAdapterLast(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == QUESTION_DESCRIPTION_CARD) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_description, parent, false);
            QuestionDescription cvQuestionDescription = new QuestionDescription(view);
            return cvQuestionDescription;
        }
        else if(viewType == QUESTION_OPTION){
            View view = LayoutInflater.from(context).inflate(R.layout.item_question_option,parent,false);
            QuestionOption cvQuestionOption = new QuestionOption(view);
            return cvQuestionOption;
        }
        else if(viewType == QUESTION_STATS){
            View view = LayoutInflater.from(context).inflate(R.layout.item_chart,parent,false);
            QuestionChart questionChart = new QuestionChart(view);
            return questionChart;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        System.out.print(getItemViewType(position));
        if (getItemViewType(position) == QUESTION_DESCRIPTION_CARD) {
            ((QuestionDescription) viewHolder).bindView(context);
        } else if (getItemViewType(position) == QUESTION_OPTION) {
            ((QuestionOption) viewHolder).bindView();
        }
        else if(getItemViewType(position) == QUESTION_STATS){
            ((QuestionChart)viewHolder).bindView();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return QUESTION_DESCRIPTION_CARD;
        }
        else if(position > 0  && position <= 4){
            return QUESTION_OPTION;
        }
        else{
            return QUESTION_STATS;
        }
    }
    public static class QuestionChart extends RecyclerView.ViewHolder{
        @Bind(R.id.pieChart)
        PieChart mChart;
        private float[] ydata = {5,10,15,30};
        private String[] xdata = {"Option 1","Option 2","Option 3","Option 4"};

        public QuestionChart(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
        public void bindView(){

            mChart.setUsePercentValues(true);
            mChart.setDescription("Sample Chart");
            mChart.setHoleColor(Color.parseColor("#FFFFFF"));
            mChart.setHoleRadius(20);
            mChart.setTransparentCircleRadius(10);
            mChart.setRotationAngle(0);
            mChart.setRotationEnabled(true);

            ArrayList<Entry> yVals1 = new ArrayList<Entry>();

            for (int i = 0; i < ydata.length; i++)
                yVals1.add(new Entry(ydata[i], i));

            ArrayList<String> xVals = new ArrayList<String>();

            for (int i = 0; i < xdata.length; i++)
                xVals.add(xdata[i]);

            PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
            dataSet.setSliceSpace(3);
            dataSet.setSelectionShift(5);

            ArrayList<Integer> colors = new ArrayList<Integer>();

            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);
            PieData data = new PieData(xVals, dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(11f);
            data.setValueTextColor(Color.GRAY);
            mChart.setData(data);
            mChart.highlightValues(null);
            mChart.invalidate();
        }
    }
    public static class QuestionOption extends RecyclerView.ViewHolder{

        @Bind(R.id.tvOptionNew)
        TextView tvOptionNew;
        @Bind(R.id.BottomColor)
        View bottomColor;
        @Bind(R.id.setColor)
        View setColor;
        public QuestionOption(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindView(){
            //Random rnd = new Random();
            //int color = Color.argb(255, 0, 0,0);
            //bottomColor.setBackgroundColor(color);
            //setColor.setBackgroundColor(color);
            tvOptionNew.setText("Sample Bind View");
        }
    }

    public static class QuestionDescription extends RecyclerView.ViewHolder {
        @Bind(R.id.ivPhotoUserQuestion)
        ImageView ivPhotoUserQuestion;
        public QuestionDescription(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        public void bindView(Context context) {
            Picasso.with(context)
                    .load(R.drawable.profile)
                    .placeholder(R.drawable.img_circle_placeholder)
                    .resize(context.getResources().getDimensionPixelSize(R.dimen.card_view_avatar_size), context.getResources().getDimensionPixelSize(R.dimen.card_view_avatar_size))
                    .centerCrop()
                    .transform(new CircleTransformation())
                    .into(ivPhotoUserQuestion);

        }
    }
    @Override
    public int getItemCount() {
        return 6;
    }


}
