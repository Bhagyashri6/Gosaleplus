package com.chandrakant.abc.crm_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TodaysPJP extends AppCompatActivity  {
    ArrayList<AddData> addData = new ArrayList<>();
    private static final int REQUEST_CALL = 1;
    Intent callIntent;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    String currentdate, tomorrow, next3day, next4day, next5day, next6day, next7day, back1, back2, back3, back4, back5, back6, back7;
    Boolean isOpen = false;
    FloatingActionButton fab_plus, fab_create, fab_viewall;
    Animation FabOpen, FabClose, FabClosewise, FabantiClosewise;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    AutoCompleteTextView autoComplete;
    Context context;
    TelephonyManager tm;
    Boolean check;


    private EditText getnumber;
    private Button call;
    ListView list;

    ListAdapter listAdapter;
    static long start_time;
    static long end_time;
    static long total_time;

    static int p=0;
    private static  final String AUDIO_ROCORDER_FILE_EXT_3GP=".3gp";
    private static  final String AUDIO_ROCORDER_FOLDER="AudioRecorder";
    private static  final String AUDIO_ROCORDER_FILE_EXT_MP4=".mp4";

    String duration;
    static String du;
    private MediaRecorder recorder=null;
    private int currentFormat=0;
    private int output_formats[]={MediaRecorder.OutputFormat.MPEG_4,MediaRecorder.OutputFormat.THREE_GPP};


    AudioManager audioManager;
    static String number;






    String[] andheri = {"Dell", "Pioneer", "HP"};
    String[] jogeshwari = {"Anchor", "Onida", "JustDial"};
    String[] malad = {"Wipro", "Infosys", "TCS"};
    String[] borivali = {"India mart", "Snapdeal", "Flipkart"};
    Spinner datespin, arealistspin;
    String address, Name, addDate, Id;
    ImageButton back;
    RelativeLayout activity_todays_pjp;
    CoordinatorLayout scc;
    LinearLayout linearLayout;
    TextView textfabcreate, textfabviewall;
    ProgressDialog pd;

    private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/getAllAreaName";
    private static final String METHOD_NAME = "getAllAreaName";
    private static final String SOAP_ACTION1 = "http://tempuri.org/newCreatePJP";
    private static final String METHOD_NAME1 = "newCreatePJP";
    private static final String SOAP_ACTIONAU = "http://tempuri.org/allDistributorName";
    private static final String METHOD_NAMEAU = "allDistributorName";
    private static final String SOAP_ACTIONTP = "http://tempuri.org/todaysPJP";
    private static final String METHOD_NAMETP = "todaysPJP";


    ArrayAdapter<String> areaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_pjp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pd = new ProgressDialog(TodaysPJP.this);


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

//        toolbar = (Toolbar) findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        activity_todays_pjp = (RelativeLayout) findViewById(R.id.activity_todays_pjp);
        scc = (CoordinatorLayout) findViewById(R.id.scc);

        fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab_create = (FloatingActionButton) findViewById(R.id.fab_create);
        fab_viewall = (FloatingActionButton) findViewById(R.id.fab_viewall);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabClosewise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabantiClosewise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        linearLayout = (LinearLayout) findViewById(R.id.Bottom);

        /*button=(Button)findViewById(R.id.button);

        final Animation an= AnimationUtils.loadAnimation(getBaseContext(),R.anim.slide_up_dialog);
        final Animation an1= AnimationUtils.loadAnimation(getBaseContext(),R.anim.slide_out_down);
        linearLayout=(LinearLayout)findViewById(R.id.Bottom);
        button.setOnClickListener(new View.OnClickListener() {
            int flag=0;
            @Override
            public void onClick(View view) {

                if(flag==0) {
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout.startAnimation(an);
                    flag=1;
                }
                else {
                    linearLayout.startAnimation(an1);
                    linearLayout.setVisibility(View.GONE);
                    flag=0;
                }
            }
        });

    }*/

        viewPager.setCurrentItem(7);


        textfabcreate = (TextView) findViewById(R.id.textfabcreate);
        textfabviewall = (TextView) findViewById(R.id.textfabviewall);
        new Todayspjp().execute();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);





        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isOpen) {
                    fab_viewall.startAnimation(FabClose);
                    fab_create.startAnimation(FabClose);
                    textfabcreate.startAnimation(FabClose);
                    textfabviewall.startAnimation(FabClose);

                    fab_plus.startAnimation(FabantiClosewise);
                    fab_viewall.setClickable(false);
                    fab_create.setClickable(false);
                    isOpen = false;
                } else {
                    fab_viewall.startAnimation(FabOpen);
                    fab_create.startAnimation(FabOpen);
                    textfabcreate.startAnimation(FabOpen);
                    textfabviewall.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabClosewise);
                    fab_viewall.setClickable(true);
                    fab_create.setClickable(true);
                    isOpen = true;
                }
            }
        });

        //=============================== Custom dialouge ========================//

        fab_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(TodaysPJP.this, ViewAll.class);
                startActivity(intent);
            }
        });


        fab_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(TodaysPJP.this);
                final View mView = getLayoutInflater().inflate(R.layout.dialough_create, null);
                final Button createbutton = (Button) mView.findViewById(R.id.createbutton);
