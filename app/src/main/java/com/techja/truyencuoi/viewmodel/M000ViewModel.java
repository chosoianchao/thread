package com.techja.truyencuoi.viewmodel;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.techja.truyencuoi.view.base.BaseViewModel;

public class M000ViewModel extends BaseViewModel {
    private static final String TAG = M000ViewModel.class.getName();
    private Thread thread;
    private final MutableLiveData<Integer> minute = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> second = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> miliSecond = new MutableLiveData<>(0);
    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            miliSecond.setValue(miliSecond.getValue() + 1);
            if (miliSecond.getValue() >= 59) {
                miliSecond.setValue(0);
                second.setValue(second.getValue() + 1);
            }
            if (second.getValue() >= 59) {
                second.setValue(0);
                minute.setValue(minute.getValue() + 1);
            }
            if (minute.getValue() >= 59) {
                stop();
            }
            return false;
        }
    });
    private final MediatorLiveData<String> time = new MediatorLiveData<>();

    public MediatorLiveData<String> getTime() {
        return time;
    }

    public void initObserve() {
        Observer<? super Integer> timeObserve = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String strMinute = (minute.getValue() < 10 ? "0" : "") + minute.getValue();
                String strSecond = (second.getValue() < 10 ? "0" : "") + second.getValue();
                String strMiliSecond = (miliSecond.getValue() < 10 ? "0" : "") + miliSecond.getValue();
                time.setValue(String.format("%s:%s:%s", strMinute, strSecond, strMiliSecond));

            }
        };
        time.addSource(minute, timeObserve);
        time.addSource(second, timeObserve);
        time.addSource(miliSecond, timeObserve);
    }

    public void startCounting() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Message.obtain(mHandler).sendToTarget();
                            Thread.sleep(10);
                            Log.i(TAG, "Counting : " + time);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
        }
    }

    public void stop() {
        if (thread == null) return;

        thread.interrupt();

    }
}



