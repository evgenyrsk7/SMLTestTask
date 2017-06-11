package com.test.smltesttask.Main;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Adapters.ItemsRecyclerViewAdapter;
import com.test.smltesttask.R;

import java.util.ArrayList;

/**
 * Created by evgen on 10.06.2017.
 */

public class MainPresenter implements MainPresenterInterface {

    private MainView mainView;
    private RecyclerView itemsRecyclerView;
    private Context mContext;
    private Activity mActivity;
    private LinearLayoutManager linearLayoutManager;
    private MainViewDividerItemDecoration mainViewDividerItemDecoration;
    private TextView settingsTextView;
    private ArrayList<MainModel> arrayOfItems;
    private int countOfItems = 100;
    private double fillOfItemButton = 0.0;

    MainPresenter(MainView view, Context context, Activity activity) {
        this.mainView = view;
        this.mContext = context;
        this.mActivity = activity;
    }

    @Override
    public void onItemClicked(int selectedItem) {
        mainView.navigateToSelectedItem(selectedItem);
    }

    @Override
    public void fillView() {
        arrayOfItems = createItems();
        itemsRecyclerView = (RecyclerView) this.mainView.findViewById(R.id.items_recycler_view);
        itemsRecyclerView.addItemDecoration(getMainViewDividerItemDecoration());
        itemsRecyclerView.setLayoutManager(getLinearLayoutManager());
        itemsRecyclerView.setAdapter(new ItemsRecyclerViewAdapter(arrayOfItems, mContext, this));

        settingsTextView = (TextView) this.mainView.findViewById(R.id.main_settings_txt);
        settingsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.navigateToSettings();
            }
        });

        saveItemsArray(arrayOfItems);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

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
        linearLayoutManager = new LinearLayoutManager(mContext);
        return  linearLayoutManager;
    }

    private MainViewDividerItemDecoration getMainViewDividerItemDecoration() {
        mainViewDividerItemDecoration = new MainViewDividerItemDecoration(mContext);
        return mainViewDividerItemDecoration;
    }
}
