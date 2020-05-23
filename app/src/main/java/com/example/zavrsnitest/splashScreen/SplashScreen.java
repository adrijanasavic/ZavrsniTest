package com.example.zavrsnitest.splashScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.example.zavrsnitest.MainActivity;
import com.example.zavrsnitest.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    Animation topAnim, bottomAnim;

    private SharedPreferences prefs;
    private String splashTime;
    private boolean splash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        hideSystemUI();

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);



        prefs = PreferenceManager.getDefaultSharedPreferences( this );

        splashTime = prefs.getString( getString( R.string.splashtime_key ), "3000" );

        splash = prefs.getBoolean( getString( R.string.splash_key ), true );

        if (splash) {
            setContentView( R.layout.splash_screen );

            ImageView imageView = findViewById( R.id.imageSplash );
            TextView splash_txt = findViewById( R.id.textView );

            InputStream is;
//            imageView.animate().rotation( 1800 ).alpha( 0 ).setDuration( 10000 );
//            imageView.animate().scaleX( 0.5f ).scaleY( 0.5f ).setDuration( 3000 );
////            imageView.animate().translationXBy( 1000 ).rotation( 3600 ).setDuration( 3000 );

            try {
                is = getAssets().open( "android_pink.jpg" );
                Drawable drawable = Drawable.createFromStream( is, null );
                imageView.setImageDrawable( drawable );

                imageView.setAnimation(topAnim);
                splash_txt.setAnimation(bottomAnim);
            } catch (IOException e) {
                e.printStackTrace();
            }

            new Timer().schedule( new TimerTask() {
                @Override
                public void run() {
                    startActivity( new Intent( SplashScreen.this, MainActivity.class ) );
                    finish();
                }
            }, Integer.parseInt( splashTime ) );
        } else {
            startActivity( new Intent( SplashScreen.this, MainActivity.class ) );
            finish();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}



