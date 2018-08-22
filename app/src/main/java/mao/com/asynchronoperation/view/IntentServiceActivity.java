package mao.com.asynchronoperation.view;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import mao.com.asynchronoperation.R;
import mao.com.asynchronoperation.view.utils.MyService;

/**
 * Description:
 * author:jingmaolin
 * email:1271799407@qq.com.
 * phone:13342446520.
 * date: 2018/8/21.
 */
public class IntentServiceActivity extends Activity {
    private static final String TAG = "IntentServiceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void useIntentService(View v) {
        startService(new Intent(this, MyService.class));

        startService(new Intent(this, MyService.class));
    }
}
