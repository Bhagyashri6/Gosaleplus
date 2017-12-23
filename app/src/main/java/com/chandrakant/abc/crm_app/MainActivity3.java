
package com.chandrakant.abc.crm_app;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

public class MainActivity3 extends AppCompatActivity {



    LinearLayout newpjp,newoutlet,newprofile,newperformance;
ImageView gosalespic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

         newpjp=(LinearLayout)findViewById(R.id.newpjp);
        newoutlet=(LinearLayout)findViewById(R.id.newoutlet);
        newprofile=(LinearLayout)findViewById(R.id.newprofile);
        newperformance=(LinearLayout)findViewById(R.id.newperformance);
        gosalespic=(ImageView)findViewById(R.id.gosalespic);


        final ImageView im=(ImageView)findViewById(R.id.gosalespic);
//            final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate_horizontal);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getBaseContext(),R.anim.rotate_horizontal);
        set.setTarget(im);

        set.start();

        newpjp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this,TodaysPJP.class));
            }
        });

        newoutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this,NewOU.class));
            }
        });

        newprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this,MapsActivity.class));
            }
        });

        newperformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this,Performance.class));
            }
        });










       /* recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(MainActivity3.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position==0)
                {
                    Intent intent =new Intent(MainActivity3.this,TodaysPJP.class);
                    startActivity(intent);
                }
                if(position==1)
                {

                    Intent intent =new Intent(MainActivity3.this,NewOU.class);
                    startActivity(intent);
                }
                if(position==2)
                {

                    Intent intent =new Intent(MainActivity3.this,Profile.class);
                    startActivity(intent);
                }
                if(position==3)
                {

                    Intent intent =new Intent(MainActivity3.this,Performance.class);
                    startActivity(intent);
                }
            }
        }));
        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.go_salesplus).into((ImageView) findViewById(R.id.backdrop));

           final ImageView im=(ImageView)findViewById(R.id.backdrop);
//            final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate_horizontal);
          AnimatorSet  set = (AnimatorSet) AnimatorInflater.loadAnimator(getBaseContext(),R.anim.rotate_horizontal);
            set.setTarget(im);

                set.start();
            
           *//* im.startAnimation(an);

            an.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    im.startAnimation(an);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });*//*
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

   /* private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.mapp,
                R.drawable.onlineretail,
                R.drawable.profilee,
                R.drawable.perfmce,
               *//* R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11*//*};

        Album a = new Album("PJP", 13, covers[0]);
        albumList.add(a);

        a = new Album("Outlet", 8, covers[1]);
        albumList.add(a);

        a = new Album("Profile", 11, covers[2]);
        albumList.add(a);

        a = new Album("Performance", 12, covers[3]);
        albumList.add(a);

        *//*a = new Album("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        albumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);
*//*
        adapter.notifyDataSetChanged();
    }

    *//**
     * RecyclerView item decoration - give equal margin around grid item
     *//*
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    *//**
     * Converting dp to pixel
     *//*
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_mainactivity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();


        if (id==R.id.logout)
        {

          /*  AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setMessage("Do yo want Log out?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
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

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.setTitle("Logout..");
            alertDialog.show();*/

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


    private void alertDialg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit.?")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                if(getIntent().getBooleanExtra("EXIT",false)) {


                    finish();
                    finishAffinity();

                }

                else
                {
                    MainActivity3.this.finish();
                    finishAffinity();

                }


            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Option");
        alertDialog.show();
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
                            MainActivity3.this.finish();
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
    @Override
    public void onBackPressed() {

    }
}


