package com.test.smltesttask.Settings;

import android.support.v7.widget.LinearLayoutManager;

import com.test.smltesttask.Adapters.SettingsHistoryRecyclerViewAdapter;
import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Main.MainViewDividerItemDecoration;

/**
 * Created by evgen on 11.06.2017.
 */

class SettingsPresenter {

    private SettingsView settingsView;

    SettingsPresenter(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    void fillHistory() {
        settingsView.getSettingsRecyclerView().addItemDecoration(getMainViewDividerItemDecoration());
        settingsView.getSettingsRecyclerView().setLayoutManager(getLinearLayoutManager());
        settingsView.getSettingsRecyclerView().setAdapter(new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? null : DataHolder.getSettingsHistory(), settingsView.getApplicationContext()));
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(settingsView.getApplicationContext());
    }

    private MainViewDividerItemDecoration getMainViewDividerItemDecoration() {
        return new MainViewDividerItemDecoration(settingsView.getApplicationContext());
    }


}
