package com.osharifali.trueshoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.text.DecimalFormat;

public class StatsActivity extends AppCompatActivity {

    double footHeight;
    double footWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale);
        a.reset();

        TextView m = (TextView) findViewById(R.id.meas);
        m.clearAnimation();
        m.startAnimation(a);


        Intent intent = getIntent();
        footHeight = intent.getDoubleExtra("footHeight" , 0);
        footWidth = intent.getDoubleExtra("footWidth", 0);

        DecimalFormat dec = new DecimalFormat("#0.000");

        TextView width = (TextView) findViewById(R.id.fWidth);
        TextView height = (TextView) findViewById(R.id.fHeight);
        width.setText(""+dec.format(footWidth));
        height.setText(""+dec.format(footHeight));
        TextView mSizes = (TextView) findViewById(R.id.mSize);
        TextView wSizes = (TextView) findViewById(R.id.wSize);
        double mSize = 5;
        double wSize = 3;
        // men's size checks
        if (footHeight >= 9.25 && footHeight < 9.5) {
            mSize = 6;
        } else if (footHeight >= 9.5 && footHeight < 9.625) {
            mSize = 6.5;
        } else if (footHeight >= 9.625 && footHeight < 9.75) {
            mSize = 7;
        } else if (footHeight >= 9.75 && footHeight < 9.9575) {
            mSize = 7.5;
        } else if (footHeight >= 9.9575 && footHeight < 10.125) {
            mSize = 8;
        } else if (footHeight >= 10.125 && footHeight < 10.25) {
            mSize = 8.5;
        } else if (footHeight >= 10.25 && footHeight < 10.4575) {
            mSize = 9;
        } else if (footHeight >= 10.4575 && footHeight < 10.5625) {
            mSize = 9.5;
        } else if (footHeight >= 10.5625 && footHeight < 10.75) {
            mSize = 10;
        } else if (footHeight >= 10.75 && footHeight < 10.9365) {
            mSize = 10.5;
        } else if (footHeight >= 10.9365 && footHeight < 11.125) {
            mSize = 11;
        } else if (footHeight >= 11.125 && footHeight < 11.25) {
            mSize = 11.5;
        } else if (footHeight >= 11.25 && footHeight < 11.5626) {
            mSize = 12;
        } else if (footHeight >= 11.5626 && footHeight < 11.875) {
            mSize = 13;
        } else if (footHeight >= 11.875 && footHeight < 12.1875) {
            mSize = 14;
        } else if (footHeight >= 12.1875 && footHeight < 12.5) {
            mSize = 15;
        }  else if (footHeight >= 12.5) {
            mSize = 16;
        }

        
        if (footHeight >= 8.1875 && footHeight < 8.375) {
            wSize = 4;
        } else if (footHeight >= 8.375 && footHeight < 8.5) {
            wSize = 4.5;
        } else if (footHeight >= 8.5 && footHeight < 8.75) {
            wSize = 5;
        } else if (footHeight >= 8.75 && footHeight < 8.875) {
            wSize = 5.5;
        } else if (footHeight >= 8.875 && footHeight < 9.0625) {
            wSize = 6;
        } else if (footHeight >= 9.0625 && footHeight < 9.25) {
            wSize = 6.5;
        } else if (footHeight >= 9.25 && footHeight < 9.375) {
            wSize = 7;
        } else if (footHeight >= 9.375 && footHeight < 9.5) {
            wSize = 7.5;
        } else if (footHeight >= 9.5 && footHeight < 9.6875) {
            wSize = 8;
        } else if (footHeight >= 9.6875 && footHeight < 9.875) {
            wSize = 8.5;
        } else if (footHeight >= 9.875 && footHeight < 10) {
            wSize = 9;
        } else if (footHeight >= 10 && footHeight < 10.1875) {
            wSize = 9.5;
        } else if (footHeight >= 10.1875 && footHeight < 10.3125) {
            wSize = 10;
        } else if (footHeight >= 10.3125 && footHeight < 10.5) {
            wSize = 10.5;
        } else if (footHeight >= 10.5 && footHeight < 10.6875) {
            wSize = 11;
        } else if (footHeight >= 10.6875 && footHeight < 10.87) {
            wSize = 11.5;
        } else if (footHeight >= 10.87) {
            wSize = 12;
        }
        mSizes.setText("Size " + mSize);
        wSizes.setText("Size " + wSize);
    }
}
