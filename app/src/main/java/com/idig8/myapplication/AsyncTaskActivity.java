package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        initViews();
        setListener();

        //初始化UI数据
        setData();
    }

    private void setData() {
        progressBar.setProgress(0);
        button.setText("开始下载");
        textView.setText("开始下载");
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBarAsync);
        button = findViewById(R.id.buttonAsync);
        textView = findViewById(R.id.textViewAsync);
    }

    private void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/5/29 下载
                DownloadAsyncTask asyncTask = new DownloadAsyncTask();
                asyncTask.execute("https://4dac61970a9d2ea67927f952ca528296.dlied1.cdntips.com/dlied1.qq.com/qqweb/QQ_1/android_apk/Android_8.3.6.4590_537064458.apk?mkey=5ed07cb6b677a558&f=17c9&cip=182.119.131.173&proto=https&access_type=$header_ApolloNet");
            }
        });
    }

    /**
     * String 入参
     * Integer 进度
     * Boolean 返回值
     */
    public class DownloadAsyncTask extends AsyncTask<String,Integer,Boolean>{

        String downloadFolderName;
        public DownloadAsyncTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            button.setText("下载中");
            textView.setText("下载中");
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values!=null && values.length>0) {
                progressBar.setProgress(values[0]);
            }
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            if(params!=null && params.length>0){
                try {
                    URL url =new URL(params[0]);
                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    // 获取文件的总长度
                    int contentLength = urlConnection.getContentLength();

                    downloadFolderName = getApplicationContext().getFilesDir().getAbsolutePath()+ File.separator+
                            "dh2"+File.separator;
                    File file = new File(downloadFolderName);
                    if(!file.exists()){
                        file.mkdir();
                    }
                    String fileName = downloadFolderName+"dh2.apk";
                    File apkFile = new File(fileName);
                    if(apkFile.exists()){
                        apkFile.delete();
                    }
                    int downSize = 0;
                    byte[] bytes = new byte[1024];
                    int length = 0;

                    OutputStream outputStream =new FileOutputStream(fileName);
                    while ((length=inputStream.read(bytes))!=-1){
                        outputStream.write(bytes,0,length);
                        downSize+=length;
                        long downSizelong=(long)downSize*100;
                        long resultObj = downSizelong/contentLength;
                        publishProgress((int) resultObj);

                    }
                    inputStream.close();
                    outputStream.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            button.setText("下载完成");
            textView.setText(aBoolean?"下载完成"+downloadFolderName:"下载失败");
        }

    }


}