//                final LinearLayout alloutlet = (LinearLayout) mView.findViewById(R.id.alloutlet);
                LinearLayout adddistributor = (LinearLayout) mView.findViewById(R.id.adddistributor);
                autoComplete = (AutoCompleteTextView) mView.findViewById(R.id.autoComplete);
                datespin = (Spinner) mView.findViewById(R.id.datespin);
                arealistspin = (Spinner) mView.findViewById(R.id.arealistspin);
                ImageView closeDialoug = (ImageView) mView.findViewById(R.id.closeDialoug);

                Name = autoComplete.getText().toString();


                fab_viewall.startAnimation(FabClose);
                fab_create.startAnimation(FabClose);
                textfabcreate.startAnimation(FabClose);
                textfabviewall.startAnimation(FabClose);

                fab_plus.startAnimation(FabantiClosewise);
                fab_viewall.setClickable(false);
                fab_create.setClickable(false);

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();
                String[] days = new String[7];
                days[0] = sdf.format(date);

                for (int i = 1; i < 7; i++) {
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    date = cal.getTime();
                    days[i] = sdf.format(date);

                }


                scc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isOpen) {
                            fab_viewall.startAnimation(FabClose);
                            fab_create.startAnimation(FabClose);
                            textfabcreate.startAnimation(FabClose);
                            textfabviewall.startAnimation(FabClose);

                            fab_plus.startAnimation(FabantiClosewise);
                            fab_viewall.setClickable(false);
                            fab_create.setClickable(false);
                        }
                    }
                });

                //---------------------------- Add Distributor ----------------------//

                adddistributor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), AddDistributor.class));
                    }
                });


                //---------------------------- Area Spinner ------------------------//


                arealistspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        address = adapterView.getItemAtPosition(i).toString();

                    /*    if (address.contentEquals("Andheri")) {
                            autoComplete.setVisibility(View.VISIBLE);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.select_dialog_item, andheri);

                            autoComplete.setThreshold(1);
                            autoComplete.setAdapter(adapter);
                            autoComplete.setTextColor(Color.RED);
                        }
                        if (address.contentEquals("jogeshwari")) {
                            autoComplete.setVisibility(View.VISIBLE);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.simple_selectable_list_item, jogeshwari);
                            autoComplete.setThreshold(1);
                            autoComplete.setAdapter(adapter);
                            autoComplete.setTextColor(Color.RED);
                        }
                        if (address.contentEquals("malad")) {
                            autoComplete.setVisibility(View.VISIBLE);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.select_dialog_item, malad);
                            autoComplete.setThreshold(1);
                            autoComplete.setAdapter(adapter);
                            autoComplete.setTextColor(Color.RED);
                        }
                        if (address.contentEquals("borivali")) {
                            autoComplete.setVisibility(View.VISIBLE);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.select_dialog_item, borivali);
                            autoComplete.setThreshold(1);
                            autoComplete.setAdapter(adapter);
                            autoComplete.setTextColor(Color.RED);
                        }
*/
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                datespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        addDate = adapterView.getItemAtPosition(i).toString();
//                        Toast.makeText(TodaysPJP.this, "" + addDate, Toast.LENGTH_SHORT).show();
                        String dd = String.valueOf(adapterView.getItemAtPosition(i).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                builder.setView(mView);
                dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimatione;
                dialog.show();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.simple_list_item_1, days);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                datespin.setAdapter(arrayAdapter);

                //---------------------- AutoComplete TextView -----------------//


                new AutoName().execute();

                //------------------------ arealist spinner----------------------------------//

                new SpinnerArea().execute();
