package com.example.dell.myapplication;

import android.util.Log;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.12.27 上午 9:22
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class ViewPagerHelper {
    private double mLastPositionOffsetSum;  // 上一次滑动总的偏移量
    private OnPageScrollListener mOnPageScrollListener;
   private static  String TAG="【"+ViewPagerHelper.class+"】==";
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 当前总的偏移量
        float currentPositionOffsetSum = position + positionOffset;
        // 上次滑动的总偏移量大于此次滑动的总偏移量，页面从右向左进入(手指从右向左滑动)
        boolean rightToLeft = mLastPositionOffsetSum <= currentPositionOffsetSum;
        if (currentPositionOffsetSum == mLastPositionOffsetSum) return;
        int enterPosition;
        int leavePosition;
        float percent;
        if (rightToLeft) {  // 从右向左滑
            enterPosition = (positionOffset == 0.0f) ? position : position + 1;
            leavePosition = enterPosition - 1;
            percent = (positionOffset == 0.0f) ? 1.0f : positionOffset;
            Log.e(TAG, "从右向左滑: "+enterPosition+leavePosition+percent );
        } else {            // 从左向右滑
            enterPosition = position;
            leavePosition = position + 1;
            percent = 1 - positionOffset;
            Log.e(TAG, "从左向右滑: "+enterPosition+leavePosition+percent );
        }
        if (mOnPageScrollListener != null) {
            mOnPageScrollListener.onPageScroll(enterPosition, leavePosition, percent);
        }
        mLastPositionOffsetSum = currentPositionOffsetSum;
    }

    public void onPageSelected(int position) {
        if (mOnPageScrollListener != null) {
            mOnPageScrollListener.onPageSelected(position);
        }
    }

    /**
     * @param state 当前滑动状态
     *              ViewPager.SCROLL_STATE_IDLE     页面处于闲置、稳定状态，即没被拖动也没惯性滑动
     *              ViewPager.SCROLL_STATE_DRAGGING 页面正在被用户拖动，即手指正在拖动状态
     *              Viewpager.SCROLL_STATE_SETTLING 页面处于即将到达最终状态的过程，即手指松开后惯性滑动状态
     */
    public void onPageScrollStateChanged(int state) {
        if (mOnPageScrollListener != null) {
            mOnPageScrollListener.onPageScrollStateChanged(state);
        }
    }

    public void setOnPageScrollListener(OnPageScrollListener onPageScrollListener) {
        mOnPageScrollListener = onPageScrollListener;
    }

}
