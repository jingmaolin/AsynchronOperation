package mao.com.asynchronoperation.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mao.com.asynchronoperation.R;

/**
 * Description: 耗时操作通知ioHandle,UI操作通知uiHandle
 * author:jingmaolin
 * email:1271799407@qq.com.
 * phone:13342446520.
 * date: 2018/8/21.
 */
public class HandleThreadActivity extends Activity {
    private static final String TAG = "HandleThreadActivity";


    private Handler uiHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: 当前线程 = " + Thread.currentThread().getName());
            changeUI();
        }
    };

    private HandlerThread handlerThread;
    private Handler ioHandle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
        initData();
    }

    private void initData() {
        handlerThread = new HandlerThread("IO");
        handlerThread.start();
        ioHandle = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "handleMessage: 当前线程 = " + Thread.currentThread().getName());
                requestResource();
            }
        };
    }

    private void changeUI() {
        ((TextView) (findViewById(R.id.handle_text))).setText("哈哈哈哈哈哈哈" + System.currentTimeMillis());
    }

    private void requestResource() {
        try {
            Thread.sleep(6000);
            uiHandle.sendEmptyMessage(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork(View v) {
        if (ioHandle != null) {
            ioHandle.sendEmptyMessage(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}
