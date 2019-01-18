package com.example.shobhitverma.ars;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.example.shobhitverma.ars.Registration.Activity_Registration;
import com.example.shobhitverma.ars.Search.Activity_AddRoom;
import com.example.shobhitverma.ars.Search.Activity_GetLocation;
import com.google.firebase.auth.FirebaseAuth;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class MainActivity extends AwesomeSplash {
    private  FirebaseAuth mAuth;
    private static int splashTimeOut=4000;
    @Override
    public void initSplash(ConfigSplash configSplash) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         //Backgroud Animation
        mAuth=FirebaseAuth.getInstance();
        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);
        //Logo

        configSplash.setLogoSplash(R.drawable.mobile);
        configSplash.setAnimLogoSplashDuration(3000);
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);

        //title
        configSplash.setTitleSplash("SEARCH ROOMS");
        configSplash.setTitleTextSize(20f);
        configSplash.setTitleTextColor(R.color.strokeColor);
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.DropOut);
    }

    @Override
    public void animationsFinished() {
        if(mAuth.getCurrentUser()!=null)
        {
            Toast.makeText(MainActivity.this,"Already Login.." + mAuth.getCurrentUser().getPhoneNumber(),Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this,Activity_GetLocation.class));
            //startActivity(new Intent(MainActivity.this,Activity_AddRoom.class));
            finish(); }
         else {
           // startActivity(new Intent(MainActivity.this, Activity_Registration.class));
            startActivity(new Intent(MainActivity.this,Activity_GetLocation.class));
            finish();
        }
    }
}
