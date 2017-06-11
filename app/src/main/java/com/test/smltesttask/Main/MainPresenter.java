package com.test.smltesttask.Main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.test.smltesttask.ItemsRecyclerViewAdapter.ItemsRecyclerViewAdapter;
import com.test.smltesttask.R;

import java.util.ArrayList;

/**
 * Created by evgen on 10.06.2017.
 */

public class MainPresenter implements MainPresenterInterface {

    private MainView view;
    private RecyclerView itemsRecyclerView;
    private Context mContext;
    private LinearLayoutManager linearLayoutManager;

    MainPresenter(MainView view, Context context) {
        this.view = view;
        this.mContext = context;

        itemsRecyclerView = (RecyclerView) this.view.findViewById(R.id.items_recycler_view);
        itemsRecyclerView.addItemDecoration(new MainViewDividerItemDecoration(mContext));
        itemsRecyclerView.setLayoutManager(getLinearLayoutManager());
    }

    @Override
    public void onItemClicked(int position) {
        //startActivity
    }

    @Override
    public void fillView(ArrayList<MainModel> items) {
        itemsRecyclerView.setAdapter(new ItemsRecyclerViewAdapter(items, mContext));
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    private LinearLayoutManager getLinearLayoutManager() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        return  linearLayoutManager;
    }
}
