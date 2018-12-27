package com.example.dell.myapplication;

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
public interface OnPageScrollListener {
    /**
     * 页面滚动时调用
     *
     * @param enterPosition 进入页面的位置
     * @param leavePosition 离开的页面的位置
     * @param percent       滑动百分比
     */
    void onPageScroll(int enterPosition, int leavePosition, float percent);

    /**
     * 页面选中时调用
     *
     * @param position 选中页面的位置
     */
    void onPageSelected(int position);

    /**
     * 页面滚动状态变化时调用
     *
     * @param state 页面的滚动状态
     */
    void onPageScrollStateChanged(int state);


}
