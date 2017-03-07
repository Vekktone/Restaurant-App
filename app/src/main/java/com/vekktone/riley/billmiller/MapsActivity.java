package com.vekktone.riley.billmiller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 10000;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();

            }
            return false;
        }
        return true;

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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //BM Locations
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3750020,-98.4487030)).title("Bill Miller, Store #01"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4294800,-98.4059510)).title("Bill Miller, Store #02"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3688840,-98.5038180)).title("Bill Miller, Store #03"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5149740,-98.5265810)).title("Bill Miller, Store #04"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4481170,-98.5526410)).title("Bill Miller, Store #05"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4837190,-98.4055910)).title("Bill Miller, Store #06"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4077090,-98.6287600)).title("Bill Miller, Store #07"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4283200,-98.5262670)).title("Bill Miller, Store #08"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3892940,-98.5323200)).title("Bill Miller, Store #09"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4660240,-98.5074770)).title("Bill Miller, Store #10"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4723530,-98.5334360)).title("Bill Miller, Store #11"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5107120,-98.3884550)).title("Bill Miller, Store #12"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5169420,-98.4507930)).title("Bill Miller, Store #13"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4846090,-98.6039080)).title("Bill Miller, Store #14"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3552510,-98.4817680)).title("Bill Miller, Store #15"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.2178460,-97.7558850)).title("Bill Miller, Store #16"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.3594560,-97.7295270)).title("Bill Miller, Store #17"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3976450,-98.3903710)).title("Bill Miller, Store #18"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.4387810,-97.6702570)).title("Bill Miller, Store #19"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4430680,-98.4989340)).title("Bill Miller, Store #20"));
        //21?
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4210780,-98.4979820)).title("Bill Miller, Store #22"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3792600,-98.5123470)).title("Bill Miller, Store #23"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5477210,-98.4087760)).title("Bill Miller, Store #24"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3571920,-98.5547000)).title("Bill Miller, Store #25"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5638840,-98.4804180)).title("Bill Miller, Store #26"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5274460,-98.5654380)).title("Bill Miller, Store #27"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4399130,-98.4608600)).title("Bill Miller, Store #28"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5628000,-98.5379340)).title("Bill Miller, Store #29"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27.7367470,-97.4258830)).title("Bill Miller, Store #30"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27.7976840,-97.4525790)).title("Bill Miller, Store #31"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27.6952150,-97.3434970)).title("Bill Miller, Store #32"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4132360,-98.4794950)).title("Bill Miller, Store #33"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5447730,-98.2880030)).title("Bill Miller, Store #34"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.3292290,-97.6589330)).title("Bill Miller, Store #35"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27.6923190,-97.3989050)).title("Bill Miller, Store #36"));
        //37?
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5247860,-98.5993940)).title("Bill Miller, Store #38"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5777650,-98.4407270)).title("Bill Miller, Store #39"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5154140,-98.4921320)).title("Bill Miller, Store #40"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4794720,-98.6586490)).title("Bill Miller, Store #41"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4854820,-98.5354160)).title("Bill Miller, Store #42"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4529260,-98.6290850)).title("Bill Miller, Store #43"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4679260,-98.4631850)).title("Bill Miller, Store #44"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4252640,-98.4939200)).title("Bill Miller, Store #45"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5187340,-98.6376320)).title("Bill Miller, Store #46"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.6084230,-98.4700140)).title("Bill Miller, Store #47"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5359420,-98.3306790)).title("Bill Miller, Store #48"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.2413480,-98.7771110)).title("Bill Miller, Store #49"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5882190,-98.6302610)).title("Bill Miller, Store #50"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4287760,-98.4916100)).title("Bill Miller, Store #51"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5438950,-98.5801810)).title("Bill Miller, Store #52"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3970580,-98.4956280)).title("Bill Miller, Store #53"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5632040,-98.5892470)).title("Bill Miller, Store #54"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5996650,-98.2756510)).title("Bill Miller, Store #55"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4362045,-98.7100942)).title("Bill Miller, Store #56")); //needed slight updating from physical address
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3402510,-98.6236680)).title("Bill Miller, Store #57"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27.8553290,-97.6292860)).title("Bill Miller, Store #58"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.2201260,-98.4129760)).title("Bill Miller, Store #59"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5608040,-98.6811700)).title("Bill Miller, Store #60"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.1450440,-98.1581880)).title("Bill Miller, Store #61"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4963026,-98.7090911)).title("Bill Miller, Store #62")); //needed slight updating
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.2345520,-98.5823800)).title("Bill Miller, Store #63"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.1676220,-97.7899420)).title("Bill Miller, Store #64"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(30.0890610,-97.8230230)).title("Bill Miller, Store #65"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.9568510,-98.4835850)).title("Bill Miller, Store #66"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3671020,-98.2427850)).title("Bill Miller, Store #67"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.5816900,-97.9949020)).title("Bill Miller, Store #68"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.4628400,-98.6904170)).title("Bill Miller, Store #69"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.3563460,-98.8719210)).title("Bill Miller, Store #70"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.6915560,-98.4517760)).title("Bill Miller, Store #71"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.7015246,-98.0947030)).title("Bill Miller, Store #72")); //needed slight updating
        mMap.addMarker(new MarkerOptions().position(new LatLng(29.675344,-98.636394)).title("Bill Miller, Store #73"));

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        Button btnRestaurant = (Button) findViewById(R.id.btnRestaurant);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            String Restaurant = "restaurant";
            @Override
            public void onClick(View v) {
                Toast.makeText(MapsActivity.this,"Nearby Restaurants", Toast.LENGTH_LONG).show();
            }
        });

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        Toast.makeText(MapsActivity.this,"Your Current Location", Toast.LENGTH_LONG).show();

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

}