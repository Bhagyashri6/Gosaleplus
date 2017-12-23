package com.chandrakant.abc.crm_app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.maps.GoogleMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.chandrakant.abc.crm_app.MapsActivity.cityName;


public class Confirm_Order extends AppCompatActivity {


    private TextView t1,totalc;
    String name;
    Button orderbutton;
    ListView listconfirm;
    ConfirmOrderAdapter confirmOrderAdapter;

    AlertDialog dialog;

    private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTIONrr = "http://tempuri.org/orderplaced";
    private static final String METHOD_NAMErr= "orderplaced";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__order);

        listconfirm = (ListView) findViewById(R.id.listconfirm);

        orderbutton = (Button) findViewById(R.id.orderbutton);

        confirmOrderAdapter = new ConfirmOrderAdapter(getApplicationContext(), OrderGet.listD);
        listconfirm.setAdapter(confirmOrderAdapter);


        orderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maneDialog();

            }
        });


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
        if(id==android.R.id.home)
        {
            OrderGet.listD.clear();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class OrderConfirm extends AsyncTask<Void,Void,Void>{
        ProgressDialog pd= new ProgressDialog(Confirm_Order.this);

        GoogleMap mMap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Loading...");
//            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
            String datee= simpleDateFormat.format(new Date());


            for(int i=0;i<OrderGet.listD.size();i++) {
                try {
                    String pname = OrderGet.getList().get(i).getProductname();
                    String desc =  OrderGet.getList().get(i).getPdiscription();
                    String spec =  OrderGet.getList().get(i).getSpecification();
                    String prate = OrderGet.getList().get(i).getRate();
                    String qty =  OrderGet.getList().get(i).getQty();
                    String am =  OrderGet.getList().get(i).getAmount();
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAMErr);

                    request.addProperty("name", pname);
                    request.addProperty("description", desc);
                    request.addProperty("specification", spec);
                    request.addProperty("rate", prate);
                    request.addProperty("qty", qty);
                    request.addProperty("amount", am);
                    request.addProperty("date",datee);
                    request.addProperty("location", cityName);
                    request.addProperty("companyid", "1");

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);

                    HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                    httpTransportSE.call(SOAP_ACTIONrr, envelope);

                } catch (IOException | XmlPullParserException e) {
                    pd.dismiss();

                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
        }
    }


    private void maneDialog() {

        MaterialStyledDialog dialog = new MaterialStyledDialog.Builder(this)
                .setTitle("Confirm !")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setDescription("Do you want to Confirm Order. \n")
                .withIconAnimation(true)
                .setPositiveText("Confirm")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        new OrderConfirm().execute();
                        startActivity(new Intent(Confirm_Order.this,MainActivity.class));


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
    private Boolean displayGpsStatus() {

        ContentResolver resolver = getBaseContext().getContentResolver();
        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(resolver, LocationManager.GPS_PROVIDER);
        if(gpsStatus)
        {
            return  true;
        }
        else {
            return false;
        }
    }
    private void alertbox(String s, String s1) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("Gps Status ")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