//                ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.simple_list_item_1, Arealist);


                //------------------------------ close dialoug ------------------------------//


                closeDialoug.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                createbutton.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {

                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                        Name = autoComplete.getText().toString();

                        if (Name.isEmpty()) {
                            Toast.makeText(view.getContext(), "Please Enter Field", Toast.LENGTH_SHORT).show();
                        } else {
                            new CreatePJP().execute();

                            dialog.dismiss();
                            finish();
                            startActivity(getIntent());

                        }
                    }


                });

                //--------------------- date spin ----------------------//


            }
        });

        //----------------------------------------+++++-------------------------------//

        //--------------------- simple date format -----------------------------------//


        dateFormated();
        viewPagerAdapter.addFragments(new Tab7(), back7);
        viewPagerAdapter.addFragments(new Tab6(), back6);
        viewPagerAdapter.addFragments(new Tab5(), back5);
        viewPagerAdapter.addFragments(new Tab4(), back4);
        viewPagerAdapter.addFragments(new Tab3(), back3);
        viewPagerAdapter.addFragments(new Tab2(), back2);
        viewPagerAdapter.addFragments(new Tab1(), back1);
        viewPagerAdapter.addFragments(new Monday(), currentdate);

        viewPagerAdapter.addFragments(new Tuesday(), tomorrow);
        viewPagerAdapter.addFragments(new Wednesday(), next3day);
        viewPagerAdapter.addFragments(new Thursday(), next4day);
        viewPagerAdapter.addFragments(new Friday(), next5day);
        viewPagerAdapter.addFragments(new Saturday(), next6day);
        viewPagerAdapter.addFragments(new Sunday(), next7day);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(7);


    }


    private void dateFormated() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd.MM.yyyy");
        Calendar currentCal = Calendar.getInstance();
        currentdate = dateFormat.format(currentCal.getTime());


        Calendar currentCal1 = Calendar.getInstance();
        currentCal1.add(Calendar.DATE, 1);
        tomorrow = dateFormat.format(currentCal1.getTime());

        Calendar currentCal2 = Calendar.getInstance();
        currentCal2.add(Calendar.DATE, 2);
        next3day = dateFormat.format(currentCal2.getTime());

        Calendar currentCal3 = Calendar.getInstance();
        currentCal3.add(Calendar.DATE, 3);
        next4day = dateFormat.format(currentCal3.getTime());

        Calendar currentCal4 = Calendar.getInstance();
        currentCal4.add(Calendar.DATE, 4);
        next5day = dateFormat.format(currentCal4.getTime());

        Calendar currentCal5 = Calendar.getInstance();
        currentCal5.add(Calendar.DATE, 5);
        next6day = dateFormat.format(currentCal5.getTime());

        Calendar currentCal6 = Calendar.getInstance();
        currentCal6.add(Calendar.DATE, 6);
        next7day = dateFormat.format(currentCal6.getTime());

        Calendar currentCal7 = Calendar.getInstance();
        currentCal6.add(Calendar.DATE, 7);
        String next8day = dateFormat.format(currentCal6.getTime());

        Calendar currentcal_1 = Calendar.getInstance();
        currentcal_1.add(Calendar.DATE, -1);
        back1 = dateFormat.format(currentcal_1.getTime());

        Calendar currentcal_2 = Calendar.getInstance();
        currentcal_2.add(Calendar.DATE, -2);
        back2 = dateFormat.format(currentcal_2.getTime());

        Calendar currentcal_3 = Calendar.getInstance();
        currentcal_3.add(Calendar.DATE, -3);
        back3 = dateFormat.format(currentcal_3.getTime());

        Calendar currentcal_4 = Calendar.getInstance();
        currentcal_4.add(Calendar.DATE, -4);
        back4 = dateFormat.format(currentcal_4.getTime());

        Calendar currentcal_5 = Calendar.getInstance();
        currentcal_5.add(Calendar.DATE, -5);
        back5 = dateFormat.format(currentcal_5.getTime());

        Calendar currentcal_6 = Calendar.getInstance();
        currentcal_6.add(Calendar.DATE, -6);
        back6 = dateFormat.format(currentcal_6.getTime());

        Calendar currentcal_7 = Calendar.getInstance();
        currentcal_7.add(Calendar.DATE, -7);
        back7 = dateFormat.format(currentcal_7.getTime());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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




        class SpinnerArea extends AsyncTask<Void, Void, Void> {
            private String model = "";
            private ArrayAdapter<String> areaAdapter;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd.setMessage("Loading....");
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

                try {
                    httpTransportSE.call(SOAP_ACTION, envelope);
                    SoapObject obj = (SoapObject) envelope.getResponse();


                    for (int i = 0; i < obj.getPropertyCount(); i++) {
                        model = model + obj.getProperty(i).toString() + ',';
                    }
                    String[] data = model.split(",");

                    areaAdapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.simple_list_item_1, data);

                } catch (Exception e) {
                    pd.dismiss();
                    e.printStackTrace();

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                pd.dismiss();
                arealistspin.setAdapter(areaAdapter);

            }
        }

        class AutoName extends AsyncTask<Void, Void, Void> {
            private String model = "";
            private ArrayAdapter<String> adapter;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd.setMessage("Loading....");
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAMEAU);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

                try {
                    httpTransportSE.call(SOAP_ACTIONAU, envelope);
                    SoapObject obj = (SoapObject) envelope.getResponse();


                    for (int i = 0; i < obj.getPropertyCount(); i++) {
                        model = model + obj.getProperty(i).toString() + ',';
                    }
                    String[] data = model.split(",");

                    adapter = new ArrayAdapter<String>(TodaysPJP.this, android.R.layout.select_dialog_item, data);



                } catch (IOException e) {
                    pd.dismiss();
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    pd.dismiss();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                autoComplete.setThreshold(1);
                autoComplete.setTextColor(Color.BLACK);
                autoComplete.setAdapter(adapter);
                pd.dismiss();

            }
        }
        class CreatePJP extends AsyncTask<Void, Void, Void> {
            private String model = "";

            @Override
            protected Void doInBackground(Void... avoid) {
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);

                    request.addProperty("name", Name);
                    request.addProperty("address", address);
                    request.addProperty("date", addDate);

                  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);

                    HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                    httpTransportSE.call(SOAP_ACTION1, envelope);
                    SoapPrimitive obj = (SoapPrimitive) envelope.getResponse();
                 model=obj.toString();
                } catch (IOException | XmlPullParserException e) {


                    e.printStackTrace();
                }


                return null;
            }
        }

        class Todayspjp extends AsyncTask<Void, Void, Void> {



            String dd = "";
            String wid = "", wname = "", waddress = "", wcontact = "", wdate = "";

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd.setMessage("Loading...");
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... avoid) {
                String[] Loca = new String[]{}, Loca1 = new String[]{}, Loca2 = new String[]{}, Loca3 = new String[]{}, id = new String[]{};
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAMETP);


                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);

                    HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
                    httpTransportSE.call(SOAP_ACTIONTP, envelope);

          /*      for (int i = 0; i < object.getPropertyCount(); i++) {
                    model +=object.getProperty(i).toString()+ ',';

//                    ListModel.setList(0,object.getProperty("distributorname").toString(),object.getProperty("addresss").toString(),object.getProperty("contact").toString(),object.getProperty("date").toString());
                }
                 data=model.split(",");
                for (int i=0;i<data.length;i++) {
                       dd =data[0]+","+data[1]+","+data[2];
                }
                    ListModel.setList(0, data[0], data[1], data[2], "28.01.2017");*/


                    SoapObject objs = (SoapObject) envelope.getResponse();

                    for (int j = 0; j < objs.getPropertyCount(); j++)

                    {


                        SoapObject obj2 = (SoapObject) objs.getProperty(j);

                        wid = wid + obj2.getProperty("S1").toString() + ',';
                        wname = wname + obj2.getProperty("S2").toString() + ',';
                        waddress = waddress + obj2.getProperty("S3").toString() + ',';
                        wcontact = wcontact + obj2.getProperty("S4").toString() + ',';
                        wdate = wdate + obj2.getProperty("S5").toString() + ',';

                    }

                    if (objs.getPropertyCount() > 0) {
                        id = wid.split(",");
                        Loca = wname.split(",");
                        Loca1 = waddress.split(",");
                        Loca2 = wcontact.split(",");
                        Loca3 = wdate.split(",");

                    }
                    for (int i = 0; i < Loca.length; i++) {
                        ListModel.setList(id[i], 0, Loca[i], Loca1[i], Loca2[i], Loca3[i]);
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



                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Calendar cal = Calendar.getInstance();
                Date date = cal.getTime();

                String day = sdf.format(date);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Calendar currentCal1 = Calendar.getInstance();
                currentCal1.add(Calendar.DATE, 1);
                tomorrow = dateFormat.format(currentCal1.getTime());


                Calendar currentCal2 = Calendar.getInstance();
                currentCal2.add(Calendar.DATE, 2);
                next3day = dateFormat.format(currentCal2.getTime());

                Calendar currentCal3 = Calendar.getInstance();
                currentCal3.add(Calendar.DATE, 3);
                next4day = dateFormat.format(currentCal3.getTime());

                Calendar currentCal4 = Calendar.getInstance();
                currentCal4.add(Calendar.DATE, 4);
                next5day = dateFormat.format(currentCal4.getTime());

                Calendar currentCal5 = Calendar.getInstance();
                currentCal5.add(Calendar.DATE, 5);
                next6day = dateFormat.format(currentCal5.getTime());

                Calendar currentCal6 = Calendar.getInstance();
                currentCal6.add(Calendar.DATE, 6);
                next7day = dateFormat.format(currentCal6.getTime());

                Calendar currentcal_1 = Calendar.getInstance();
                currentcal_1.add(Calendar.DATE, -1);
                back1 = dateFormat.format(currentcal_1.getTime());


                Calendar currentcal_2 = Calendar.getInstance();
                currentcal_2.add(Calendar.DATE, -2);
                back2 = dateFormat.format(currentcal_2.getTime());

                Calendar currentcal_3 = Calendar.getInstance();
                currentcal_3.add(Calendar.DATE, -3);
                back3 = dateFormat.format(currentcal_3.getTime());

                Calendar currentcal_4 = Calendar.getInstance();
                currentcal_4.add(Calendar.DATE, -4);
                back4 = dateFormat.format(currentcal_4.getTime());

                Calendar currentcal_5 = Calendar.getInstance();
                currentcal_5.add(Calendar.DATE, -5);
                back5 = dateFormat.format(currentcal_5.getTime());

                Calendar currentcal_6 = Calendar.getInstance();
                currentcal_6.add(Calendar.DATE, -6);
                back6 = dateFormat.format(currentcal_6.getTime());

                Calendar currentcal_7 = Calendar.getInstance();
                currentcal_7.add(Calendar.DATE, -7);
                back7 = dateFormat.format(currentcal_7.getTime());


                for (int i = 0; i < ListModel.list.size(); i++) {
                    String datee = ListModel.getList().get(i).getDate();
                    Name = ListModel.getList().get(i).getDistributorname();
                    address = ListModel.getList().get(i).getAddress();
                    Id = ListModel.getList().get(i).getId();
                    String contact = ListModel.getList().get(i).getContact();
                    if (day.equals(datee)) {
                       /* Id = ListModel.getList().get(i).getId();
                        String Name = ListModel.getList().get(i).getDistributorname();
                        String address = ListModel.getList().get(i).getAddress();*/

                        ListModel.setList1(Id, R.drawable.profileimg, Name, address,contact , "");
                    } else if (tomorrow.equals(datee)) {


                        ListModel.setList2(Id, R.drawable.profileimg, Name, address, contact, "");

                    } else if (next3day.equals(datee)) {
                        ListModel.setList3(Id, R.drawable.profileimg, Name, address,contact , "");
                    } else if (next4day.equals(datee)) {
                        ListModel.setList4(Id, R.drawable.profileimg, Name, address, contact , "");
                    } else if (next5day.equals(datee)) {
                        ListModel.setList5(Id, R.drawable.profileimg, Name, address,contact , "");
                    } else if (next6day.equals(datee)) {
                        ListModel.setList6(Id, R.drawable.profileimg, Name, address, contact , "");
                    } else if (next7day.equals(datee)) {
                        ListModel.setList7(Id, R.drawable.profileimg, Name, address, contact , "");
                    } else if (back1.equals(datee)) {
                        ListModel.setListback1(R.drawable.profileimg, Name, address, contact , "");
                    } else if (back2.equals(datee)) {
                        ListModel.setListback2(R.drawable.profileimg, Name, address, contact , "");
                    } else if (back3.equals(datee)) {
                        ListModel.setListback3(R.drawable.profileimg, Name, address,contact , "");
                    } else if (back4.equals(datee)) {
                        ListModel.setListback4(R.drawable.profileimg, Name, address, contact , "");
                    } else if (back5.equals(datee)) {
                        ListModel.setListback5(R.drawable.profileimg, Name, address, contact , "");
                    } else if (back6.equals(datee)) {
                        ListModel.setListback6(R.drawable.profileimg, Name, address, contact , "");
                    } else if (back7.equals(datee)) {
                        ListModel.setListback7(R.drawable.profileimg, Name, address, contact , "");
                    }
                }
                pd.dismiss();
            }
        }


        @Override
        public void onBackPressed () {

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

