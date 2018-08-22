package mao.com.asynchronoperation.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
 * date: 2018/8/20.
 */
public class AsyncTaskActivity extends AppCompatActivity {
    private MyAsyncTask asyncTask;
    private ExecutorService executorService;
    private static final String TAG = "AsyncTaskActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        int number = Runtime.getRuntime().availableProcessors();
        executorService = new ThreadPoolExecutor(number, number * 50, 2000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(number * 4));
    }

    public void downLoad(View v) {
        /**
         * 在同一子线程中排队执行
         */
//        asyncTask = new MyAsyncTask("first");
//        asyncTask.execute("参数1", "参数2", "参数3");
//
//        asyncTask = new MyAsyncTask("second");
//        asyncTask.execute("第一个", "第二个", "第三个");

        /**
         * 在线程池中并行执行
         */
        asyncTask = new MyAsyncTask("first");
        asyncTask.executeOnExecutor(executorService, "参数1", "参数2", "参数3");

        asyncTask = new MyAsyncTask("second");
        asyncTask.executeOnExecutor(executorService, "第一个", "第二个", "第三个");
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {
        private String sign;

        public MyAsyncTask(String sign) {
            this.sign = sign;
        }

        /**
         * 准备工作，UI线程
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: " + "准备工作");
            Log.d(TAG, "onPreExecute: " + "当前线程：" + Thread.currentThread().getName() + "   " + sign);
        }

        /**
         * 子线程，返回值传递到onPostExecute方法中
         */
        @Override
        protected String doInBackground(String... strings) {
            if (!isCancelled()) {
                Log.d(TAG, "doInBackground: " + "子线程开始");
                Log.d(TAG, "doInBackground: " + "当前线程：" + Thread.currentThread().getName() + "  " + sign);
                for (String str : strings) {
                    Log.d(TAG, "doInBackground: " + str);
                }

                try {
                    Log.d(TAG, "doInBackground: time = " + System.currentTimeMillis() + " sign=" + sign);
                    Thread.sleep(6000);
                    Log.d(TAG, "doInBackground: time = " + System.currentTimeMillis() + " sign=" + sign);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "执行完毕 OK！";
        }

        /**
         * 进度，UI线程
         */
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 执行结束，UI线程
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: 执行完毕 获得参数为=" + s);
            Log.d(TAG, "onPostExecute: " + "当前线程为" + Thread.currentThread().getName() + "  " + sign);
        }
    }
}
