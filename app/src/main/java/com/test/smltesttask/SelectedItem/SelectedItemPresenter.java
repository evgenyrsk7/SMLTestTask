package com.test.smltesttask.SelectedItem;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.test.smltesttask.Main.MainModel;
import com.test.smltesttask.R;

/**
 * Created by evgen on 11.06.2017.
 */

public class SelectedItemPresenter  {

    private SelectedItemView selectedItemView;
    private Context mContext;
    private Activity mActivity;
    private TextView indexOfItem;
    private double buttonFillDegree;

    SelectedItemPresenter(SelectedItemView selectedItemView, Context context, Activity activity) {
        this.selectedItemView = selectedItemView;
        this.mContext = context;
        this.mActivity = activity;

        indexOfItem = (TextView) this.selectedItemView.findViewById(R.id.selected_item_txt);
    }

    public void showItem(MainModel itemToShow) {
        indexOfItem.setText(String.valueOf(itemToShow.index));

        //ЗАПОЛНЕНИЕ КНОПКИ
    }

}
