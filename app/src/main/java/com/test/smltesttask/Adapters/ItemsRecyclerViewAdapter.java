package com.test.smltesttask.Adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.smltesttask.ColorBarDrawable;
import com.test.smltesttask.Models.ItemModel;
import com.test.smltesttask.Main.MainPresenter;
import com.test.smltesttask.R;

import java.util.ArrayList;


/**
 * Created by evgen on 09.06.2017.
 */

public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ItemModel> items;
    private Context mContext;
    private MainPresenter mainPresenter;

    /**
     * Constructor
     * @param items - the array of the elements to show on RecyclerView
     * @param context - the current state
     * @param mainPresenter - presenter for events handling
     */
    public ItemsRecyclerViewAdapter(ArrayList<ItemModel> items, Context context, MainPresenter mainPresenter) {
        this.items = items;
        this.mContext = context;
        this.mainPresenter = mainPresenter;
    }

    /**
     * Creating a new view holder
     * @param parent - a group into which the new recyclerView will be added
     * @param viewType - a type of the new view
     * @return - returns the new view holder
     */
    @Override
    public ItemsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_with_arrow, parent, false);
        return new ViewHolder(recyclerView);
    }

    /**
     * Binding the view holder
     * @param holder - a components holder of the current item
     * @param position - a position of the adapter (an index of the current item)
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.index.setText(String.valueOf(item.getIndex()));
        holder.index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onItemClicked(holder.getAdapterPosition());
            }
        });

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onItemClicked(holder.getAdapterPosition());
            }
        });

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
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView index;
        private ImageView arrow;
        private RelativeLayout relativeLayout;

        /**
         * Constructor
         * @param itemView - a view of the current item
         */
        public ViewHolder(View itemView) {
            super(itemView);
            index = (TextView) itemView.findViewById(R.id.recycler_view_item_index);
            arrow = (ImageView) itemView.findViewById(R.id.recycler_view_item_arrow);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.recycler_view_item_relative_layout_btn);
        }
    }
}
