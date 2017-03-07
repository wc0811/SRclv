package com.cw.srclv.rclv;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cw.srclv.R;
import com.cw.srclv.rclv.ad.RecyclerAdapter;
import com.cw.srclv.rclv.itemdecoration.SpaceItemDecoration;
import com.cw.srclv.rclv.iterface.Action;

/**
 * Created by chao.wang on 2017/3/6.
 */

public class RefreshRecyclerView extends FrameLayout {
    /**
     * 这个是TAG
     */
    private final String TAG = "RefreshRecyclerView";
    /**
     * Google 提供的刷新布局
     */
    private SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * 列表
     */
    private RecyclerView mRecyclerView;
    /**
     * 这个是Adapter
     */
    private RecyclerAdapter mAdapter;
    /**
     * 是否可以刷新
     */
    private boolean refreshAble;
    /**
     * 是否需要加载更多
     */
    private boolean loadMoreAble;

    /**
     * 非常必要的构造函数，
     *
     * @param context
     */
    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    /**
     * 构造函数，不可或缺
     *
     * @param context
     * @param attrs
     */
    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 貌似是可以设置模式的构造函数
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 这个位置设置RecyclerView 布局
         */
        View view = inflate(context, R.layout.view_refresh_recycler, this);
        /**
         * 真正的RecyclerView
         */
        mRecyclerView = (RecyclerView) view.findViewById(R.id.$_recycler_view);
        /**
         * 刷新需要的布局
         */
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.$_refresh_layout);
        /**
         * 自定义属性，不是很了解具体内容，但是应该是没啥问题
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RefreshRecyclerView);
        refreshAble = typedArray.getBoolean(R.styleable.RefreshRecyclerView_refresh_able, true);
        loadMoreAble = typedArray.getBoolean(R.styleable.RefreshRecyclerView_load_more_able, false);
        if (!refreshAble) {
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(RecyclerAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
        mAdapter = adapter;
        mAdapter.loadMoreAble = loadMoreAble;
    }

    /**
     * 设置布局，没看明白设置的是什么布局
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 设置刷新动画
     *
     * @param action
     */
    public void setRefreshAction(final Action action) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.isRefreshing = true;
                action.onAction();
            }
        });
    }

    /**
     * 设置加载更多
     *
     * @param action
     */
    public void setLoadMoreAction(final Action action) {
        Log.i(TAG, "setLoadMoreAction");
        if (mAdapter.isShowNoMore || !loadMoreAble) {
            return;
        }
        mAdapter.loadMoreAble = true;
        mAdapter.setLoadMoreAction(action);
    }

    /**
     * 显示没有更多数据了
     */
    public void showNoMore() {
        mAdapter.showNoMore();
    }

    /**
     * 设置间隔
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setItemSpace(int left, int top, int right, int bottom) {
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(left, top, right, bottom));
    }

    /**
     * 设置中间的分割线
     *
     * @param itemDecoration
     */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * 貌似是获取相应的RecyclerView
     *
     * @return
     */
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 获取整个View
     *
     * @return
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    /**
     * 上啦获取更多数据
     *
     * @return
     */
    public TextView getNoMoreView() {
        return mAdapter.mNoMoreView;
    }

    /**
     * 数组参数
     *
     * @param colors
     */
    public void setSwipeRefreshColorsFromRes(@ColorRes int... colors) {
        mSwipeRefreshLayout.setColorSchemeResources(colors);
    }

    /**
     * 8位16进制数 ARGB
     * 设置颜色
     */
    public void setSwipeRefreshColors(@ColorInt int... colors) {
        mSwipeRefreshLayout.setColorSchemeColors(colors);
    }

    public void showSwipeRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void dismissSwipeRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}