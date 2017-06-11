package com.test.smltesttask;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.test.smltesttask.Main.MainModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

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

    public static ArrayList<MainModel> readItemsArrayFromFile(Context context, String fileNameString) {
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

    private static JSONObject convertedFromArrayListToJson(ArrayList<MainModel> itemsArray) {
        JSONObject itemsJson = new JSONObject();
        for (MainModel item : itemsArray) {
            try {
                itemsJson.put(String.valueOf(item.getIndex()), String.valueOf(item.getFill()));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return itemsJson;
    }

    private static ArrayList<MainModel> convertedFromJsonToArrayList(JSONObject itemsJson) {
        ArrayList<MainModel> itemsArray = new ArrayList<>();
        Iterator<String> iterator = itemsJson.keys();
        int indexOfItem;
        double fillDegree;
        while (iterator.hasNext()) {
            try {
                indexOfItem = Integer.parseInt(iterator.next());
                fillDegree = Double.parseDouble(itemsJson.getString(String.valueOf(indexOfItem)));
                itemsArray.add(new MainModel(indexOfItem, fillDegree));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return itemsArray;
    }
}
