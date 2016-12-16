package com.example.raju.androidpractice.places;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;

import com.android.gps.tracking.GPSTracker;
import com.ui.controller.sliding.DrawerLayoutListener;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raju.androidpractice.R;
import com.example.raju.androidpractice.flashlight.FlashLightActivity;

import java.util.List;
import java.util.Locale;

/**
 * Created by Raju on 10/26/2016.
 */

public class FindPlacesActivity extends Activity {

    private Context context;

    private FloatingActionButton fabAddPlaces;

    private RelativeLayout layFindPlaceContainer;

    private NavigationView navigationView;

    private GPSTracker gps;

    private TextView lblCurrentAddress;

    private String currentLocation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_places);

        context = FindPlacesActivity.this;

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        getCurrentLocation();

        initialize();
    }

    /**
     * Method to initialize all ui components
     */
    public void initialize(){

        // initialize Linear layout
        layFindPlaceContainer = (RelativeLayout) findViewById(R.id.layFindPlaceContainer);

        /*Snackbar.make(layFindPlaceContainer, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();*/

        // initialize floating action button
        fabAddPlaces = (FloatingActionButton)findViewById(R.id.fabAddPlaces);

        fabAddPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNavigationDrawer();

                /*DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

                //drawerLayout.setDrawerListener(new DrawerLayoutListener(R.id.nav_view));

                drawerLayout.openDrawer(Gravity.RIGHT);*/
            }
        });

        /*Animation animationToLeft = new TranslateAnimation(400, -400, 0, 0);
        animationToLeft.setDuration(12000);
        animationToLeft.setRepeatMode(Animation.RESTART);
        animationToLeft.setRepeatCount(Animation.INFINITE);*/

        /*Animation animationToRight = new TranslateAnimation(-400,400, 0, 0);
        animationToRight.setDuration(12000);
        animationToRight.setRepeatMode(Animation.RESTART);
        animationToRight.setRepeatCount(Animation.INFINITE);*/

        lblCurrentAddress = (TextView)findViewById(R.id.lblCurrentAddress);
        lblCurrentAddress.setText(currentLocation);
        lblCurrentAddress.setSelected(true);
        //lblCurrentAddress.setAnimation(animationToLeft);

    }

    /**
     * Method to open the naviagtion drawer
     * when the fab button clicked
     */
    public void openNavigationDrawer(){

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.LEFT);

    }

    /**
     * Method to get the user current location
     */
    public void getCurrentLocation(){

        // create class object
        gps = new GPSTracker(FindPlacesActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            /*try {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                Geocoder geocoder;
                List<Address> addressList;
                geocoder = new Geocoder(this, Locale.getDefault());

                addressList = geocoder.getFromLocation(latitude, longitude, 1);

                String address = addressList.get(0).getAddressLine(0);
                String city = addressList.get(0).getLocality();
                String subLocality = addressList.get(0).getSubLocality();
                String state = addressList.get(0).getAdminArea();
                String subAdminArea = addressList.get(0).getSubAdminArea();
                String thoroughfare = addressList.get(0).getThoroughfare();
                String subThoroughfare = addressList.get(0).getSubThoroughfare();
                String country = addressList.get(0).getCountryName();
                String postalCode = addressList.get(0).getPostalCode();
                String knownName  = addressList.get(0).getFeatureName();
                String premises  = addressList.get(0).getPremises();
                String locale  = addressList.get(0).getLocale().toString();


                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nAddress: " + address
                        + "\nCity: " + city
                        + "\nsubCity: " + subLocality
                        + "\nState: " + state
                        + "\nsubState: " + subAdminArea
                        + "\nthoroughfare: " + thoroughfare
                        + "\nsubThoroughfare: " + subThoroughfare
                        + "\nCountry: " + country
                        + "\nPostalCode: " + postalCode
                        + "\nKnownName: " + knownName
                        + "\nPremises: " + premises
                        + "\nLocale: " + locale
                        , Toast.LENGTH_LONG).show();

                //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

            }catch (Exception e){

                e.printStackTrace();
            }*/


            String strAdd = "";
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses != null) {
                    Address returnedAddress = addresses.get(0);
                    StringBuilder strReturnedAddress = new StringBuilder("");

                    for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");

                        strAdd = strReturnedAddress.toString();
                    }

                    currentLocation = strReturnedAddress.toString();

                    Log.w("My Current loction address", "" + strReturnedAddress.toString());


                } else {
                    Log.w("My Current loction address", "No Address returned!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w("My Current loction address", "Canont get Address!");
            }



        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();

        }

    }
}
