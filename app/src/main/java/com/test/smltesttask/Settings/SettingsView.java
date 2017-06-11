package com.test.smltesttask.Settings;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.smltesttask.R;

/**
 * Created by evgen on 10.06.2017.
 */

public class SettingsView extends Activity {

    private SettingsPresenter settingsPresenter;
    private RecyclerView settingsRecyclerView;
    private EditText rowEditText;
    private EditText fillDegreeEditText;
    private Button okButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_view);

        settingsRecyclerView = (RecyclerView) findViewById(R.id.settings_recycler_view);

        settingsPresenter = new SettingsPresenter(this);
        settingsPresenter.fillHistory();

        rowEditText = (EditText) findViewById(R.id.settings_view_row_edit_text);

        fillDegreeEditText = (EditText) findViewById(R.id.settings_view_degree_fill_edit_text);

        okButton = (Button) findViewById(R.id.settings_ok_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsPresenter.changeItem();
            }
        });
    }

    public RecyclerView getSettingsRecyclerView() {
        return settingsRecyclerView;
    }

    public EditText getRowEditText() {
        return rowEditText;
    }

    public EditText getFillDegreeEditText() {
        return fillDegreeEditText;
    }
}
