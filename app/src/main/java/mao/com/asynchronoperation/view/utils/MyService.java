package mao.com.asynchronoperation.view.utils;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Description:
 * author:jingmaolin
 * email:1271799407@qq.com.
 * phone:13342446520.
 * date: 2018/8/21.
 */
public class MyService extends IntentService {
    private static final String TAG = "MyService";
    private String sign;


    public MyService() {
        super("mao");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name, String sign) {
        super(name);
        this.sign = sign;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: 当前线程 " + Thread.currentThread().getName() + "  sign= " + sign);
        try {
            Thread.sleep(25000);
            Log.d(TAG, "onHandleIntent: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
