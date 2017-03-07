package com.cw.srclv.rclv.itemdecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chao.wang on 2017/3/6.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int top;
    private int left;
    private int right;
    private int bottom;

    public SpaceItemDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = left;
        outRect.top = top;
        outRect.right = right;
        outRect.bottom = bottom;
    }
}

