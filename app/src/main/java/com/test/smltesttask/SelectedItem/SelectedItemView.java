package com.test.smltesttask.SelectedItem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Main.MainModel;
import com.test.smltesttask.Main.MainPresenter;
import com.test.smltesttask.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by evgen on 11.06.2017.
 */

public class SelectedItemView extends Activity {

    private TextView indexOfSelectedItem;
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
        selectedItemPresenter.showItem(getItemWithIndex(indexSelectedItem));

    }

    private MainModel getItemWithIndex(int indexSelectedItem) {
        ArrayList<MainModel> itemsArray = getItemsArray();
        return itemsArray.get(indexSelectedItem);
    }

    private ArrayList<MainModel> getItemsArray() {
        return DataHolder.getData();
    }

}
