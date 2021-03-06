package com.cw.srclv.rclv.viewholder;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chao.wang on 2017/3/6.
 */

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private final String TAG = "RecyclerView_BaseViewHolder";

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 在ViewHolder中添加初始话View的函数
     *
     * @param parent
     * @param layoutId
     */
    public BaseViewHolder(ViewGroup parent, int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        onInitializeView();
    }

    public void onInitializeView() {

    }

    public <T extends View> T findViewById(@IdRes int resId) {
        return (T) itemView.findViewById(resId);
    }

    public void setData(final T object) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemViewClick(object);
            }
        });
    }

    /**
     * 在SetitemView上添加点击函数
     *
     * @param object
     */
    public void onItemViewClick(T object) {

    }

}