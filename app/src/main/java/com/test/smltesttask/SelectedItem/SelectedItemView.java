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
    private TextView indexOfItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item_view);

        Bundle toReceive = getIntent().getExtras();
        if (toReceive != null) {
            indexSelectedItem = toReceive.getInt("selectedItem");
        }

        indexOfItem = (TextView) findViewById(R.id.selected_item_txt);

        selectedItemPresenter = new SelectedItemPresenter(this);
        selectedItemPresenter.showItem(indexSelectedItem);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public TextView getIndexOfItem() {
        return indexOfItem;
    }


}
