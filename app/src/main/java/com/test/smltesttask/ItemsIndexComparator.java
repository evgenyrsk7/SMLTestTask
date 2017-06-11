package com.test.smltesttask;

import com.test.smltesttask.Main.MainModel;

import java.util.Comparator;

/**
 * Created by evgen on 11.06.2017.
 */

public class ItemsIndexComparator implements Comparator<MainModel> {

    @Override
    public int compare(MainModel left, MainModel right) {
        return left.getIndex() > right.getIndex() ? 1 : -1;
    }


}
