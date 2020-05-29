package com.idig8.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownHandlerActivity extends AppCompatActivity {

    public static final int DOWNLOAD_MESSAGE_CODE=100001;
    private static final int DOWNLOAD_MESSAGE_FAIL_CODE = 100002;
    public static final int EXTERNAL_STORAGE_REQ_CODE = 10 ;
    private  Handler handler;
    private static final String TAG = "DownHandlerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_handler);

        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    EXTERNAL_STORAGE_REQ_CODE);
        }

        final ProgressBar progressBar = findViewById(R.id.progressBar2);

        /**
         * 点击按钮开始下载
         * 发起下载
         * 开启子线程做下载
         * 过程中通知主线程
         * 主线程更新进度条
         */
        findViewById(R.id.downBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download("https://4dac61970a9d2ea67927f952ca528296.dlied1.cdntips.com/dlied1.qq.com/qqweb/QQ_1/android_apk/Android_8.3.6.4590_537064458.apk?mkey=5ed07cb6b677a558&f=17c9&cip=182.119.131.173&proto=https&access_type=$header_ApolloNet");
//                        download("https://g17.gdl.netease.com/com.netease.dhxy_simple.1.1.235.apk");
                    }
                }).start();

            }
        });
         handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==DOWNLOAD_MESSAGE_CODE){

                    Log.d(TAG,"Handler="+(Long) msg.obj);
                    progressBar.setProgress(((Long) msg.obj).intValue());
                }
                if(msg.what==DOWNLOAD_MESSAGE_FAIL_CODE){
                   //下载失败的控制
                }
            }
        };
    }

    private void download(String appUrl) {
        try {
            URL url = new URL(appUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            // 获取文件的总长度
            int contentLength = urlConnection.getContentLength();
//            String downloadFolderName = Environment.getDownloadCacheDirectory()+ File.separator+
//                    "dh2"+File.separator;
            String downloadFolderName = getApplicationContext().getFilesDir().getAbsolutePath()+ File.separator+
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
                Message message = Message.obtain();
                long downSizelong=(long)downSize*100;
                long resultObj = downSizelong/contentLength;
                Log.d(TAG,"Handler+downSize="+ downSize+":"+contentLength+":"+resultObj);
                message.obj = resultObj;
                message.what=DOWNLOAD_MESSAGE_CODE;
                handler.sendMessage(message);
            }
            inputStream.close();
            outputStream.close();

        } catch (MalformedURLException e) {
            notifyDownloadFaild();
            e.printStackTrace();
        } catch (IOException e) {
            notifyDownloadFaild();
            e.printStackTrace();
        }
    }
    private void notifyDownloadFaild(){
        Message message = Message.obtain();
        message.what=DOWNLOAD_MESSAGE_FAIL_CODE;
        handler.sendMessage(message);
    }
}
