package com.ui.controller.sliding;

import android.animation.ObjectAnimator;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * Created by Raju on 10/28/2016.
 */

public class DrawerLayoutListener implements DrawerLayout.DrawerListener {

    private View _contentDrawer;
    private int _idView;

    public DrawerLayoutListener(int idView){

        _idView = idView;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

        if(_contentDrawer == null){

            _contentDrawer = ((View)drawerView.getParent()).findViewById(_idView);
            float moveFactor = (drawerView.getWidth() * slideOffset);
            ObjectAnimator.ofFloat(_contentDrawer, "translationX", moveFactor).setDuration(0).start();
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
