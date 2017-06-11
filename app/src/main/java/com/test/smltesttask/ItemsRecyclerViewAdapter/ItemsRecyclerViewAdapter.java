package com.test.smltesttask.ItemsRecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.smltesttask.Main.MainModel;
import com.test.smltesttask.R;

import java.util.ArrayList;


/**
 * Created by evgen on 09.06.2017.
 */

public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<MainModel> items;
    private Context mContext;

    public ItemsRecyclerViewAdapter(ArrayList<MainModel> items, Context context) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public ItemsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainModel item = items.get(position);
        holder.index.setText(String.valueOf(item.index));

        //holder.buttonToFill.setBac
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView index;
        private Button buttonToFill;
        private ImageView arrow;

        public ViewHolder(View itemView) {
            super(itemView);
            index = (TextView) itemView.findViewById(R.id.recycler_view_item_index);
            buttonToFill = (Button) itemView.findViewById(R.id.recycler_view_item_btn);
            arrow = (ImageView) itemView.findViewById(R.id.recycler_view_item_arrow);
        }
    }
}
