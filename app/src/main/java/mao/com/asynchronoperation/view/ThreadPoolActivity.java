package mao.com.asynchronoperation.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mao.com.asynchronoperation.R;

/**
 * Description:
 * author:jingmaolin
 * email:1271799407@qq.com.
 * phone:13342446520.
 * date: 2018/8/21.
 */
public class ThreadPoolActivity extends Activity {
    private static final String TAG = "ThreadPoolActivity";
    private ExecutorService executorService;
    private int number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);
        initPools();
    }

    private void initPools() {
        number = Runtime.getRuntime().availableProcessors();
        Log.d(TAG, "initPools: 核数为 " + number);
        executorService = new ThreadPoolExecutor(number, number * 50, 2000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(number * 4));
    }

    public void executeMultiOpe(View v) {
        for (int i = 0; i < 50; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: 当前线程 " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
