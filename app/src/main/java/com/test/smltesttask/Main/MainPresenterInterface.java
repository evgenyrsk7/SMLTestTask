package com.test.smltesttask.Main;

import java.util.ArrayList;

/**
 * Created by evgen on 09.06.2017.
 */

public interface MainPresenterInterface {

    void onItemClicked(int selectedItem);

    void fillView(ArrayList<MainModel> items);

    void onResume();

    void onDestroy();

}
