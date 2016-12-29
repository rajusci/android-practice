package com.example.raju.androidpractice.sqlite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.raju.androidpractice.R;
import com.example.raju.androidpractice.general.GeneralMethods;

import java.util.List;

/**
 * Created by raju on 12/26/16.
 */

public class SQLiteContactsActivity extends Activity {

    // declare contact name
    private TextView txtName;

    // declare phone number
    private TextView txtPhoneNumber;

    // declare submit button
    private Button btnSubmit;

    private GeneralMethods generalMethods = new GeneralMethods();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // set content view of the layout
        setContentView(R.layout.activity_sqlite_contacts);

        initialize();

        //DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD operations
         */
        // Inserting Contacts
        /*Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Raju", "9100000000"));
        db.addContact(new Contact("malar", "9199999999"));
        db.addContact(new Contact("priya", "9522222222"));
        db.addContact(new Contact("nanthini", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllcontacts();

        for(Contact cn : contacts) {

            String record = "id:" + cn.getmId() + ",Name:" + cn.getmName() + ",phone number:" + cn.getmPhoneNumber();

            Log.d("rows", record);
        }*/
    }

    /**
     * Method to initialize the ui components
     */
    public void initialize(){

        // initialize contact name
        txtName = (TextView)findViewById(R.id.txtName);

        // initialize phone number
        txtPhoneNumber = (TextView)findViewById(R.id.txtPhoneNumber);

        // initialize submit button
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveContactDetails();

            }
        });

    }

    public void saveContactDetails(){

        // get contact name
        String name = txtName.getText().toString().trim();

        // get phone number
        String phoneNumber = txtPhoneNumber.getText().toString().trim();

        DatabaseHandler db = new DatabaseHandler(this);

        db.addContact(new Contact(name, phoneNumber));

        generalMethods.showToastMessage(this, "Saved successfully...");

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllcontacts();

        for(Contact cn : contacts) {

            String record = "id:" + cn.getmId() + ",Name:" + cn.getmName() + ",phone number:" + cn.getmPhoneNumber();

            Log.d("rows", record);
        }
    }
}
