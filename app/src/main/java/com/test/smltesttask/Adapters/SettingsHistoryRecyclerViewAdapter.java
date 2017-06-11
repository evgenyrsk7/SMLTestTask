package com.test.smltesttask.Adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.smltesttask.ColorBarDrawable;
import com.test.smltesttask.Models.ItemModel;
import com.test.smltesttask.R;

import java.util.ArrayList;

/**
 * Created by evgen on 11.06.2017.
 */

public class SettingsHistoryRecyclerViewAdapter extends RecyclerView.Adapter<SettingsHistoryRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ItemModel> historyItems;

    /**
     * Constructor
     * @param historyItems - an array of the changes history
     * @param context - the current state
     */
    public SettingsHistoryRecyclerViewAdapter(ArrayList<ItemModel> historyItems, Context context) {
        this.historyItems = historyItems;
        this.mContext = context;
    }

    /**
     * Creating a new view holder
     * @param parent - a group into which the new recyclerView will be added
     * @param viewType - a type of the new view
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_without_arrow, parent, false);
        return new SettingsHistoryRecyclerViewAdapter.ViewHolder(recyclerView);
    }

    /**
     * Binding the view holder
     * @param holder - a components holder of the current item
     * @param position - a position of the adapter (an index of the current item)
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemModel item = historyItems.get(position);
        holder.index.setText(String.valueOf(item.getIndex()));

        ColorBarDrawable colorBarDrawable = new ColorBarDrawable(item, mContext);
        Canvas canvas = new Canvas();
        colorBarDrawable.draw(canvas);
        holder.relativeLayout.setBackground(colorBarDrawable);
    }

    /**
     * Getter
     * @return - returns a size of the handled items
     */
    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView index;
        private RelativeLayout relativeLayout;

        /**
         * Constructor
         * @param itemView - a view of the current item
         */
        public ViewHolder(View itemView) {
            super(itemView);
            index = (TextView) itemView.findViewById(R.id.recycler_view_item_index);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.recycler_view_item_relative_layout_btn);
        }
    }
}
