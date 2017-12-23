package com.chandrakant.abc.crm_app;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.vistrav.ask.Ask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout newpjp,newoutlet,newprofile,newperformance,recordings2;
    ImageView gosalespic;
    TelephonyManager tm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ask.on(this)
                .forPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("Location permission need for map to work properly",
                        "In order to save file you will need to grant storage permission") //optional
                .go();

if(!isNetworkAvailable())
{
    MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
            .setTitle("Alert!")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setDescription("Netwrok Not Availible..\n")
            .withIconAnimation(true)
            .setPositiveText("Retry")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {



                    startActivity(new Intent(Settings.ACTION_SETTINGS));

                }}) .setNegativeText("Close") .onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    Log.d("MaterialStyledDialogs", "Do something!");

                    dialog.dismiss();
                    finishAffinity();
                }})
            .show();
}

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final View headerView = navigationView.getHeaderView(0);

     LinearLayout recordings2 = (LinearLayout)headerView.findViewById(R.id.recordings2);
        recordings2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),CallRecorded.class));
            }
        });
        newpjp=(LinearLayout)findViewById(R.id.newpjp);
        newoutlet=(LinearLayout)findViewById(R.id.newoutlet);
        newprofile=(LinearLayout)findViewById(R.id.newprofile);
        newperformance=(LinearLayout)findViewById(R.id.newperformance);
        gosalespic=(ImageView)findViewById(R.id.gosalespic);







        final ImageView im=(ImageView)findViewById(R.id.gosalespic);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getBaseContext(),R.anim.rotate_horizontal);
        set.setTarget(im);

        set.start();



        newpjp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TodaysPJP.class));
            }
        });

        newoutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewOU.class));
            }
        });

        newprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });

        newperformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Performance.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement






        if (id==R.id.logout)
        {


            MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                    .setTitle("Alert!")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setDescription("Do you want to Logout. \n")
                    .withIconAnimation(true)
                    .setPositiveText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Log.d("MaterialStyledDialogs", "Do something!");
                            SharedPreferences loginPref=getSharedPreferences("loginprefs",MODE_PRIVATE);
                            SharedPreferences.Editor loginEditor=loginPref.edit();
                            loginEditor.remove("logged");
                            loginEditor.commit();


                            Intent  intent=new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                            finish();

                        }}) .setNegativeText("Cancel") .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            Log.d("MaterialStyledDialogs", "Do something!");
                            dialog.dismiss();

                        }})
                    .show();

        }



        if(id==R.id.exit)
        {
            maneDialog();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void maneDialog()
    {

        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                .setTitle("Alert!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setDescription("Do you want to Exit. \n")
                .withIconAnimation(true)
                .setPositiveText("Exit")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.d("MaterialStyledDialogs", "Do something!");
                        if (getIntent().getBooleanExtra("EXIT", false)) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                            finish();
                            finishAffinity();
                        } else {
                            MainActivity.this.finish();
                            finishAffinity();
                        }

                    }}) .setNegativeText("Cancel") .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.d("MaterialStyledDialogs", "Do something!");
                        dialog.dismiss();
                    }})
                .show();

    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }
}
