package com.example.raju.androidpractice.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raju on 12/27/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All static variables
    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "contactsManager";

    // Table name
    private  static final String TABLE_CONTACTS = "contacts";

    // Table coloumns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create table again
        onCreate(sqLiteDatabase);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) operations
     */
    /**
     * Method to add the new contact in table
     * @param contact object of Contact class
     */
    public void addContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getmName()); // Contact name
        values.put(KEY_PH_NO, contact.getmPhoneNumber()); // phone number

        // Inserting row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // closing database connection
    }

    /**
     * Method to getting single contact
     * @param id  record id
     * @return return the contact as per id
     */
    public Contact getContact(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?", new String[]{String.valueOf(id)},null, null, null, null);

        if(cursor!=null){

            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));

        // return contact
        return contact;
    }

    /**
     * Method to getting all contacts
     */
    public List<Contact> getAllcontacts(){

        List<Contact> contactList = new ArrayList<Contact>();

        //select all query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if(cursor.moveToFirst()){

            do{

                Contact contact = new Contact();
                contact.setmId(Integer.parseInt(cursor.getString(0)));
                contact.setmName(cursor.getString(1));
                contact.setmPhoneNumber(cursor.getString(2));

                // Adding contact to list
                contactList.add(contact);
            }while(cursor.moveToNext());
        }

        // Return contact list
        return contactList;
    }

    /**
     * Method to update the contact
     * @param contact object of the Contact class
     * @return
     */
    public int updateContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getmName());
        values.put(KEY_PH_NO, contact.getmPhoneNumber());

        // Updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "= ?", new String[]{String.valueOf(contact.getmId())});
    }

    /**
     * Method to delete the contact
     * @param contact object of the Contact class
     */
    public void deleteContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACTS, KEY_ID + "= ?", new String[]{String.valueOf(contact.getmId())});
        db.close();
    }

    /**
     * Method to get contacts count
     * @return contacts count
     */
    public int getContactsCount(){

        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}

