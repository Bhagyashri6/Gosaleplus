package com.chandrakant.abc.crm_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

public class DistributorInfo extends AppCompatActivity {
    ViewPager viewPager;

    TabLayout tabLayout;
    PiePageAdapter piePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributor_info);

    viewPager =(ViewPager)findViewById(R.id.activity_distributor_Viewpager);
        tabLayout = (TabLayout) findViewById(R.id.pietabLayout);


       piePageAdapter =new PiePageAdapter(getSupportFragmentManager());

piePageAdapter.addFragments(new PieFragmentOne(),"Current Month");
piePageAdapter.addFragments(new PieFragmentTwo(),"Last Month");
        viewPager.setAdapter(piePageAdapter);



        tabLayout.setupWithViewPager(viewPager);













    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_outlet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {


            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
        }


        if (id == R.id.logout) {
            ListModel.list.clear();
            maneDialog();


        }
        if(id== android.R.id.home) {
            ListModel.list1.clear();
            ListModel.list2.clear();
            ListModel.list3.clear();
            ListModel.list4.clear();
            ListModel.list5.clear();
            ListModel.list6.clear();
            ListModel.list7.clear();
            ListModel.listback1.clear();
            ListModel.listback2.clear();
            ListModel.listback3.clear();
            ListModel.listback4.clear();
            ListModel.listback5.clear();
            ListModel.listback6.clear();
            ListModel.listback7.clear();
            ListModel.list.clear();
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void maneDialog() {

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
                        SharedPreferences loginPref = getSharedPreferences("loginprefs", MODE_PRIVATE);
                        SharedPreferences.Editor loginEditor = loginPref.edit();
                        loginEditor.remove("logged");
                        loginEditor.commit();


                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeText("Cancel").onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Log.d("MaterialStyledDialogs", "Do something!");
                        dialog.dismiss();

                    }
                })
                .show();

    }

    @Override
    public void onBackPressed() {

    }
}
