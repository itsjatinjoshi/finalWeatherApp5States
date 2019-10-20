package com.example.weatherAppFiveStates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    LinearLayout l1,l2,logo,logo2;

   // Button btnsub;
    Animation uptodown,downtoup, lefttoright, righttoleft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        logo = (LinearLayout) findViewById(R.id.logo);
        logo2 = (LinearLayout) findViewById(R.id.logo2);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lefttoright = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft = AnimationUtils.loadAnimation(this,R.anim.righttoleft);

        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);
        logo.setAnimation(lefttoright);
        logo2.setAnimation(righttoleft);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        },10000);}

}
