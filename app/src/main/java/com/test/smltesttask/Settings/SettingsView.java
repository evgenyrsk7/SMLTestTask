package com.test.smltesttask.Settings;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.test.smltesttask.R;

/**
 * Created by evgen on 10.06.2017.
 */

public class SettingsView extends Activity {

    private SettingsPresenter settingsPresenter;
    private RecyclerView settingsRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_view);

        settingsRecyclerView = (RecyclerView) findViewById(R.id.settings_recycler_view);

        settingsPresenter = new SettingsPresenter(this);
        settingsPresenter.fillHistory();

    }

    public RecyclerView getSettingsRecyclerView() {
        return settingsRecyclerView;
    }
}
