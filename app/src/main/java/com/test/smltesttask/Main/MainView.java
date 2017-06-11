package com.test.smltesttask.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.test.smltesttask.R;
import com.test.smltesttask.SelectedItem.SelectedItemView;
import com.test.smltesttask.Settings.SettingsView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainView extends Activity implements MainViewInterface {

    private Activity mActivity;
    private Context mContext;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
        mContext = this;


        mainPresenter = new MainPresenter(this, mContext, mActivity);
        mainPresenter.fillView();
        //fillItems(arrayOfItems);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void navigateToSettings() {
        startActivity(new Intent(this, SettingsView.class));
        //finish();
    }

    @Override
    public void navigateToSelectedItem(int selectedItem) {
        Bundle toPass = new Bundle();
        toPass.putInt("selectedItem", selectedItem);
        startActivity(new Intent(this, SelectedItemView.class).putExtras(toPass));
        //finish();
    }

}
