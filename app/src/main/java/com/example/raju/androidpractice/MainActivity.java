package com.example.raju.androidpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.raju.androidpractice.flashlight.FlashLightActivity;
import com.example.raju.androidpractice.places.FindPlacesActivity;

public class MainActivity extends AppCompatActivity {

    // declare flash light
    private Button btnFlashLight;

    // declare find palaces button
    private Button btnFindPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /**
     * Method to initialize ui components
     */
    protected void initialize(){

        // initialize flash light button
        btnFlashLight = (Button)findViewById(R.id.btnFlashLight);

        // initialize find places button
        btnFindPlaces = (Button)findViewById(R.id.btnFindPlaces);

        btnFlashLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFlashLightActivity();
            }
        });

        btnFindPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFindPlacesActivity();
            }
        });

    }

    /**
     * Method to show the flash light activity
     */
    public void showFlashLightActivity(){

        Intent intent = new Intent(this, FlashLightActivity.class);

        startActivity(intent);

        finish();
    }

    /**
     * Method to show the find places activity
     */
    public void showFindPlacesActivity(){

        Intent intent = new Intent(this, FindPlacesActivity.class);

        startActivity(intent);

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
