package com.test.smltesttask.SelectedItem;

import android.graphics.Canvas;

import com.test.smltesttask.ColorBarDrawable;
import com.test.smltesttask.DataHolder;
import com.test.smltesttask.Models.ItemModel;

import java.util.ArrayList;

/**
 * Created by evgen on 11.06.2017.
 */

class SelectedItemPresenter  {

    private SelectedItemView selectedItemView;

    /**
     * Constructor
     * @param selectedItemView - a view of the activity which shows the selected item
     */
    SelectedItemPresenter(SelectedItemView selectedItemView) {
        this.selectedItemView = selectedItemView;
    }

    /**
     * Show the selected item separately
     * @param selectedItem - an index of the selected item
     */
    void fillSelectedItem(int selectedItem) {
        ItemModel itemToShow = getItemsArray().get(selectedItem);
        selectedItemView.getIndexOfItem().setText(String.valueOf(itemToShow.getIndex()));

        ColorBarDrawable colorBarDrawable = new ColorBarDrawable(itemToShow, selectedItemView.getApplicationContext());
        Canvas canvas = new Canvas();
        colorBarDrawable.draw(canvas);
        selectedItemView.getRelativeLayoutForButtonBackground().setBackground(colorBarDrawable);
    }

    /**
     * Getter
     * @return - returns an array of the items from DataHolder's static field
     */
    private ArrayList<ItemModel> getItemsArray() {
        return DataHolder.getItemsArray();
    }


}
