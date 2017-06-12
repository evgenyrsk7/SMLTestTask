package com.test.smltesttask.Settings;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

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
     *
     * @param settingsView - a view of the settings activity
     */
    SettingsPresenter(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    /**
     * Filling the recycler view with the changes history
     */
    void fillComponents() {
        historyItems = DataHolder.readItemsArrayFromFile(settingsView, "history");
        if (historyItems != null)
            Collections.sort(historyItems, new ItemsIndexComparator());
        DataHolder.setSettingsHistory(historyItems);

        settingsView.getSettingsRecyclerView().addItemDecoration(getMainViewDividerItemDecoration());
        settingsView.getSettingsRecyclerView().setLayoutManager(getLinearLayoutManager());
        settingsHistoryRecyclerViewAdapter = new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? new ArrayList<ItemModel>() : DataHolder.getSettingsHistory(), settingsView.getApplicationContext());
        settingsView.getSettingsRecyclerView().setAdapter(settingsHistoryRecyclerViewAdapter);

        ClearFocusFromEditText();
    }

    /**
     * Getter
     *
     * @return - returns a layout manager for a recycler view
     */
    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(settingsView.getApplicationContext());
    }

    /**
     * Getter
     *
     * @return - returns a divider item decoration for dividing items
     */
    private DividerItemDecoration getMainViewDividerItemDecoration() {
        return new DividerItemDecoration(settingsView.getApplicationContext());
    }

    /**
     * Handling a "OK" click event to accept changes of the item
     */
    void onClickedOkButton() {
        int itemIndex = Integer.parseInt(settingsView.getRowEditText().getText().toString().equals("") ? String.valueOf(-1) : settingsView.getRowEditText().getText().toString());
        double fillDegree = Double.parseDouble(settingsView.getFillDegreeEditText().getText().toString().equals("") ? String.valueOf(-1) : settingsView.getFillDegreeEditText().getText().toString());
        double notFilled = -1;
        double boundOfFillDegree = 1;
        boolean replaced = false;

        boolean isValidValues;
        boolean isFilled;

        
        ArrayList<ItemModel> items = DataHolder.getItemsArray();
        isValidValues = itemIndex < items.size() && fillDegree <= boundOfFillDegree;
        isFilled = !(itemIndex == notFilled || fillDegree == notFilled);

        /* Replace an existed item with the new item if the put index isn't out from range */
        if (isValidValues) {
            items.set(itemIndex, new ItemModel(itemIndex, fillDegree));
            DataHolder.setItemsArray(items);
        } else {
            new Toast(settingsView.getApplicationContext());
            if (!isFilled) {
                Toast.makeText(settingsView.getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(settingsView.getApplicationContext(), "Invalid values entered", Toast.LENGTH_SHORT).show();
            }
        }

        if (historyItems == null)
            historyItems = new ArrayList<>();

        /* Replace an existed history item */
        for (int i = 0; i < historyItems.size(); i++) {
            if (historyItems.get(i).getIndex() == itemIndex && isValidValues) {
                historyItems.set(i, new ItemModel(itemIndex, fillDegree));
                replaced = true;
                break;
            }
        }

        /* Add a history item if there wasn't replace and the entered values isn't out from range */
        if (!replaced && isValidValues)
            historyItems.add(new ItemModel(itemIndex, fillDegree));

        DataHolder.setSettingsHistory(historyItems);
        DataHolder.recordItemsArrayToFile(settingsView, "history");

        /* Update recycler view after changes */
        settingsHistoryRecyclerViewAdapter = new SettingsHistoryRecyclerViewAdapter(DataHolder.getSettingsHistory() == null ? new ArrayList<ItemModel>() : DataHolder.getSettingsHistory(), settingsView.getApplicationContext());
        settingsView.getSettingsRecyclerView().setAdapter(settingsHistoryRecyclerViewAdapter);
        settingsHistoryRecyclerViewAdapter.notifyDataSetChanged();
    }


    /**
     * Clear focus from edit text
     */
    private void ClearFocusFromEditText() {
        settingsView.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
