package com.chandrakant.abc.crm_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.io.File;

public class NewOU extends AppCompatActivity {

    ImageView ICapture;
    CollapsingToolbarLayout toolbar_layout;
    ImageButton back,custom_home;
    Button save;
    private EditText outletname,outletaddress,area,city,state,pincode,owenername,contactno;
    private String soutletname,soutletaddress,sarea,scity,sstate,spincode,sowenername,scontactno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ou);


        toolbar_layout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
       
        ICapture=(ImageView)findViewById(R.id.ICapture);

        outletname=(EditText)findViewById(R.id.outletname);
        outletaddress=(EditText)findViewById(R.id.outletaddress);
        area=(EditText)findViewById(R.id.area);
        city=(EditText)findViewById(R.id.city);
        state=(EditText)findViewById(R.id.state);
        pincode=(EditText)findViewById(R.id.pincode);
        owenername=(EditText)findViewById(R.id.owenername);
        contactno=(EditText)findViewById(R.id.contactno);
        save=(Button) findViewById(R.id.savemyoutlet);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file=getFile();

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent,1);
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soutletname=outletname.getText().toString();
                soutletaddress=outletaddress.getText().toString();
                sarea=area.getText().toString();
                scity=city.getText().toString();
                sstate=state.getText().toString();
                spincode=pincode.getText().toString();
                sowenername=owenername.getText().toString();
                scontactno=contactno.getText().toString();



                if (soutletname.equals("")||soutletaddress.equals("") ||sarea.equals("") ||scity.equals("") ||sstate.equals("") ||spincode.equals("") ||sowenername.equals("")||scontactno.equals(""))
                {

                    maneDialog();
                }





            }
        });


       /* back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this,MainActivity3.class));
            }
        });
*/
       /* custom_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScrollingActivity.this,MainActivity3.class));
            }
        });*/
    }
    private File getFile()
    {
        File folder=new File("sdcard/camera_app");

        if(!folder.exists())
        {
            folder.mkdir();
        }

        File imageFile= new File(folder,"cam_image.jpg");
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path="sdcard/camera_app/cam_image.jpg";
        ICapture.setImageDrawable(Drawable.createFromPath(path));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_outlet,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.home)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
        }


        if (id==R.id.logout)
        {
            maneDialoglogout();


        }
        return super.onOptionsItemSelected(item);
    }

    private void maneDialoglogout() {

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


    private void maneDialog()
    {

        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                .setTitle("Alert!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setDescription("Please Enter Aall Field. \n")
                .withIconAnimation(true)
                .setPositiveText("Ok")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }})
                .show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
