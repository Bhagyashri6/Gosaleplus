package com.chandrakant.abc.crm_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

//    Bundle b;
    Button infobutton;
    TextView nameText,add1Text,add2Text,pinText,ownerText,contactText;
    Spinner spinText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
/*

        nameText=(TextView)findViewById(R.id.nameText);
        add1Text=(TextView)findViewById(R.id.addText);
        add2Text=(TextView)findViewById(R.id.addText2);
        pinText=(TextView)findViewById(R.id.PinText);
        ownerText=(TextView)findViewById(R.id.OwnerText);
        contactText=(TextView)findViewById(R.id.contactText);
//        spinText=(Spinner)findViewById(R.id.spinnerArea);

*/
        infobutton=(Button)findViewById(R.id.infobutton);
        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,MainActivity3.class));
            }
        });
    }

    @Override
    protected void onStart() {


     /*   b=getIntent().getExtras();
        String NameT=b.getString("name");
        String Add1T=b.getString("add1");
        String Add2T=b.getString("add2");
        String PinT=b.getString("pincode");
        String OwnerT=b.getString("Oname");
        String ContactT=b.getString("contact1");
        String SpinT=b.getString("spin");


        nameText.setText(NameT);
        add1Text.setText(Add1T);
        add2Text.setText(Add2T);
        pinText.setText(PinT);
        ownerText.setText(OwnerT);
        contactText.setText(ContactT);
*/



        super.onStart();
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
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

    }
}
