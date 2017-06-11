package com.test.smltesttask.SelectedItem;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;


import com.test.smltesttask.R;

import java.util.ArrayList;

/**
 * Created by evgen on 11.06.2017.
 */

public class SelectedItemView extends Activity {

    private int indexSelectedItem;
    private SelectedItemPresenter selectedItemPresenter;
    private Context mContext;
    private Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item_view);

        mActivity = this;
        mContext = this;

        Bundle toReceive = getIntent().getExtras();
        if (toReceive != null) {
            indexSelectedItem = toReceive.getInt("selectedItem");
        }

        selectedItemPresenter = new SelectedItemPresenter(this, mContext, mActivity);
        selectedItemPresenter.showItem(indexSelectedItem);

    }


}
