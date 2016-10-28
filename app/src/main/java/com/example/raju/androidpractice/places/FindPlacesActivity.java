package com.example.raju.androidpractice.places;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.LinearLayout;

import com.example.raju.androidpractice.R;
import com.example.raju.androidpractice.flashlight.FlashLightActivity;

/**
 * Created by Raju on 10/26/2016.
 */

public class FindPlacesActivity extends Activity {

    private Context context;

    private LinearLayout layFindPlaceContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_places);

        context = FindPlacesActivity.this;

        initialize();
    }

    /**
     * Method to initialize all ui components
     */
    public void initialize(){

        layFindPlaceContainer = (LinearLayout)findViewById(R.id.layFindPlaceContainer);

        Snackbar.make(layFindPlaceContainer, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
