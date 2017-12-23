package com.chandrakant.abc.crm_app;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static GoogleMap mMap;
    private TextView startlocation,endlocation;
    private Button ssearch;
    List<Address> fromaddressList ;
    List<Address> toaddressList ;
    List<Address> alladdress ;
    LatLng latLng,lontLng;
    String fromLocation;
    Address address, addresse,muadd;
    String toLocation;
    private ArrayAdapter<String> areaAdapter;
    Polyline line;
    List<Address>  addresses;
    CheckBox clocation;
    String[] data;
    AlertDialog alertDialog;

    private LocationManager locationMangaer=null;
    private LocationListener locationListener=null;
    ListView listView;
   static String cityName=null;




    private static final String URL = "http://www.gosalesplus.com/service.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String SOAP_ACTION = "http://tempuri.org/getAllAreaName";
    private static final String METHOD_NAME = "getAllAreaName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
     startlocation=(TextView)findViewById(R.id.startlocation);
        endlocation=(TextView)findViewById(R.id.endlocation);
        ssearch=(Button)findViewById(R.id.ssearch);
        clocation=(CheckBox) findViewById(R.id.clocation);






        startlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder =new AlertDialog.Builder(MapsActivity.this);

                View v= View.inflate(MapsActivity.this,R.layout.dialoughmap,null);
                 listView=(ListView)v.findViewById(R.id.outletselect);


            new fromArea().execute();

             /*   listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String vv=adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();
                        startlocation.setText(vv);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String vv=adapterView.getItemAtPosition(i).toString();
                       startlocation.setText(vv);
                alertDialog.dismiss();
                    }
                });

                builder.setView(v);
                alertDialog = builder.create();
                alertDialog.show();



            }
        });

        endlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder =new AlertDialog.Builder(MapsActivity.this);

                View v= View.inflate(MapsActivity.this,R.layout.dialoughmap,null);
                listView=(ListView)v.findViewById(R.id.outletselect);


                new fromArea().execute();

             /*   listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String vv=adapterView.getItemAtPosition(i).toString();
                        Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();
                        startlocation.setText(vv);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String vv=adapterView.getItemAtPosition(i).toString();
                        endlocation.setText(vv);
                        alertDialog.dismiss();
                    }
                });

                builder.setView(v);
                alertDialog = builder.create();
                alertDialog.show();



            }
        });


       clocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {



           Boolean flag=false;


           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                   flag = displayGpsStatus();
                   if (b == true) {
                       if (flag) {


//                   LocationListener locationListener = new MyLocationListener();
                           Location loc = new Location(mMap.getMyLocation());


                           Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());

                           List<Address> myadd;
                           try {
                               myadd = gcd.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);

                               if (myadd.size() > 0) {

                                   cityName = myadd.get(0).getAddressLine(1);
                                   startlocation.setText(cityName);
                               }


                           } catch (IOException e) {
                               e.printStackTrace();
                           }
//                     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);


                       } else {
                           alertbox("Gps Status!!", "Your GPS is : OFF");
                       }
                   } else {

                   }



           }

       });


        ssearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    fromLocation = startlocation.getText().toString();
                    toLocation = endlocation.getText().toString();

                    if (fromLocation != null || fromLocation.equals("")) {
                        Geocoder geocoder = new Geocoder(MapsActivity.this);
                        try {
                            fromaddressList = geocoder.getFromLocationName(fromLocation, 1);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        address = fromaddressList.get(0);
                        latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(fromLocation));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    }
                    if (toLocation != null || toLocation.equals("")) {
                        Geocoder geocoder = new Geocoder(MapsActivity.this);
                        try {
                            toaddressList = geocoder.getFromLocationName(toLocation, 2);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        addresse = toaddressList.get(0);
                        lontLng = new LatLng(addresse.getLatitude(), addresse.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(lontLng).title(toLocation));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(lontLng));

                    }

                    line = mMap.addPolyline(new PolylineOptions().add(latLng, lontLng).width(7).color(Color.RED).geodesic(true));

                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=" + address.getLatitude() + "," + address.getLongitude() + "&daddr=" + addresse.getLatitude() + "," + addresse.getLongitude()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);

            }
        });




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

    private Boolean displayGpsStatus() {

        ContentResolver resolver = getBaseContext().getContentResolver();
        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(resolver,LocationManager.GPS_PROVIDER);
        if(gpsStatus)
        {
            return  true;
        }
        else {
            return false;
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(19.117159, 72.855785) , 6.0f) );
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
     /*   LatLng mumbai = new LatLng(19.138851, 72.835594);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("Marker in Mumbai"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mumbai,18));
*/
//        Geocoder geocoder = new Geocoder(MapsActivity.this);

       new SpinnerArea().execute();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            int flag=0;
            @Override
            public boolean onMarkerClick(Marker marker) {
        if(flag==0) {
            String value = marker.getTitle();
            startlocation.setText(value);

            endlocation.requestFocus();
            flag=1;
        }
                else {
            String value = marker.getTitle();
            endlocation.setText(value);
            flag=0;
        }
                return false;
            }

        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED&& ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }


       /* mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String value =marker.getTitle();
                endlocation.setText(value);

                return false;
            }
        });*/


    }

     /* class MyLocationListener implements LocationListener {
        ProgressDialog pd= new ProgressDialog(MapsActivity.this);
        @Override
        public void onLocationChanged(Location loc) {
            startlocation.setText("");

            Toast.makeText(getBaseContext(),"Location changed : Lat: " +
                            loc.getLatitude()+ " Lng: " + loc.getLongitude(),
                    Toast.LENGTH_SHORT).show();


            String logtitude= "Longitude "+loc.getLongitude();
            String latitude= "latitude"+loc.getLatitude();

            String cityName=null;

            Geocoder gcd= new Geocoder(getBaseContext(), Locale.getDefault());

            List<Address> myadd;
            try {
                myadd=gcd.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);

                if(myadd.size()>0)
                {
                    System.out.println(myadd.get(0).getLocality());
                    cityName=myadd.get(0).getLocality();
                    startlocation.setText(cityName);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }*/

    class SpinnerArea extends AsyncTask<Void,Void,Void> {
        private String model="";
        private ArrayAdapter<String> areaAdapter;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
           pd =new ProgressDialog(MapsActivity.this);
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

                data=model.split(",");

            } catch (XmlPullParserException e) {
                e.printStackTrace();
                pd.dismiss();
            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);

            for (int i=0;i<data.length;i++) {
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    fromLocation=data[i].toString();
                    fromaddressList = geocoder.getFromLocationName(fromLocation, 1);
                    address = fromaddressList.get(0);
                    latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(fromLocation));


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            pd.dismiss();
//            arealistspin .setAdapter(areaAdapter);

        }
    }

    class fromArea extends AsyncTask<Void,Void,Void> {
        private String model="";

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd =new ProgressDialog(MapsActivity.this);
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
                String[] data=model.split(",");

                areaAdapter = new ArrayAdapter<String>(MapsActivity.this, android.R.layout.simple_list_item_single_choice, data);

            } catch (IOException e) {
                pd.dismiss();
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                pd.dismiss();
            }

            return  null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
             listView.setAdapter(areaAdapter);
            pd.dismiss();


        }
    }


}
