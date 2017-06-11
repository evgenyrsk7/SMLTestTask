package com.test.smltesttask.Models;

/**
 * Created by evgen on 09.06.2017.
 */

public class ItemModel {
    private int index;
    private double fill;

    /** A model of the items
     * @param index - an index name of an item
     * @param fill  - a value of a button's filling degree
     */
    public ItemModel(int index, double fill) {
        this.index = index;
        this.fill = fill;
    }

    /**
     * Getter
     * @return - returns an index name of the item
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter
     * @return - returns a value of the button's filling degree
     */
    public double getFill() {
        return fill;
    }
}
