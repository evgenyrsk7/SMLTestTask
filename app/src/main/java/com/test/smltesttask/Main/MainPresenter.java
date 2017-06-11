package com.test.smltesttask.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;


import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Adapters.ItemsRecyclerViewAdapter;
import com.test.smltesttask.DividerItemDecoration;
import com.test.smltesttask.Models.ItemModel;
import com.test.smltesttask.ItemsIndexComparator;
import com.test.smltesttask.SelectedItem.SelectedItemView;
import com.test.smltesttask.Settings.SettingsView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by evgen on 10.06.2017.
 */

public class MainPresenter {

    private MainView mainView;
    /** adapter for items */
    private ItemsRecyclerViewAdapter itemsRecyclerViewAdapter;

    /**
     * Constructor
     * @param mainView - a view of the main activity (shows all 100 items)
     */
    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Fill view's components
     */
    void fillView() {
        ArrayList<ItemModel> arrayOfItems;
        if (DataHolder.readItemsArrayFromFile(mainView, "items") == null)
            arrayOfItems = createItems();
        else
            arrayOfItems = DataHolder.readItemsArrayFromFile(mainView, "items");
        if (arrayOfItems != null) {
            Collections.sort(arrayOfItems, new ItemsIndexComparator());
        }

        mainView.getItemsRecyclerView().addItemDecoration(getMainViewDividerItemDecoration());
        mainView.getItemsRecyclerView().setLayoutManager(getLinearLayoutManager());
        itemsRecyclerViewAdapter = new ItemsRecyclerViewAdapter(arrayOfItems, mainView.getApplicationContext(), this);
        mainView.getItemsRecyclerView().setAdapter(itemsRecyclerViewAdapter);

        mainView.getSettingsTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSettings();
            }
        });

        saveItemsArray(arrayOfItems);
    }

    /**
     * Updating the recycler view when some items have been changed
     */
    void updateView() {
        itemsRecyclerViewAdapter.notifyDataSetChanged();
        DataHolder.recordItemsArrayToFile(mainView.getApplicationContext(), "items");
    }

    /**
     * Show the selected item separately
     * @param selectedItem - an index of the selected item
     */
    public void onItemClicked(int selectedItem) {
        Bundle toPass = new Bundle();
        toPass.putInt("selectedItem", selectedItem);
        mainView.startActivity(new Intent(mainView, SelectedItemView.class).putExtras(toPass));
    }

    private void navigateToSettings() {
        mainView.startActivity(new Intent(mainView, SettingsView.class));
    }

    /**
     * Create items if an user has entered first time
     * @return - returns a new array of items when user has entered first time
     */
    private ArrayList<ItemModel> createItems() {
        ArrayList<ItemModel> arrayOfItems = new ArrayList<>();
        int countOfItems = 100;
        for (int i = 0; i < countOfItems; i++) {
            double fillOfItemButton = 0.0;
            ItemModel item = new ItemModel(i, fillOfItemButton);
            arrayOfItems.add(item);
        }
        return arrayOfItems;

    }

    /**
     * Save the items array
     * @param items - all the 100 items for saving at the DataHolder's static field
     */
    private void saveItemsArray(ArrayList<ItemModel> items) {
        DataHolder.setItemsArray(items);
    }

    /**
     * Getter
     * @return - returns a new layout manager for the recyclerView
     */
    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(mainView.getApplicationContext());
    }

    /**
     * Getter
     * @return - returns a new layout manager for the recyclerView
     */
    private DividerItemDecoration getMainViewDividerItemDecoration() {
        return new DividerItemDecoration(mainView.getApplicationContext());
    }
}
