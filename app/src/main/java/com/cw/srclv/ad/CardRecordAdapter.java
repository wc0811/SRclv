package com.cw.srclv.ad;

import android.content.Context;
import android.view.ViewGroup;

import com.cw.srclv.bean.Consumption;
import com.cw.srclv.holder.CardRecordHolder;
import com.cw.srclv.rclv.ad.RecyclerAdapter;
import com.cw.srclv.rclv.viewholder.BaseViewHolder;


public class CardRecordAdapter extends RecyclerAdapter<Consumption> {

    public CardRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<Consumption> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new CardRecordHolder(parent);
    }
}