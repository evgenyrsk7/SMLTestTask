package com.test.smltesttask;

import com.test.smltesttask.Models.ItemModel;

import java.util.Comparator;

/**
 * Created by evgen on 11.06.2017.
 */

public class ItemsIndexComparator implements Comparator<ItemModel> {

    /**
     * Compare two items by index name
     * @param left - the first item's index name
     * @param right - the second item's index name
     * @return - returns 1 if left > right, -1 if left < right (to get sorted array when finished)
     */
    @Override
    public int compare(ItemModel left, ItemModel right) {
        return left.getIndex() > right.getIndex() ? 1 : -1;
    }


}
