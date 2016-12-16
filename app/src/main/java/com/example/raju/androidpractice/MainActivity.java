package com.example.raju.androidpractice;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.raju.androidpractice.alertdialog.AlertDialogTestFragment;
import com.example.raju.androidpractice.flashlight.FlashLightActivity;
import com.example.raju.androidpractice.general.GeneralMethods;
import com.example.raju.androidpractice.places.FindPlacesActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // declare flash light
    private Button btnFlashLight;

    // declare find palaces button
    private Button btnFindPlaces;

    // declare alert dialog
    private Button btnAlertDialog;

    private EditText fromDate;

    private static final int ALERT_DIALOG = 1;

    //Declare DatePickerDialog for filter
    private DatePickerDialog dtpFilterDialog;

    //Date format for app
    private SimpleDateFormat dateFormatter;

    private GeneralMethods generalMethods = new GeneralMethods();

    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    private Stack<Integer> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get fragment manager
        fragmentManager = getSupportFragmentManager();

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

        // initialize alert dialog
        btnAlertDialog = (Button)findViewById(R.id.btnAlertDialog);

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

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //showDialog(ALERT_DIALOG);

                // moveToFragment();

                showAlertDialog();
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

    public void moveToFragment(){

        // Create new fragment and transaction
        Fragment fragment = new AlertDialogTestFragment();
        fragmentTransaction = fragmentManager.beginTransaction();

        if(fragment!=null){

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            fragmentTransaction.replace(R.id.layHomeContent, fragment);
            fragmentTransaction.addToBackStack(null);

            // Commit the transaction
            fragmentTransaction.commit();

        }

    }

    /*@Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog dialogDetails = null;

        switch (id) {
            case ALERT_DIALOG:
                LayoutInflater inflater = LayoutInflater.from(this);
                View dialogview = inflater.inflate(R.layout.alert_dialog_window, null);
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
                dialogbuilder.setTitle("Filter by date");
                dialogbuilder.setView(dialogview);
                dialogbuilder.setIcon(R.drawable.icon_transact_filter);
                dialogDetails = dialogbuilder.create();
                break;
        }
        return dialogDetails;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case ALERT_DIALOG:
                final AlertDialog alertDialog = (AlertDialog) dialog;
                final Button btnFilter = (Button) alertDialog
                        .findViewById(R.id.btnFilter);

                fromDate = (EditText) alertDialog
                        .findViewById(R.id.txtFromDate);

                fromDate.setInputType(InputType.TYPE_NULL);

                fromDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dtpFilterDialog.show();
                    }
                });

                Calendar newCalendar = Calendar.getInstance();

                dtpFilterDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        fromDate.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


                btnFilter.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           String name = fromDate.getText().toString();
                                                           Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                                                       }
                                                       });
                }
        }*/



    /**
     * Method to show alert dialog
     */
    public void showAlertDialog(){

        View view = View.inflate(this, R.layout.alert_dialog_window, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        alert.setTitle("Filter by date");

        fromDate  = (EditText)view.findViewById(R.id.txtFromDate);

        fromDate.setInputType(InputType.TYPE_NULL);

        //instantiate app date formatter
        dateFormatter = generalMethods.getAppDateFormat();

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dtpFilterDialog.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();

        dtpFilterDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        // Setting Positive "OK" Button
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String fDate = fromDate.getText().toString();

                // Change transaction date format
                try{

                    final String OLD_FORMAT = "dd-MMM-yyyy";
                    final String NEW_FORMAT = "yyyy-MM-dd";

                    String oldDateString = fDate;
                    String newDateFormat = "";

                    SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
                    Date d = sdf.parse(oldDateString);
                    sdf.applyPattern(NEW_FORMAT);
                    newDateFormat = sdf.format(d);

                    generalMethods.showToastMessage(MainActivity.this,newDateFormat);

                }catch (ParseException e){

                    e.printStackTrace();
                }

            }
        });

        AlertDialog alertDialog = alert.create();

        try {
            alertDialog.show();
        } catch (Exception e) {
            // WindowManager$BadTokenException will be caught and the app would
            // not display the 'Force Close' message
            e.printStackTrace();
        }
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
