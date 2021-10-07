package com.techja.truyencuoi.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<B extends ViewBinding, V extends BaseViewModel> extends Fragment implements View.OnClickListener {
    protected B mBinding;
    protected V mViewModel;
    protected Context mContext;
    protected Object mData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayOutId(), container, false);
        mBinding = initViewBinding(view);
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        initViews();
        return view;
        
    }

    protected abstract void initViews();

    protected abstract Class<V> initViewModel();

    protected abstract B initViewBinding(View view);

    protected abstract int getLayOutId();

    @Override
    public final void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    private void clickView(View view) {
    }

    public void setData(Object data) {
        mData = data;
    }
}