package com.example.raju.androidpractice.general;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.raju.androidpractice.R;

/**
 * Created by Raju on 10/24/2016.
 */

public class GeneralMethods {


    /**
     * Function to show the Toast message on the success
     * We need to pass the context and the passed message
     *
     * @param context - The Activity context
     * @param message - The message to be displayed
     */
    public void showToastMessage(Context context,String message ) {

        // The duration to show the Toast message
        int duration = Toast.LENGTH_LONG;

        // Create the toast object
        Toast toast = Toast.makeText(context, message, duration);

        // Show the toast message
        toast.show();

    }

    /**
     * Function to show the alert message in popup window
     * @param context the activity context
     */
    public void showErrorPopupMessage(Context context){

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle("Error");

        // Setting Dialog Message
        alertDialog.setMessage("Sorry you can't use this QR Code");

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_status_failed);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                return;
            }
        });

        // Showing Alert Message
        alertDialog.show();

    }
}
