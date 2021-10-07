package com.techja.truyencuoi.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.techja.truyencuoi.R;

public abstract class BaseActivity<B extends ViewBinding, V extends BaseViewModel> extends AppCompatActivity implements View.OnClickListener {
    protected B mBinding;
    protected V mViewModel;

    @Override
    protected final void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        View view = LayoutInflater.from(this).inflate(getLayOutId(), null);
        mBinding = initViewBinding(view);
        mViewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(view);
        initViews();


    }

    protected abstract void initViews();

    protected abstract Class<V> initViewModel();

    protected abstract B initViewBinding(View view);

    protected abstract int getLayOutId();

    @Override
    public final void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);

    }

    private void clickView(View view) {
    }

    public void showFrg(String tag, Object data, Boolean isChecked) {
        showFrg(R.id.ln_main, tag, data, isChecked);
    }

    public void showFrg(String tag, Boolean isBacked) {
        showFrg(R.id.ln_main, tag, null, isBacked);
    }

    public void showFrg(String tag) {
        showFrg(R.id.ln_main, tag, null, false);
    }

    public void showFrg(int layOutId, String tag, Object data, Boolean isBacked) {
        try {
            Class<?> clazz = Class.forName(tag);
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) clazz.newInstance();
            frg.setData(data);
            FragmentManager frgMgr = getSupportFragmentManager();
            FragmentTransaction frgTrans = frgMgr.beginTransaction();
            frgTrans.replace(layOutId, frg);
            if (isBacked) {
                frgTrans.addToBackStack(null);
            }
            frgTrans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}