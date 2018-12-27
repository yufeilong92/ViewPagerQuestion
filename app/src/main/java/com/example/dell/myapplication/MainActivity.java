package com.example.dell.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentOne.MainInterface {

    private ImageView mShadowView;
    private FrameLayout mFlContentLayoutOne;
    private Context mContext;
    private ArrayList<CardList> mlists;
    private ArrayList<CardList> mlistOnes;
    private OnePagerAdapetr mOneAdapter;
    private OnePagerOneAdapetr mOneOneAdapter;
    private ReaderViewPager mRpContent;
    private ReaderViewPager mRpContentOne;
    private int mPostion = 0, mPostionOne = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mlists = initList();
        initViewPage();

    }

    int data = 0;

    private void initViewPageOne() {
        mOneOneAdapter = new OnePagerOneAdapetr(getSupportFragmentManager(), mlistOnes);
        mRpContentOne.setAdapter(mOneOneAdapter);
/*        ViewPagerUtil.bind(mRpContentOne, new OnPageScrollListener() {
            @Override
            public void onPageScroll(int enterPosition, int leavePosition, float percent) {
                Log.d("mRpContentOne", "onPageScrolled————>"
                        + "    进入页面：" + enterPosition
                        + "    离开页面：" + leavePosition
                        + "    滑动百分比：" + percent);
                if (percent == 0) {
                    changerView();
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("mRpContentOne", "onPageSelected————>    position：" + position);
            }
            private boolean mIsScrolled ;                   //  viewpager是否处于惯性滑动
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mIsScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mIsScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (!mIsScrolled) {
                            // TODO     你想要实现的操作  
                            Log.e("onPageScrollStateChanged", "执行" );
                        }
                        mIsScrolled = true;
                        break;
                }

//                Log.d("mRpContentOne", "onPageScrollStateChanged————>    state：" + state);
            }
        });*/
        mRpContentOne.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mRpContentOne.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mPostionOne = position;
            }

            private boolean mIsScrolled;                   //  viewpager是否处于惯性滑动

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mIsScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        mIsScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (!mIsScrolled) {
                            Log.e("===", "执行");
                            if (mPostionOne == 0) {
                                changerView();
                            } else if (mPostionOne == mlistOnes.size() - 1) {
                                changerRightView();
                            }
                        }
                        mIsScrolled = true;
                        break;
                }
            }

        });
    }

    private void initViewPage() {
        mOneAdapter = new OnePagerAdapetr(getSupportFragmentManager(), mlists);
        mRpContent.setAdapter(mOneAdapter);
        mRpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mRpContent.getWidth() - positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mPostion = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void changerView() {
        if (mPostion == 0) {
            Toast.makeText(MainActivity.this, "已经是第一题", Toast.LENGTH_SHORT).show();
            return;
        }
        mRpContent.setVisibility(View.VISIBLE);
        mRpContentOne.setVisibility(View.GONE);
        mRpContent.setCurrentItem(mPostion - 1, true);
    }

    private void changerRightView() {

        if (mPostion == mlists.size() - 1) {
            Toast.makeText(MainActivity.this, "已经是最后一道题", Toast.LENGTH_SHORT).show();
            return;
        }
        mRpContent.setVisibility(View.VISIBLE);
        mRpContentOne.setVisibility(View.GONE);
        mRpContent.setCurrentItem(mPostion + 1, true);
    }

    private ArrayList<CardList> initList() {
        ArrayList<CardList> cardLists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CardList vo = new CardList();
            vo.setType(1);
            vo.setContent("主题" + i);
            ArrayList<CardList> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                CardList cardList = new CardList();
                cardList.setType(2);
                cardList.setContent("副题" + j);
                list.add(cardList);
            }
            vo.setList(list);
            cardLists.add(vo);
        }
        return cardLists;
    }


    private void initView() {
        mContext = this;
        mShadowView = (ImageView) findViewById(R.id.shadowView);
        mFlContentLayoutOne = (FrameLayout) findViewById(R.id.fl_content_layout_one);
        mRpContent = (ReaderViewPager) findViewById(R.id.rp_content);
        mRpContentOne = (ReaderViewPager) findViewById(R.id.rp_content_one);
    }

    @Override
    public void changerfu(ArrayList<CardList> list) {
        mRpContentOne.setVisibility(View.VISIBLE);
        mRpContent.setVisibility(View.GONE);
        mlistOnes = list;
        initViewPageOne();
    }

    @Override
    public void changerMuLu() {
        mRpContentOne.setVisibility(View.GONE);
        mRpContent.setVisibility(View.VISIBLE);
    }


    public class OnePagerAdapetr extends FragmentPagerAdapter {

        private final ArrayList<?> mlist;

        public OnePagerAdapetr(FragmentManager fm, ArrayList<?> mlist) {
            super(fm);
            this.mlist = mlist;
        }

        @Override
        public Fragment getItem(int position) {
            CardList vo = (CardList) mlist.get(position);
            return FragmentOne.newInstance(vo, "");
        }

        @Override
        public int getCount() {
            return mlist.size();
        }
    }

    public class OnePagerOneAdapetr extends FragmentPagerAdapter {

        private final ArrayList<?> mlist;

        public OnePagerOneAdapetr(FragmentManager fm, ArrayList<?> mlist) {
            super(fm);
            this.mlist = mlist;
        }

        @Override
        public Fragment getItem(int position) {
            CardList vo = (CardList) mlist.get(position);
            return FragmentOne.newInstance(vo, "");
        }

        @Override
        public int getCount() {
            return mlist.size();
        }
    }
}
