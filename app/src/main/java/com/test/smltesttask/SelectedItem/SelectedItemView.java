package com.test.smltesttask.SelectedItem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.test.smltesttask.R;

/**
 * Created by evgen on 11.06.2017.
 */

public class SelectedItemView extends Activity {

    private int indexSelectedItem;
    private SelectedItemPresenter selectedItemPresenter;
    private TextView indexOfItem;
    private RelativeLayout relativeLayoutForButtonBackground;

    /**
     * Initialization of the selected item's activity components
     * @param savedInstanceState - instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item_view);

        Bundle toReceive = getIntent().getExtras();
        if (toReceive != null) {
            indexSelectedItem = toReceive.getInt("selectedItem");
        }

        indexOfItem = (TextView) findViewById(R.id.selected_item_view_txt);
        relativeLayoutForButtonBackground = (RelativeLayout) findViewById(R.id.selected_item_view_relative_layout_btn);

        selectedItemPresenter = new SelectedItemPresenter(this);
        selectedItemPresenter.showItem(indexSelectedItem);
    }

    /**
     * Getter
     * @return - returns an index of the selected item
     */
    public TextView getIndexOfItem() {
        return indexOfItem;
    }

    /**
     * Getter
     * @return - returns a relative layout for filling button's background
     */
    public RelativeLayout getRelativeLayoutForButtonBackground() {
        return relativeLayoutForButtonBackground;
    }


}
