package com.techja.truyencuoi.view.activity;

import android.view.View;

import com.techja.truyencuoi.R;
import com.techja.truyencuoi.databinding.ActivityMainBinding;
import com.techja.truyencuoi.view.base.BaseActivity;
import com.techja.truyencuoi.view.fragment.M000CountingFrg;
import com.techja.truyencuoi.viewmodel.CommonVM;

public class MainActivity extends BaseActivity<ActivityMainBinding, CommonVM> {


    @Override
    protected void initViews() {
        showFrg(M000CountingFrg.TAG);
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    @Override
    protected ActivityMainBinding initViewBinding(View view) {
        return ActivityMainBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.activity_main;
    }


}