package com.test.smltesttask;

import com.test.smltesttask.Main.MainModel;

import java.util.ArrayList;

/**
 * Created by evgen on 11.06.2017.
 */

public class DataHolder {
    private static ArrayList<MainModel> items;
    public static ArrayList<MainModel> getData() {return items;}
    public static void setData(ArrayList<MainModel> items) {
        DataHolder.items = items;
    }
}
