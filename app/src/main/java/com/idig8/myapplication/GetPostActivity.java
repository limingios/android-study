package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetPostActivity extends AppCompatActivity {

    private Button buttonNetData;
    private Button buttonFormatData;
    private TextView textViewData;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_post);

        //初始化控件
        findViews();
    }

    public void findViews(){
        buttonNetData = findViewById(R.id.getNetData);
        buttonFormatData = findViewById(R.id.getFormatData);
        textViewData = findViewById(R.id.showView);
    }


    public void getNetData(View w){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.imooc.com/api/teacher?type=2&page=1");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout(30*1000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setRequestProperty("Accept-Charset", "UTF-8");
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    String reponseMessage  = connection.getResponseMessage();

                    if(responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        result = convertStreamToString(inputStream);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewData.setText(result);
                            }
                        });

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getFormatData(View w){

    }

    /**
     * InputStream转String工具类，返回String
     * @param inputStream
     * @return
     */
    private static String convertStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }// StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }



}
