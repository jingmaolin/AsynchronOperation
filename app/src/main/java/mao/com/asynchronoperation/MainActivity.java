package mao.com.asynchronoperation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mao.com.asynchronoperation.view.AsyncTaskActivity;
import mao.com.asynchronoperation.view.HandleThreadActivity;
import mao.com.asynchronoperation.view.IntentServiceActivity;
import mao.com.asynchronoperation.view.ThreadPoolActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void asyncTask(View v) {
        startActivity(new Intent(this, AsyncTaskActivity.class));
    }

    public void handleThread(View v) {
        startActivity(new Intent(this, HandleThreadActivity.class));
    }

    public void threadPool(View v) {
        startActivity(new Intent(this, ThreadPoolActivity.class));
    }

    public void intentService(View v) {
        startActivity(new Intent(this, IntentServiceActivity.class));
    }

    public void loader(View v) {

    }
}
