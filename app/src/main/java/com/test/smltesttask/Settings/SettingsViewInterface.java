package com.test.smltesttask.Settings;



import com.test.smltesttask.Main.MainModel;

import java.util.LinkedList;

/**
 * Created by evgen on 10.06.2017.
 */

public interface SettingsViewInterface {

    void fillStory(LinkedList<MainModel> items);

    void showForm();

}
