package com.example.raju.androidpractice.sqlite;

/**
 * Created by raju on 12/26/16.
 */

public class Contact {

    private int mId;
    private String mName;
    private String mPhoneNumber;

    // empty constructor
    public Contact(){

    }

    // constructor
    public Contact(int mId, String mName, String mPhoneNumber){

        this.mId = mId;
        this.mName = mName;
        this.mPhoneNumber = mPhoneNumber;
    }

    // constructor
    public Contact(String mName, String mPhoneNumber){

        this.mName = mName;
        this.mPhoneNumber = mPhoneNumber;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}
