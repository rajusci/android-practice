package com.example.raju.androidpractice.places;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import com.ui.controller.sliding.DrawerLayoutListener;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.raju.androidpractice.R;
import com.example.raju.androidpractice.flashlight.FlashLightActivity;

/**
 * Created by Raju on 10/26/2016.
 */

public class FindPlacesActivity extends Activity {

    private Context context;

    private FloatingActionButton fabAddPlaces;

    private LinearLayout layFindPlaceContainer;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_places);

        context = FindPlacesActivity.this;

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        initialize();
    }

    /**
     * Method to initialize all ui components
     */
    public void initialize(){

        // initialize Linear layout
        layFindPlaceContainer = (LinearLayout)findViewById(R.id.layFindPlaceContainer);

        Snackbar.make(layFindPlaceContainer, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();

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

    }

    /**
     * Method to open the naviagtion drawer
     * when the fab button clicked
     */
    public void openNavigationDrawer(){

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.LEFT);

    }
}
