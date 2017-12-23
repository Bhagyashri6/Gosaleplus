package com.chandrakant.abc.crm_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

 final ImageView im=(ImageView)findViewById(R.id.imageView4);
        final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.zoom);
        final Animation an2= AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

       /* Typeface myt= Typeface.createFromAsset(getAssets(),"bailey.ttf");
        TextView textView = (TextView)findViewById(R.id.textview1) ;
        textView.setTypeface(myt);
*/
        im.startAnimation(an);

        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                im.startAnimation(an2);

                finish();
                Intent i= new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

       /* final Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                    Intent i= new Intent(SplashScreen.this,Login.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        t1.start();
*/




    }
}
