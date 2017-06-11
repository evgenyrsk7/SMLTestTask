package com.test.smltesttask.Settings;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.test.smltesttask.R;

/**
 * Created by evgen on 11.06.2017.
 */

class SettingsPresenter {

    private SettingsView settingsView;
    private Context mContext;
    private Activity mActivity;
    private RecyclerView settingsRecyclerView;

    SettingsPresenter(SettingsView settingsView, Context context, Activity activity) {
        this.settingsView = settingsView;
        this.mActivity = activity;
        this.mContext = context;
    }

    public void fillHistory() {
        settingsRecyclerView = (RecyclerView) settingsView.findViewById(R.id.settings_view_recycler_view);

    }


}
