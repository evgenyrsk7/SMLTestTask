package com.test.smltesttask.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Adapters.ItemsRecyclerViewAdapter;
import com.test.smltesttask.R;
import com.test.smltesttask.SelectedItem.SelectedItemView;
import com.test.smltesttask.Settings.SettingsView;

import java.util.ArrayList;

/**
 * Created by evgen on 10.06.2017.
 */

public class MainPresenter {

    private MainView mainView;

    private ArrayList<MainModel> arrayOfItems;
    private int countOfItems = 100;
    private double fillOfItemButton = 0.0;
    private ItemsRecyclerViewAdapter itemsRecyclerViewAdapter;

    MainPresenter(MainView view) {
        this.mainView = view;
    }

    void fillView() {
        if (DataHolder.readItemsArrayFromFile(mainView, "items") == null)
            arrayOfItems = createItems();
        else
            arrayOfItems = DataHolder.readItemsArrayFromFile(mainView, "items");

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

    void updateView() {
        itemsRecyclerViewAdapter.notifyDataSetChanged();
        DataHolder.recordItemsArrayToFile(mainView.getApplicationContext(), "items");
    }

    public void onItemClicked(int selectedItem) {
        Bundle toPass = new Bundle();
        toPass.putInt("selectedItem", selectedItem);
        mainView.startActivity(new Intent(mainView, SelectedItemView.class).putExtras(toPass));
        //mainView.finish();
    }

    private void navigateToSettings() {
        mainView.startActivity(new Intent(mainView, SettingsView.class));
        //mainView.finish();
    }

    private ArrayList<MainModel> createItems() {
        ArrayList<MainModel> arrayOfItems = new ArrayList<>();
        for (int i = 0; i < countOfItems; i++) {
            MainModel item = new MainModel(i, fillOfItemButton);
            arrayOfItems.add(item);
        }
        return arrayOfItems;

    }

    private void saveItemsArray(ArrayList<MainModel> items) {
        DataHolder.setItemsArray(items);
        /*SharedPreferences.Editor editor = mActivity.getPreferences(Activity.MODE_PRIVATE).edit();
        editor.putString("items", items.toString());
        editor.commit();*/
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(mainView.getApplicationContext());
    }

    private MainViewDividerItemDecoration getMainViewDividerItemDecoration() {
        return new MainViewDividerItemDecoration(mainView.getApplicationContext());
    }
}
