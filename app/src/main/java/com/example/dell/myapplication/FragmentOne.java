package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentOne extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CardList mParam1;
    private String mParam2;
    private TextView mTvContent;
    private Button mBtnLayout;
    private MainActivity mainActivity;
    private Button mBtnMolu;


    public FragmentOne() {
    }

    public static FragmentOne newInstance(CardList param1, String param2) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (CardList) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
        if (mParam1.getType() == 1) {
            mBtnMolu.setVisibility(View.GONE);
            mBtnLayout.setVisibility(View.VISIBLE);
        } else if (mParam1.getType() == 2) {
            mBtnMolu.setVisibility(View.VISIBLE);
            mBtnLayout.setVisibility(View.GONE);
        }
        String content = mParam1.getContent();
        mTvContent.setText(content);
    }

    private void initView(View view) {
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
        mBtnLayout = (Button) view.findViewById(R.id.btn_layout);
        mBtnLayout.setOnClickListener(this);
        mBtnMolu = (Button) view.findViewById(R.id.btn_molu);
        mBtnMolu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_layout:
                mainActivity.changerfu(mParam1.getList());
                break;
            case R.id.btn_molu:
                mainActivity.changerMuLu();
                break;
        }
    }

    public interface MainInterface {
        public void changerfu(ArrayList<CardList> list);

        public void changerMuLu();
    }

}
