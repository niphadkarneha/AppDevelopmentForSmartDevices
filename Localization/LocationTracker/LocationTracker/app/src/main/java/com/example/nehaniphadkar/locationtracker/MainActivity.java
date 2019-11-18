package com.example.nehaniphadkar.locationtracker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button getLL;
    TextView latitudeLL;
    TextView longitudeLL;

    String lati, longi;

    TextView result;
    Geocoder geocoder;
    List<Address> addressList;

    // GPSTracker class    GPSTracker gps;

    Context mContext;

    double latitude, longitude;
    private GPSTracker gps;
    private Button btn;
    private EditText name;
    private String names;
    private FusedLocationProviderClient fusedLocationClient;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;

    private LocationAddressResultReceiver addressResultReceiver;

    private TextView currentAddTv;

    private Location currentLocation;

    private LocationCallback locationCallback;
    private boolean yes;
    private String nameComparable, latcomparable, longcomparable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
        btn = (Button) findViewById(R.id.b);
        Button save = (Button) findViewById(R.id.save);
        latitudeLL = (TextView) findViewById(R.id.latitude);
        longitudeLL = (TextView) findViewById(R.id.longitude);
        name = (EditText) findViewById(R.id.name);
        addressResultReceiver = new LocationAddressResultReceiver(new Handler());


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();
            }

            ;
        };
        startLocationUpdates();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        geocoder = new Geocoder(this, Locale.getDefault());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addData();
            }

        });
        mContext = this;

        getLL = (Button) findViewById(R.id.getLL);

        getLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CheckedInLocations.class));
            }
        });

    }

    /* @Override
     public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                            int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         switch (requestCode) {
             case 1: {
                 // If request is cancelled, the result arrays are empty.

                 if (grantResults.length > 0

                         && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                     // permission was granted, yay! Do the
                     // contacts-related task you need to do.
                     gps = new GPSTracker(mContext, MainActivity.this);

                     // Check if GPS enabled

                     if (gps.canGetLocation()) {

                         double latitude = gps.getLatitude();
                         double longitude = gps.getLongitude();

                         // \n is for new line

                         Toast.makeText(getApplicationContext(),
                                 "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                     } else {
                         // Can't get location.

                         // GPS or network is not enabled.

                         // Ask user to enable GPS/network in settings.

                         gps.showSettingsAlert();
                     }

                 } else {

                     // permission denied, boo! Disable the

                     // functionality that depends on this permission.
                     Toast.makeText(mContext, "You need to grant permission",

                             Toast.LENGTH_SHORT).show();
                 }
                 return;
             }
         }
     }*/
    private void addData() {
        ArrayList<Float> floats = new ArrayList<>();

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String dateToStr = format.format(today);
        System.out.println(dateToStr);
        names = name.getText().toString();
        String lats = latitudeLL.getText().toString();
        String longs = longitudeLL.getText().toString();
        Location loc1 = new Location("");
        loc1.setLatitude(Double.parseDouble(lats));
        loc1.setLongitude(Double.parseDouble(longs));
        DBHelper database = new DBHelper(this);
        ArrayList<MapClass> mapClasses = new ArrayList<>();
        mapClasses.addAll(database.showList());
        if (mapClasses.size() > 0) {

            for (int i = 0; i < mapClasses.size(); i++) {
                Location loc2 = new Location("");
                loc2.setLatitude(Double.parseDouble(mapClasses.get(i).getLat()));
                loc2.setLongitude(Double.parseDouble(mapClasses.get(i).getLongi()));

                float distanceInMeters = loc1.distanceTo(loc2);
                System.out.println("zdcks" + distanceInMeters);
                if (distanceInMeters < 30) {
                    floats.add(distanceInMeters);
                    nameComparable = mapClasses.get(i).getName();

                    latcomparable = mapClasses.get(i).getLat();
                    longcomparable = mapClasses.get(i).getLongi();


                }
            /*    if (distanceInMeters > 30) {
                    MapClass task = new MapClass();
                    task.setName(names);
                    task.setLat(lats);
                    task.setLongi(longs);
                    task.setTime(dateToStr);
                    task.setAddress(result.getText().toString());


                    if (database.insertData(task)) {
                        System.out.println("here1");
                        Toast.makeText(this, "location inserted successfully", Toast.LENGTH_LONG).show();
                        name.setText("");

                    } else {
                        System.out.println("here2");

                        Toast.makeText(this, " location not inserted", Toast.LENGTH_LONG).show();
                    }
                } */
            }
        }
        if (floats.size() > 0) {

                Toast.makeText(MainActivity.this, "Checkin Done", Toast.LENGTH_SHORT).show();



        } else {
            MapClass task = new MapClass();
            task.setName(names);
            task.setLat(lats);
            task.setLongi(longs);
            task.setTime(dateToStr);
            task.setAddress(result.getText().toString());


            if (database.insertData(task)) {
                System.out.println("here4");

                Toast.makeText(this, "location inserted successfully", Toast.LENGTH_LONG).show();
                name.setText("");

            } else {
                Toast.makeText(this, " location not inserted", Toast.LENGTH_LONG).show();
            }
        }


    }

    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            gps = new GPSTracker(MainActivity.this, MainActivity.this);

            // Check if GPS enabled

            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                // \n is for new line


                lati = Double.toString(latitude);
                longi = Double.toString(longitude);
                latitudeLL.setText(lati);
                longitudeLL.setText(longi);
            } else {
                // Can't get location.

                // GPS or network is not enabled.

                // Ask user to enable GPS/network in settings.

                gps.showSettingsAlert();
            }
            @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getAddress() {

        if (!Geocoder.isPresent()) {
            Toast.makeText(MainActivity.this,
                    "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                    gps = new GPSTracker(MainActivity.this, MainActivity.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();

                        // \n is for new line


                        lati = Double.toString(latitude);
                        longi = Double.toString(longitude);
                        latitudeLL.setText("Lattitude is : " + lati);
                        longitudeLL.setText("Longitude is : " + longi);
                    } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }
                } else {
                    Toast.makeText(this, "Location permission not granted, " +
                                    "restart the app if you want the feature",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    private class LocationAddressResultReceiver extends ResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultCode == 0) {
                //Last Location can be null for various reasons
                //for example the api is called first time
                //so retry till location is set
                //since intent service runs on background thread, it doesn't block main thread
                Log.d("Address", "Location null retrying");
                getAddress();
            }

            if (resultCode == 1) {
                Toast.makeText(MainActivity.this,
                        "Address not found, ",
                        Toast.LENGTH_SHORT).show();
            }

            String currentAdd = resultData.getString("address_result");

            showResults(currentAdd);
        }
    }

    private void showResults(String currentAdd) {
        result.setText(currentAdd);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}

