package com.example.nehaniphadkar.locationtracker;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    double la = location.getLatitude();
                    double lo = location.getLongitude();
                    LatLng latLng = new LatLng(la, lo);
                    Geocoder geocoder = new Geocoder(getApplicationContext());


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
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                   /* double la=location.getLatitude();
                    double lo=location.getLongitude();
                    LatLng latLng=new LatLng(la,lo);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList= geocoder.getFromLocation(la,lo,1);
                        String str=addressList.get(0).getLocality()+ ",";
                        str +=addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));



                    } catch (IOException e) {


                    }
               */
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
            });
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
        DBHelper dbHelper = new DBHelper(MapsActivity.this);
        // Add a marker in Sydney and move the camera
        for (int i = 0; i < dbHelper.showList().size(); i++) {

            LatLng name = new LatLng(Double.parseDouble(dbHelper.showList().get(i).getLat()), Double.parseDouble(dbHelper.showList().get(i).getLongi()));
            mMap.addMarker(new MarkerOptions().position(name).title(dbHelper.showList().get(i).getName()).draggable(true));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(name, 15.2f));
            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setCompassEnabled(true);
            uiSettings.setZoomControlsEnabled(true);
            uiSettings.setMyLocationButtonEnabled(true);
        }
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-gene

                // rated method stub
                Log.d("System out", "onMarkerDragStart..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void onMarkerDragEnd(final Marker arg0) {
                // TODO Auto-generated method stub
                Log.d("System out", "onMarkerDragEnd..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);

                mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));
                Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(
                            arg0.getPosition().latitude,
                            arg0.getPosition().longitude,
                            1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addresses == null || addresses.size()  == 0) {
                    msg = "No address found for the location";
                } else {
                    Address address = addresses.get(0);
                    StringBuffer addressDetails = new StringBuffer();

                    addressDetails.append(address.getFeatureName()+" , ");

                    addressDetails.append(address.getThoroughfare()+" , ");

                    addressDetails.append(address.getLocality()+" , ");

                    addressDetails.append(address.getSubAdminArea()+" , ");

                    addressDetails.append(address.getAdminArea()+" , ");

                    addressDetails.append(address.getCountryName()+" , ");

                    addressDetails.append(address.getPostalCode()+",");
msg=addressDetails.toString().replace("null , ","");
                }
                final Dialog dialog=new Dialog(MapsActivity.this,R.style.Theme_AppCompat_DayNight_Dialog);
                dialog.setContentView(R.layout.addname);
                final EditText editText=(EditText) dialog.findViewById(R.id.name);

                dialog.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editText.getText().toString().matches("")){
                            editText.setError("please specify name");
                        }
                        else{
                            addData( arg0.getPosition().latitude,arg0.getPosition().longitude,msg,editText.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }

            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub
                Log.i("System out", "onMarkerDrag...");
            }
        });

    }
    private void addData(double latitude, double longitude, String msg, String s) {
        ArrayList<Float> floats=new ArrayList<>();

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String dateToStr = format.format(today);
        System.out.println(dateToStr);
        String lats = String.valueOf(latitude);
        String longs =String.valueOf(longitude);
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
                System.out.println("zdcks"+distanceInMeters);
                if (distanceInMeters<30){
                    floats.add(distanceInMeters);

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
        if (floats.size()>0){
            Toast.makeText(MapsActivity.this, "Checkin Done", Toast.LENGTH_SHORT).show();
        }
        else{
            MapClass task = new MapClass();
            task.setName(s);
            task.setLat(lats);
            task.setLongi(longs);
            task.setTime(dateToStr);
            task.setAddress(msg);


            if (database.insertData(task)) {
                System.out.println("here4");

                Toast.makeText(this, "location inserted successfully", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, " location not inserted", Toast.LENGTH_LONG).show();
            }
        }


    }

}


