package com.test.smltesttask.Settings;

import android.support.v7.widget.LinearLayoutManager;

import com.test.smltesttask.Adapters.SettingsHistoryRecyclerViewAdapter;
import com.test.smltesttask.DataHolder;
import com.test.smltesttask.ItemsIndexComparator;
import com.test.smltesttask.Models.ItemModel;
import com.test.smltesttask.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by evgen on 11.06.2017.
 */

class SettingsPresenter {

    private SettingsView settingsView;
    private SettingsHistoryRecyclerViewAdapter settingsHistoryRecyclerViewAdapter;
    private ArrayList<ItemModel> historyItems;

    /**
     * Constructor
     * @param settingsView - a view of the settings activity
     */
    SettingsPresenter(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    /**
     * Filling the recycler view with the changes history
     */
    void fillHistory() {
        historyItems = DataHolder.readItemsArrayFromFile(settingsView, "history");
        Collections.sort(historyItems, new ItemsIndexComparator());
        DataHolder.setSettingsHistory(historyItems);

        settingsView.getSettingsRecyclerView().addItemDecoration(getMainViewDividerItemDecoration());
        settingsView.getSettingsRecyclerView().setLayoutManager(getLinearLayoutManager());
        settingsHistoryRecyclerViewAdapter = new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? new ArrayList<ItemModel>() : DataHolder.getSettingsHistory(), settingsView.getApplicationContext());
        settingsView.getSettingsRecyclerView().setAdapter(settingsHistoryRecyclerViewAdapter);
    }

    /**
     * Getter
     * @return - returns a layout manager for a recycler view
     */
    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(settingsView.getApplicationContext());
    }

    /**
     * Getter
     * @return - returns a divider item decoration for dividing items
     */
    private DividerItemDecoration getMainViewDividerItemDecoration() {
        return new DividerItemDecoration(settingsView.getApplicationContext());
    }

    /**
     * Handling a "OK" click event to accept changes of the item
     */
    void onClickedOkButton() {
        int itemIndex = Integer.parseInt(settingsView.getRowEditText().getText().toString());
        double fillDegree = Double.parseDouble(settingsView.getFillDegreeEditText().getText().toString());
        boolean set = false;

        ArrayList<ItemModel> items = DataHolder.getItemsArray();
        items.set(itemIndex, new ItemModel(itemIndex, fillDegree));
        DataHolder.setItemsArray(items);

        if (historyItems == null)
            historyItems = new ArrayList<>();

        for (int i = 0; i < historyItems.size(); i++) {
            if (historyItems.get(i).getIndex() == itemIndex) {
                historyItems.set(i, new ItemModel(itemIndex, fillDegree));
                set = true;
                break;
            }
        }
        if (!set)
            historyItems.add(new ItemModel(itemIndex, fillDegree));

        DataHolder.setSettingsHistory(historyItems);
        DataHolder.recordItemsArrayToFile(settingsView, "history");

        settingsHistoryRecyclerViewAdapter = new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? new ArrayList<ItemModel>() : DataHolder.getSettingsHistory(), settingsView.getApplicationContext());
        settingsView.getSettingsRecyclerView().setAdapter(settingsHistoryRecyclerViewAdapter);
        settingsHistoryRecyclerViewAdapter.notifyDataSetChanged();
    }



}
