package com.test.smltesttask.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test.smltesttask.Main.MainModel;
import com.test.smltesttask.R;
import com.test.smltesttask.Settings.SettingsView;

import java.util.ArrayList;

/**
 * Created by evgen on 11.06.2017.
 */

public class SettingsHistoryRecyclerViewAdapter extends RecyclerView.Adapter<SettingsHistoryRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MainModel> historyItems;

    public SettingsHistoryRecyclerViewAdapter(ArrayList<MainModel> historyItems, Context context) {
        this.historyItems = historyItems;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
        return new SettingsHistoryRecyclerViewAdapter.ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainModel item = historyItems.get(position);
        holder.index.setText(String.valueOf(item.getIndex()));
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView index;
        private Button buttonToFill;

        public ViewHolder(View itemView) {
            super(itemView);
            index = (TextView) itemView.findViewById(R.id.recycler_view_item_index);
            buttonToFill = (Button) itemView.findViewById(R.id.recycler_view_item_btn);
        }
    }
}
