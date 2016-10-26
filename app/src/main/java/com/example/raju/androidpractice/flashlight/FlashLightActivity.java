package com.example.raju.androidpractice.flashlight;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import com.example.raju.androidpractice.R;

import java.security.Policy;

/**
 * Created by Raju on 10/20/2016.
 */

public class FlashLightActivity extends Activity {

    private Context context;

    // declare flash light image button
    private ImageButton imgFlashLight;

    private boolean hasFlash;
    private Camera camera;
    private Parameters params;
    private boolean isFlashOn;
    private MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);

        context = FlashLightActivity.this;

        initialize();
    }

    // Method to initialize ui components
    protected void initialize(){

        // initialize flash light image button
        imgFlashLight = (ImageButton)findViewById(R.id.imgFlashLight);

        imgFlashLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFlashOn){

                    // turn off flash
                    turnOffFlash();

                }else {

                    // turn on flash
                    turnOnFlash();
                }
            }
        });

        // First check if device is supporting flash light or not
        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(getPackageManager().FEATURE_CAMERA_FLASH);

        if(!hasFlash){

            // device doesn't support flash
            // Show alert message and close the application
            AlertDialog alert = new AlertDialog.Builder(context)
                    .create();
            alert.setTitle("Error");
            alert.setMessage("Sorry, your device doesn't support flash light!");
            alert.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                }
            });
            alert.show();
            return;

        }

        // get the camera
        getCamera();

        // displaying button image
        toggleButtonImage();

    }

    /**
     * Method to get camera
     */
    public void getCamera(){

        if(camera == null){

            try{

                camera = Camera.open();
                params = camera.getParameters();

            }catch (RuntimeException e){

                Log.e(getString(R.string.err_camera_open), e.getMessage());
            }
        }
    }

    /**
     * Method to turning on flash
     */
    public void turnOnFlash(){

        if(!isFlashOn){

            if(camera == null || params == null){

                return;
            }

            // play sound
            //playSound();

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;

            // changing button/switch image
            toggleButtonImage();
        }
    }

    /**
     * Method to turning off flash
     */
    public void turnOffFlash(){

        if(isFlashOn){

            if(camera == null || params == null){

                return;
            }

            // play sound
            //playSound();

            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = false;

            // changing button/switch image
            toggleButtonImage();
        }

    }

    /**
     * Playing sound
     * will play button toggle sound on flash on / off
     */
    public void playSound(){

        if(isFlashOn){

            mp = MediaPlayer.create(context, R.raw.light_switch_off);

        }else {

            mp = MediaPlayer.create(context, R.raw.light_switch_on);
        }

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                // TODO Auto-generated method stub
                mp.release();
            }
        });
        mp.start();

    }

    /*
    * Toggle switch button images
    * changing image states to on / off
    * */
    public void toggleButtonImage(){

        if(isFlashOn){

            imgFlashLight.setImageResource(R.drawable.flash_on);

        }else {

            imgFlashLight.setImageResource(R.drawable.flash_off);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // on pause turn off the flash
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // on resume turn on the flash
        if(hasFlash)
            turnOnFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // on starting the app get the camera params
        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // on stop release the camera
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
