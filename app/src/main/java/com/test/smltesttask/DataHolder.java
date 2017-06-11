package com.test.smltesttask;

import com.test.smltesttask.Main.MainModel;

import java.util.ArrayList;

/**
 * Created by evgen on 11.06.2017.
 */

public class DataHolder {
    private static ArrayList<MainModel> items;

    public static ArrayList<MainModel> getItemsArray() {
        return items;
    }

    public static void setItemsArray(ArrayList<MainModel> items) {
        DataHolder.items = items;
    }

    private static ArrayList<MainModel> settingsHistory;

    public static ArrayList<MainModel> getSettingsHistory() {
        return settingsHistory;
    }

    public static void setSettingsHistory(ArrayList<MainModel> settingsHistory) {
        DataHolder.settingsHistory = settingsHistory;
    }
}
