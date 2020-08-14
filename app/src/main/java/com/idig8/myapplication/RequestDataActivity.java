package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.idig8.myapplication.model.LessonInfo;
import com.idig8.myapplication.model.LessonResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_data);

        ListView listView = findViewById(R.id.request_main_list_view);
        listView.setAdapter();
    }

    public class RequestDataAsyncTask extends AsyncTask<Void,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //加入loading
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //加入loading

            LessonResult lessonResult = new LessonResult();
            try {
                JSONObject object = new JSONObject(s);
                lessonResult.setStatus(object.getInt("status"));
                lessonResult.setMsg(object.getString("msg"));

                List<LessonResult.Lesson> lessonInfos = new ArrayList<>();
                JSONArray dataArray  = object.getJSONArray("data");

                for (int index =0;index<dataArray.length();index++) {
                    LessonResult.Lesson lessonInfo = new LessonResult.Lesson();
                    JSONObject tempJSONobject = (JSONObject) dataArray.get(index);
                    lessonInfo.setmName(tempJSONobject.getString("name"));
                    lessonInfos.add(lessonInfo);
                }
                lessonResult.setmLessons(lessonInfos);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            return request("https://www.imooc.com/api/teacher?type=2&page=1");

        }

        private String request(String urlString) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setConnectTimeout(30000);
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                String responseMessage  = connection.getResponseMessage();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStreamReader inputStreamReader =new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        stringBuffer.append(line);
                    }
                    return stringBuffer.toString();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
