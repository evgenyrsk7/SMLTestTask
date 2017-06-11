package com.test.smltesttask.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.test.smltesttask.R;


public class MainView extends Activity {

    private MainPresenter mainPresenter;

    private RecyclerView itemsRecyclerView;
    private TextView settingsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fillItems(arrayOfItems);

        itemsRecyclerView = (RecyclerView) findViewById(R.id.items_recycler_view);
        settingsTextView = (TextView) findViewById(R.id.main_settings_txt);

        mainPresenter = new MainPresenter(this);
        mainPresenter.fillView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.updateView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finishActivity(0);
    }

    public RecyclerView getItemsRecyclerView() {
        return itemsRecyclerView;
    }

    public TextView getSettingsTextView() {
        return settingsTextView;
    }

}
