package com.techja.truyencuoi.view.fragment;

import android.view.View;

import androidx.lifecycle.Observer;

import com.techja.truyencuoi.R;
import com.techja.truyencuoi.databinding.FrgM000CountingBinding;
import com.techja.truyencuoi.view.base.BaseFragment;
import com.techja.truyencuoi.viewmodel.M000ViewModel;

public class M000CountingFrg extends BaseFragment<FrgM000CountingBinding, M000ViewModel> {
    public static final String TAG = M000CountingFrg.class.getName();


    @Override
    protected void initViews() {
        mViewModel.initObserve();
        mViewModel.getTime().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mBinding.tvTime.setText(s);
            }
        });

        mBinding.btStart.setOnClickListener(view -> mViewModel.startCounting());
        mBinding.btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.stop();
            }
        });
    }

    @Override
    protected Class<M000ViewModel> initViewModel() {
        return M000ViewModel.class;
    }

    @Override
    protected FrgM000CountingBinding initViewBinding(View view) {
        return FrgM000CountingBinding.bind(view);
    }

    @Override
    protected int getLayOutId() {
        return R.layout.frg_m000_counting;
    }
}
