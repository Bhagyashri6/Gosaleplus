package com.chandrakant.abc.crm_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.chandrakant.abc.crm_app.ListAdapter.mynum;

public class CallActivity extends AppCompatActivity {

    private TextView getnumber,callername;
    private Button call;
    ListView list;

    android.widget.ListAdapter listAdapter;
    static long start_time;
    static long end_time;
    static long total_time;

    int p = 0;
    private static final String AUDIO_ROCORDER_FILE_EXT_3GP = ".3gp";
    private static final String AUDIO_ROCORDER_FOLDER = "AudioRecorder";
    private static final String AUDIO_ROCORDER_FILE_EXT_MP4 = ".mp4";

    String duration;
    int status;
    static String du;
    private MediaRecorder recorder = null;
    private int currentFormat = 0;
    private int output_formats[] = {MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP};

    ArrayList<CallData> datas = new ArrayList<>();
    AudioManager audioManager;
    static String number,dname;
    TelephonyManager tm;
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String SOAP_ACTIONc = "http://tempuri.org/callinsert";
    private static final String METHOD_NAMEc = "callinsert";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getnumber = (TextView) findViewById(R.id.getnumber);
        callername = (TextView) findViewById(R.id.callername);
        call = (Button) findViewById(R.id.calld);
        list = (ListView) findViewById(R.id.list);

        Bundle bundle = getIntent().getExtras();
        String numb = bundle.getString("numb");
         dname = bundle.getString("dname");
         status = bundle.getInt("status");
        getnumber.setText(numb);

        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tm.getDeviceId();

       /* if (device_id.equals("911380451446821") || device_id.equals("911380451947323")) {

            new DownloadFile().execute();
        }
*/
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = getnumber.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + number));
               /* if(ContextCompat.checkSelfPermission(MainActivity3.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                {

                }
                else {
                    ActivityCompat.requestPermissions(MainActivity3.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }*/
                startActivity(intent);
                startRocrding();


            }

        });


        PhoneStateListener callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING) {


                    Toast.makeText(getApplicationContext(), "Phone Is Riging  ",
                            Toast.LENGTH_LONG).show();
                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    start_time = System.currentTimeMillis();
                    SimpleDateFormat timingFormat = new SimpleDateFormat("hh:mm");
                    String d = timingFormat.format(start_time);

//                    Toast.makeText(getApplicationContext(),"Phone is Currently in A call  "+d,
//                            Toast.LENGTH_LONG).show();
                }

                if (state == TelephonyManager.CALL_STATE_IDLE) {

                    end_time = System.currentTimeMillis();


                    total_time = end_time - start_time;
                    SimpleDateFormat timingFormat = new SimpleDateFormat("hh:mm");
                    String dd = timingFormat.format(total_time);
                    stopRecording();
//                    Toast.makeText(getApplicationContext(),"phone is neither ringing nor in a call  "+dd,
//                            Toast.LENGTH_LONG).show();


                    int seconds = (int) (total_time / 1000) % 60;
                    int minutes = (int) ((total_time / (1000 * 60)) % 60);
                    int hours = (int) ((total_time / (1000 * 60 * 60)) % 24);

                    duration = "";
                    if (hours > 0)
                        duration += hours + ":";
                    //duration += minutes+":";
                    if (minutes < 10)
                        duration += "0" + minutes + ":";
                    else
                        duration += "" + minutes + ":";

                    if (seconds < 10)
                        duration += "0" + seconds;
                    else
                        duration += "" + seconds;


                    // Toast.makeText(getApplicationContext(),duration,Toast.LENGTH_LONG).show();


                    if (status ==0) {
                       datas.clear();
                        status++;
                    } else {
                    //ListD.setList(1, number, duration, date());
                    datas.add(new CallData(1,dname, number, duration, date()));

                        listAdapter = new ListAdapter(CallActivity.this, datas);
                      list.setAdapter(listAdapter);


                        try {
                            TimeUnit.MILLISECONDS.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        new AsynkRocrd().execute();

                      new CallInsert().execute();
                    }
                }
            }
        };
        tm.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);


    }

    private String date() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        Date datee = new java.util.Date();

        return dateFormat.format(datee);
    }


    /* private MediaRecorder.OnErrorListener onErrorListener = new MediaRecorder.OnErrorListener() {
         @Override
         public void onError(MediaRecorder mr, int what, int extra) {

             Toast.makeText(MainActivity3.this, "Error: " + what + ", " + extra, Toast.LENGTH_LONG).show();

         }
     };
 */
    private MediaRecorder.OnInfoListener onInfoListener = new MediaRecorder.OnInfoListener() {
        @Override
        public void onInfo(MediaRecorder mr, int what, int extra) {

            Toast.makeText(CallActivity.this, "Warning: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
        }
    };


    public void startRocrding() {

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(output_formats[currentFormat]);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(String.valueOf(getFilename()));


        try {

            recorder.prepare();
            recorder.start();

        } catch (IllegalStateException e) {
            Log.e("REDORDING :: ", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("REDORDING :: ", e.getMessage());
            e.printStackTrace();
        }


    }

    public void stopRecording() {
//        audioManager.setSpeakerphoneOn(false);

        try {


            if (null != recorder) {
                recorder.stop();
                recorder.reset();
                recorder.release();

                recorder = null;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            recorder.release();
        }

    }

    private File getFilename() {
      /*  String filepath= Environment.getExternalStorageDirectory().getAbsolutePath();
        File file =new File(filepath,AUDIO_ROCORDER_FOLDER);

        if(file.exists())
        {
            file.mkdirs();
        }
        return (file.getAbsolutePath()+"/"+System.currentTimeMillis()+file_exts[currentFormat]);*/


        File folder = new File("sdcard/camera_app");

        if (!folder.exists()) {
            folder.mkdirs();
        }


        if (number != null) {
            SimpleDateFormat sf = new SimpleDateFormat("hhmmss");
            String jj = sf.format(new Date());
            du = String.valueOf(jj);
        }
        File imageFile = new File(folder, dname+"_"+ number+"_" + du + ".mp3");
        mynum(number);
        return imageFile;

    }


    class AsynkRocrd extends AsyncTask<Void, Void, Void> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pd = new ProgressDialog(CallActivity.this);
            pd.setMessage("Loading....");
//           pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            try {
                FTPClient ftpClient = new FTPClient();
                ftpClient.connect(InetAddress.getByName("182.18.133.12"));
                ftpClient.enterLocalPassiveMode();
                ftpClient.login("gosalesplus", "Preet@1234");
                ftpClient.changeWorkingDirectory("/httpdocs/Recordings");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedInputStream buffIn = null;
                buffIn = new BufferedInputStream(new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/camera_app/"+dname+"_" + number+"_" + du + ".mp3"));
               ftpClient.storeFile(dname+"_"+ number +"_"+ du +".mp3", buffIn);
                buffIn.close();
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);

            pd.dismiss();


        }
    }


    @Override
    public void onBackPressed() {

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
            ListD.listD.clear();
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



    private class CallInsert extends AsyncTask<Void, Void, Void> {
        private String model = "";

        @Override
        protected Void doInBackground(Void... avoid) {
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAMEc);

                request.addProperty("name", dname);
                request.addProperty("number", number);
                request.addProperty("calltime", duration);
                request.addProperty("date", date());
                request.addProperty("recording","http://www.gosalesplus.com/Recordings/"+dname+"_"+number+"_"+du+".mp3");


                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                httpTransportSE.call(SOAP_ACTIONc, envelope);
            } catch (IOException | XmlPullParserException e) {


                e.printStackTrace();
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




}