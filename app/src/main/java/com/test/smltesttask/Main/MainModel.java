package com.test.smltesttask.Main;

/**
 * Created by evgen on 09.06.2017.
 */

public class MainModel {
    private int index;
    private double fill;

    public MainModel(int index, double fill) {
        this.index = index;
        this.fill = fill;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getFill() {
        return fill;
    }

    public void setFill(double fill) {
        this.fill = fill;
    }
}
