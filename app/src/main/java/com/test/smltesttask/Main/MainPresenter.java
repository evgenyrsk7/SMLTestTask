package com.test.smltesttask.Main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.test.smltesttask.DataHolder;
import com.test.smltesttask.ItemsRecyclerViewAdapter.ItemsRecyclerViewAdapter;
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

    MainPresenter(MainView view, Context context, Activity activity) {
        this.mainView = view;
        this.mContext = context;
        this.mActivity = activity;

        itemsRecyclerView = (RecyclerView) this.mainView.findViewById(R.id.items_recycler_view);
        itemsRecyclerView.addItemDecoration(getMainViewDividerItemDecoration());
        itemsRecyclerView.setLayoutManager(getLinearLayoutManager());
    }

    @Override
    public void onItemClicked(int selectedItem) {
        mainView.navigateToSelectedItem(selectedItem);
    }

    @Override
    public void fillView(ArrayList<MainModel> items) {
        itemsRecyclerView.setAdapter(new ItemsRecyclerViewAdapter(items, mContext, this));
        saveItemsArray(items);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    private void saveItemsArray(ArrayList<MainModel> items) {
        DataHolder.setData(items);
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
