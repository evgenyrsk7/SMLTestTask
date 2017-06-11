package com.test.smltesttask.Settings;

import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;

import com.test.smltesttask.Adapters.SettingsHistoryRecyclerViewAdapter;
import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Main.MainModel;
import com.test.smltesttask.Main.MainViewDividerItemDecoration;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by evgen on 11.06.2017.
 */

class SettingsPresenter {

    private SettingsView settingsView;
    private SettingsHistoryRecyclerViewAdapter settingsHistoryRecyclerViewAdapter;
    private ArrayList<MainModel> historyItems;

    SettingsPresenter(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    void fillHistory() {
        historyItems = DataHolder.readItemsArrayFromFile(settingsView, "history");
        DataHolder.setSettingsHistory(historyItems);

        settingsView.getSettingsRecyclerView().addItemDecoration(getMainViewDividerItemDecoration());
        settingsView.getSettingsRecyclerView().setLayoutManager(getLinearLayoutManager());
        settingsHistoryRecyclerViewAdapter = new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? new ArrayList<MainModel>() : DataHolder.getSettingsHistory(), settingsView.getApplicationContext());
        settingsView.getSettingsRecyclerView().setAdapter(settingsHistoryRecyclerViewAdapter);
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(settingsView.getApplicationContext());
    }

    private MainViewDividerItemDecoration getMainViewDividerItemDecoration() {
        return new MainViewDividerItemDecoration(settingsView.getApplicationContext());
    }

    void onClickedOkButton() {
        int itemIndex = Integer.parseInt(settingsView.getRowEditText().getText().toString());
        double fillDegree = Double.parseDouble(settingsView.getFillDegreeEditText().getText().toString());
        boolean set = false;

        ArrayList<MainModel> items = DataHolder.getItemsArray();
        items.set(itemIndex, new MainModel(itemIndex, fillDegree));
        DataHolder.setItemsArray(items);
        
        if (historyItems == null)
            historyItems = new ArrayList<>();

        for (int i = 0; i < historyItems.size(); i++) {
            if (historyItems.get(i).getIndex() == itemIndex) {
                historyItems.set(i, new MainModel(itemIndex, fillDegree));
                set = true;
                break;
            }
        }
        if (!set)
            historyItems.add(new MainModel(itemIndex, fillDegree));

        DataHolder.setSettingsHistory(historyItems);
        DataHolder.recordItemsArrayToFile(settingsView, "history");

        settingsHistoryRecyclerViewAdapter = new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? new ArrayList<MainModel>() : DataHolder.getSettingsHistory(), settingsView.getApplicationContext());
        settingsView.getSettingsRecyclerView().setAdapter(settingsHistoryRecyclerViewAdapter);
        settingsHistoryRecyclerViewAdapter.notifyDataSetChanged();
    }



}
