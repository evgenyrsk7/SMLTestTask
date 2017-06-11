package com.test.smltesttask;

import android.content.Context;

import com.test.smltesttask.Models.ItemModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by evgen on 11.06.2017.
 */

public class DataHolder {

    /**
     * the static field to store all the items
     */
    private static ArrayList<ItemModel> items;

    /**
     * Getter
     * @return - returns the stored items
     */
    public static ArrayList<ItemModel> getItemsArray() {
        return items;
    }

    /**
     * Setter
     * @param items - set items to store in the static field
     */
    public static void setItemsArray(ArrayList<ItemModel> items) {
        DataHolder.items = items;
    }

    /**
     * the static field to store the history items
     */
    private static ArrayList<ItemModel> settingsHistory;

    /**
     * Getter
     * @return - returns the stored history
     */
    public static ArrayList<ItemModel> getSettingsHistory() {
        return settingsHistory;
    }

    /**
     * Setter
     * @param settingsHistory - set history items to store in the static field
     */
    public static void setSettingsHistory(ArrayList<ItemModel> settingsHistory) {
        DataHolder.settingsHistory = settingsHistory;
    }

    /**
     * Save to file
     * @param context - current state
     * @param fileNameString - the name of saving file
     */
    public static void recordItemsArrayToFile(Context context, String fileNameString) {
        File fileName = null;
        String sdState = android.os.Environment.getExternalStorageState();
        String path = context.getApplicationInfo().dataDir;
        fileName = new File(path, fileNameString);
        if (!fileName.exists()) {
            fileName.getParentFile().mkdirs();
        }
        try {

            FileWriter f = new FileWriter(fileName);
            if (fileNameString.equals("items"))
                f.write(convertedFromArrayListToJson(items).toString());
            if (fileNameString.equals("history"))
                f.write(convertedFromArrayListToJson(settingsHistory).toString());
            f.flush();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read from file
     * @param context - current state
     * @param fileNameString - the name of reading file
     * @return - returns the read file
     */
    public static ArrayList<ItemModel> readItemsArrayFromFile(Context context, String fileNameString) {
        try {
            String path = context.getApplicationInfo().dataDir;
            File fileName = new File(path, fileNameString);
            FileInputStream fiStream = new FileInputStream(fileName);
            byte[] bytes = new byte[fiStream.available()];
            fiStream.read(bytes);
            fiStream.close();
            String itemsString = new String(bytes, "UTF-8");
            JSONObject itemsJson = new JSONObject(itemsString);
            if (fileName.equals("items")) {
                DataHolder.items = convertedFromJsonToArrayList(itemsJson);
                return DataHolder.getItemsArray();
            } else {
                DataHolder.settingsHistory = convertedFromJsonToArrayList(itemsJson);
                return DataHolder.getSettingsHistory();
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Convert from array list to JSON object
     * @param itemsArray - an array of the items
     * @return - returns converted object
     */
    private static JSONObject convertedFromArrayListToJson(ArrayList<ItemModel> itemsArray) {
        JSONObject itemsJson = new JSONObject();
        for (ItemModel item : itemsArray) {
            try {
                itemsJson.put(String.valueOf(item.getIndex()), String.valueOf(item.getFill()));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return itemsJson;
    }

    /**
     * Convert from JSON to array list
     * @param itemsJson - JSON file
     * @return - returns converted array list object
     */
    private static ArrayList<ItemModel> convertedFromJsonToArrayList(JSONObject itemsJson) {
        ArrayList<ItemModel> itemsArray = new ArrayList<>();
        Iterator<String> iterator = itemsJson.keys();
        int indexOfItem;
        double fillDegree;
        while (iterator.hasNext()) {
            try {
                indexOfItem = Integer.parseInt(iterator.next());
                fillDegree = Double.parseDouble(itemsJson.getString(String.valueOf(indexOfItem)));
                itemsArray.add(new ItemModel(indexOfItem, fillDegree));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return itemsArray;
    }
}
