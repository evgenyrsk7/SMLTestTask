package com.test.smltesttask.Settings;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.test.smltesttask.Main.MainModel;
import com.test.smltesttask.R;

import java.util.LinkedList;

/**
 * Created by evgen on 10.06.2017.
 */

public class SettingsView extends Activity {

    private Activity mActivity;
    private Context mContext;
    private SettingsPresenter settingsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_view);

        mActivity = this;
        mContext = this;

        settingsPresenter = new SettingsPresenter(this, mContext, mActivity);
        settingsPresenter.fillHistory();

    }

}
