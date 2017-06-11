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

    /**
     * Initialization of the main activity components
     * @param savedInstanceState - instance state
     */
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

    /**
     * On resume event of the main activity (update components)
     */
    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.updateView();
    }

    /**
     * Getter
     * @return - returns the recyclerView for showing items
     */
    public RecyclerView getItemsRecyclerView() {
        return itemsRecyclerView;
    }

    /**
     * Getter
     * @return - returns the settings text view
     */
    public TextView getSettingsTextView() {
        return settingsTextView;
    }

}
