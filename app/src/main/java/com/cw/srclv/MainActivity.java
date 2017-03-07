package com.cw.srclv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cw.srclv.ad.CardRecordAdapter;
import com.cw.srclv.bean.Consumption;
import com.cw.srclv.rclv.RefreshRecyclerView;
import com.cw.srclv.rclv.iterface.Action;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RefreshRecyclerView mRecyclerView;
    private CardRecordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        mAdapter = new CardRecordAdapter(this);
        mAdapter.addAll(getVirtualData());

        final TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 48));
        textView.setTextSize(16);
        textView.setText("共1条数据");
        mAdapter.setHeader(textView);

        mRecyclerView = (RefreshRecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setSwipeRefreshColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setRefreshAction(new Action() {
            @Override
            public void onAction() {
                getData(true);
            }
        });

//        mRecyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                mRecyclerView.showSwipeRefresh();
//                getData(true);
//            }
//        });


    }

    public void getData(final boolean isRefresh) {
        mAdapter.clear();
        mAdapter.addAll(getVirtualData());
        mRecyclerView.dismissSwipeRefresh();
    }

    public Consumption[] getVirtualData() {
        return new Consumption[]{
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼")
        };
    }


}
