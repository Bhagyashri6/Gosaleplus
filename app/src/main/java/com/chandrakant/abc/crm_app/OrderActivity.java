package com.chandrakant.abc.crm_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    static  TextView  totalamountorder;
    ListView listorder;
    OrderAdapter orderAdapter;
    List<Integer> tmep = new ArrayList<>();
    static  int[] cal ;
    int r=0;
    static int  t;
    ArrayList<OrderDemo> orderDemo = new ArrayList<>();
    static Button placeorder;
    static HashMap<String, Integer> hm = new HashMap<>();


    private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/order";
    private static final String METHOD_NAME = "order";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        totalamountorder=(TextView)findViewById(R.id.totalamountorder);
        listorder=(ListView)findViewById(R.id.listorder);
        placeorder=(Button)findViewById(R.id.placeorder);



new AsynkOrder().execute();



       /* placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,Confirm_Order.class);
                intent.putExtra("total",totalamountorder.getText());
                intent.putExtra("qty",t);

                startActivity(intent);
            }
        });*/
    }
    public static void setAmount(int p,int h,int q){

        cal[p] = h;
        int jl =0;

        for (int aCal : cal) {
            jl = jl + aCal;
        }


        totalamountorder .setText(String.valueOf(jl));

       int t=q;

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
            maneDialog();


        }
        return super.onOptionsItemSelected(item);
    }

    private void maneDialog()
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



    public class  AsynkOrder extends AsyncTask<Void,Void,Void> {
        ProgressDialog pd;



        String dd="";
        String wpcode="",wproductname="",wpdiscription="",wspecification="",wrate="",wimage="",wcompanyid="",wquantity="";

        String[] Loca = new String[]{},Loca1=new String[]{},Loca2=new String[]{},Loca3=new String[]{},Loca4=new String[]{},Loca6=new String[]{};
        Bitmap[] Loca5=new Bitmap[7];
        List<Bitmap> Loca45 = new ArrayList<>();


        @Override
        protected void onPreExecute() {
        super.onPreExecute();
            pd=new ProgressDialog(OrderActivity.this);
            pd.setMessage("Loading...");
            pd.show();
            //pd.dismiss();

        }

        @Override
        protected Void doInBackground(Void... voids) {



            try {
                SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                httpTransportSE.call(SOAP_ACTION,envelope);

                SoapObject objs = (SoapObject) envelope.getResponse();

                for (int j = 0; j < objs.getPropertyCount(); j++)

                {


                    SoapObject obj2 = (SoapObject) objs.getProperty(j);

                    wpcode=wpcode+obj2.getProperty("S1").toString()+',';
                    wproductname = wproductname + obj2.getProperty("S2").toString() + ',';
                    wpdiscription = wpdiscription+ obj2.getProperty("S3").toString() + ',';
                    wspecification = wspecification+ obj2.getProperty("S4").toString() + ',';
                    wrate= wrate+ obj2.getProperty("S5").toString() + ',';
                    wimage= String.valueOf(obj2.getProperty("S6").toString().contentEquals("anyType{}")? R.drawable.check :obj2.getProperty("S6").toString());
                    wcompanyid= wcompanyid+ obj2.getProperty("S7").toString() + ',';

                    if(wimage.length()>1000) {
                        byte[] bloc = Base64.decode(wimage, Base64.DEFAULT);
                        Bitmap bmp = BitmapFactory.decodeByteArray(bloc, 0, bloc.length);
                        Loca45.add(bmp);
                    }

                }



                if (objs.getPropertyCount() > 0) {
                    Loca=wpcode.split(",");
                    Loca1 = wproductname.split(",");
                    Loca2 = wpdiscription.split(",");
                    Loca3 = wspecification.split(",");
                    Loca4 = wrate.split(",");
                    Loca6 = wcompanyid.split(",");

                }
                for(int i=0;i<Loca.length;i++)
                {
                    orderDemo.add(new OrderDemo(Loca[i],Loca1[i],Loca2[i],Loca3[i],Loca4[i],Loca45.get(i),Loca6[i]));
                    hm.put(orderDemo.get(i).getProductname()+i,0);
                    hm.put(orderDemo.get(i).getRate()+i,0);
                }


            } catch (IOException | XmlPullParserException e) {
                pd.dismiss();

                e.printStackTrace();
            }





            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pd.dismiss();
            orderAdapter = new OrderAdapter(getApplicationContext(),orderDemo);
            listorder.setAdapter(orderAdapter);

            cal = new int[Loca.length];

        }
    }



    @Override
    public void onBackPressed() {
    finish();
    }
}
