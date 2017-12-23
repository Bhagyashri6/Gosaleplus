package com.chandrakant.abc.crm_app;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

public class Login extends AppCompatActivity {

    private EditText t1, t2;
    private Button b;
    private CheckBox chk;

    public SharedPreferences loginPref;
    public SharedPreferences.Editor loginEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        t1 = (EditText) findViewById(R.id.editText);
        t2 = (EditText) findViewById(R.id.editText18);
        b = (Button) findViewById(R.id.button);

        chk = (CheckBox) findViewById(R.id.checkBox2);

        loginPref = getSharedPreferences("loginprefs", MODE_PRIVATE);
        loginEditor = loginPref.edit();

        saveLogin = loginPref.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            t1.setText(loginPref.getString("username", ""));
            t2.setText(loginPref.getString("password", ""));
            chk.setChecked(true);
        }

        if (loginPref.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

       /* SharedPreferences pref =getApplicationContext().getSharedPreferences("MyPref",0);
        final SharedPreferences.Editor editor = pref.edit();*/


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = t1.getText().toString();
                String password = t2.getText().toString();

                if (name.length() == 0 || password.length() == 0) {
                    alertBox();
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(t1.getWindowToken(), 0);

                    name = t1.getText().toString();
                    password = t2.getText().toString();


                    if (chk.isChecked()) {
                        loginEditor.putBoolean("saveLogin", true);
                        loginEditor.putString("username", name);
                        loginEditor.putString("password", password);
                        loginEditor.commit();
                    } else {
                        loginEditor.clear();
                        loginEditor.commit();
                    }

                    if (t1.getText().toString().equals("abc") && t2.getText().toString().equals("123")) {
                        //make SharedPreferences object

                        loginEditor.putString("logged", "logged");
                        loginEditor.commit();
                        Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                   /* Intent intent = new Intent(Login.this, MainActivity3.class);
                    startActivity(intent);
                    finish();*/
                }
            }
        });



/*        Typeface myt= Typeface.createFromAsset(getAssets(),"bailey.ttf");
        TextView textView = (TextView)findViewById(R.id.textView16) ;
        textView.setTypeface(myt);*/


    }

    private void alertBox() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Login..");
        builder.setCancelable(false).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();


                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Please enter name & password");
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finishAffinity();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.exit) {
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


                if (getIntent().getBooleanExtra("EXIT", false)) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    finish();
                } else {
                    Login.this.finish();

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
                            Login.this.finish();
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


    }

