package com.test.smltesttask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.test.smltesttask.Models.ItemModel;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Created by evgen on 11.06.2017.
 */

public class ColorBarDrawable extends Drawable {

    private ItemModel item;
    private Context mContext;
    private float width;
    private float height;

    /**
     * Constructor
     * @param item - the current item
     * @param context - the current state
     */
    public ColorBarDrawable(ItemModel item, Context context) {
        this.item = item;
        this.mContext = context;
    }

    /**
     * Drawing the button
     * @param canvas - the component to draw
     */
    @Override
    public void draw(@NonNull Canvas canvas) {

        width = mContext.getResources().getDimension(R.dimen.button_width);
        height = mContext.getResources().getDimension(R.dimen.button_height);

        int[] themeColors = {Color.GREEN, Color.BLACK};

        // draw background gradient
        Paint backgroundPaint = new Paint();
        float barWidth = (float) (width * item.getFill());

        backgroundPaint.setColor(themeColors[0]);
        canvas.drawRect(0, 0, barWidth, height, backgroundPaint);
        backgroundPaint.setColor(themeColors[1]);
        canvas.drawRect(barWidth, 0, width, height, backgroundPaint);

    }

    /**
     * Setter
     * @param alpha - set the transparency
     */
    @Override
    public void setAlpha(int alpha) {
    }

    /**
     * Setter
     * @param cf - a color filter value
     */
    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    /**
     * Getter
     * @return - returns an opacity
     */
    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }


}
