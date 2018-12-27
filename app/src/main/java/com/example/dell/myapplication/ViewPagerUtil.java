package com.example.dell.myapplication;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

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
public class ViewPagerUtil {
    /**
     * 给ViewPager绑定自定义的滚动监听
     *
     * @param viewPager
     * @param onPageScrollListener
     */
    public static void bind(@NonNull ViewPager viewPager, @NonNull OnPageScrollListener onPageScrollListener) {
        final ViewPagerHelper helper = new ViewPagerHelper();
        // 给helper设置滚动监听
        helper.setOnPageScrollListener(onPageScrollListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                helper.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                helper.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                helper.onPageScrollStateChanged(state);
            }
        });
    }

}
