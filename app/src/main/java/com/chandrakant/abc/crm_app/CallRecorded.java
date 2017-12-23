package com.chandrakant.abc.crm_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.chandrakant.abc.crm_app.ListModel.list2;

public class CallRecorded extends AppCompatActivity {
    ListView listm;
    ListAdapter2 listAdapter2;
ProgressDialog pd;
   static String ss;

    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String SOAP_ACTIONcal = "http://tempuri.org/calllist";
    private static final String METHOD_NAMEcal = "calllist";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_recorded);

        listm = (ListView) findViewById(R.id.list2);

        new DownloadFile().execute();

        new CallList().execute();
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


        if (id == android.R.id.home) {
            ListModel.list1.clear();
            list2.clear();
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
            deleteFolder();
            Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {



    }

    private  void  deleteFolder()
    {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gosales");
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
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

    private class DownloadFile extends AsyncTask<String, Integer, String> {
        URL url;
        OutputStream output;
        InputStream input;
        @Override
        protected String doInBackground(String... urlParams) {
            int count;
            try {
              /*url = new URL("http://www.gosalesplus.com/Recordings/Chandrakant_8108561288_023437.mp3");

                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();

                input = new BufferedInputStream(url.openStream());
                output = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gosales/dasdd.mp3");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;

                    publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);

                }
*/
                ArrayList<String> FilesInFolder =GetFiles(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gosales");

                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(CallRecorded.this, android.R.layout.simple_list_item_1, FilesInFolder);
                listm.setAdapter(adapter1);
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {


            }
            return null;
        }
    }
    public  ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);

        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {

            for (int i=0; i<files.length; i++)
                MyFiles.add(files[i].getName());
        }

        return MyFiles;
    }

    class CallList extends AsyncTask<Void, Void, Void> {



        String dd = "";
        String wid = "", wname = "", wnumber = "", wcalltime = "", wdate="",wrecording = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         /*   pd.setMessage("Loading...");
            pd.show();*/
        }

        @Override
        protected Void doInBackground(Void... avoid) {
            String[] Loca = new String[]{}, Loca1 = new String[]{}, Loca2 = new String[]{},Loca4=new String[]{}, Loca3 = new String[]{}, id = new String[]{};
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAMEcal);


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                httpTransportSE.call(SOAP_ACTIONcal, envelope);




                SoapObject objs = (SoapObject) envelope.getResponse();

                for (int j = 0; j < objs.getPropertyCount(); j++)

                {


                    SoapObject obj2 = (SoapObject) objs.getProperty(j);

                    wid = wid + obj2.getProperty("S1").toString() + ',';
                    wname = wname + obj2.getProperty("S2").toString() + ',';
                    wnumber = wnumber + obj2.getProperty("S3").toString() + ',';
                    wcalltime = wcalltime + obj2.getProperty("S4").toString() + ',';
                    wdate = wdate + obj2.getProperty("S5").toString() + ',';
                    wrecording = wrecording + obj2.getProperty("S6").toString() + ',';

                }

                if (objs.getPropertyCount() > 0) {
                    id = wid.split(",");
                    Loca = wname.split(",");
                    Loca1 = wnumber.split(",");
                    Loca2 = wcalltime.split(",");
                    Loca3 = wdate.split(",");
                    Loca4 = wrecording.split(",");

                }
                for (int i = 0; i < Loca.length; i++) {
                    ListD.setList(Integer.parseInt(id[i]), Loca[i], Loca1[i], Loca2[i], Loca3[i],Loca4[i]);
                    new DownloadFiles(Loca4[i]).execute();
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

            listAdapter2= new ListAdapter2(CallRecorded.this,ListD.listD);

            listm.setAdapter(listAdapter2);
            listAdapter2.notifyDataSetChanged();


        }
    }

    private class DownloadFiles extends AsyncTask<String , Integer, String> {

        URL url;
        OutputStream output;
        InputStream input;
String gfg ;


       public DownloadFiles(String s) {
            gfg = s;
        }

        @Override
        protected String doInBackground(String... urlParams) {
            int count;
            try {

                url = new URL(gfg);

                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();

               ss=gfg.substring(38);

                input = new BufferedInputStream(url.openStream());
                output = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gosales/"+ss);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;

                    publishProgress((int) (total * 100 / lenghtOfFile));
                    output.write(data, 0, count);

                }

            /*    ArrayList<String> FilesInFolder =GetFiles(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gosales");

                ArrayAdapter<String> adapter = new ArrayAdapter<>(Ca.this, android.R.layout.simple_list_item_1, FilesInFolder);
                list.setAdapter(adapter);
                output.flush();
                output.close();
                input.close();*/
            } catch (Exception e) {


            }
            return null;
        }
    }
}
