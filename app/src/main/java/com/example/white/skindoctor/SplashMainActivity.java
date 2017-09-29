package com.example.white.skindoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashMainActivity extends AppCompatActivity {
    private ImageView splashView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);
        splashView = (ImageView) findViewById(R.id.Splash_Logo);
        Animation myAnimation = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        splashView.startAnimation(myAnimation);

        final Intent intent = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
