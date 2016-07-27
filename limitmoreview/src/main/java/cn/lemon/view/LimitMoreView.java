package cn.lemon.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by linlongxin on 2016/7/21.
 */

public class LimitMoreView extends LinearLayout {

    private final String TAG = "LimitMoreView";
    private Adapter mAdapter;

    public LimitMoreView(Context context) {
        this(context, null, 0);
    }

    public LimitMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LimitMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setOrientation(VERTICAL);
        Util.initialize((Activity) context);
    }


    /**
     * 先移除LimitMoreView中现有的view，添加adapter中所有的view
     */
    public void addAllViewItem() {
        if (mAdapter == null) {
            throw new RuntimeException("Adapter is null");
        }
        removeAllViews();
        Adapter.ItemView itemView;
        int itemType;
        for (int position = 0; position < mAdapter.getItemCount(); position++) {
            itemType = mAdapter.getItemType(position);  //根据position获取itemType
            itemView = mAdapter.createItemView(this, itemType);
            addView(itemView.mView);
            mAdapter.bindItemView(itemView, position);

            Log.i(TAG, "item type : " + itemType + " --- position : " + position);
        }
    }

    /**
     * default orientation is vertical
     *
     * @param orientation
     */
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }


    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
        mAdapter.attachView(this);
        if (adapter.getItemCount() != 0) {
            addAllViewItem();
        }
    }

}