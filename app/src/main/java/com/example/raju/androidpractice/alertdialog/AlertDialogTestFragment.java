package com.example.raju.androidpractice.alertdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.raju.androidpractice.R;

/**
 * Created by Raju on 11/7/2016.
 */

public class AlertDialogTestFragment extends Fragment {

    private View rootView;

    private LayoutInflater currInflater;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get context
        context = getActivity();

        // Get Inflator
        currInflater = inflater;

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_alert_dialog, container, false);

        // Return root view
        return rootView;

    }

}
