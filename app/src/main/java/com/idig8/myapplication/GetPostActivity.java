package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.idig8.myapplication.model.LessonResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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


    public void getNetDataMethod(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //GET的方式

                    URL url = new URL("http://www.imooc.com/api/teacher?type=2&page=1");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout(30*1000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setRequestProperty("Accept-Charset", "UTF-8");
                    connection.connect();
                    //POST的方式
//                    URL url = new URL("https://www.imooc.com/api/teacher");
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    connection.setConnectTimeout(30*1000);
//                    connection.setRequestMethod("POST");
//                    connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    connection.setRequestProperty("Accept-Charset", "UTF-8");
//                    connection.setDoInput(true);
//                    connection.setDoOutput(true);
//                    connection.setUseCaches(false);
//                    connection.connect();
//                    String datas ="username="+getEncodeValue("aaa")+"&&password="+getEncodeValue("bbb");
//                    OutputStream outputStream = connection.getOutputStream();
//                    outputStream.write(datas.getBytes());
//                    outputStream.flush();
//                    outputStream.close();


                    int responseCode = connection.getResponseCode();
                    String reponseMessage  = connection.getResponseMessage();

                    if(responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        result = convertStreamToString(inputStream);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result = decodeUnicode(result);
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
        }).start();

    }

    public void getFormatData(View w){
        handleJSONData(result);
    }

    public void handleJSONData(String result){
        try {
            LessonResult lessonResult = new LessonResult();
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt("status");
            JSONArray lessons = jsonObject.getJSONArray("data");
            lessonResult.setStatus(status);
            List<LessonResult.Lesson> list = new ArrayList<>();
            if(lessons!=null && lessons.length()>0){
                for(int index=0;index<lessons.length();index++) {
                    JSONObject lessonObject = (JSONObject) lessons.get(index);
                    int id = lessonObject.getInt("id");
                    int learner = lessonObject.getInt("learner");
                    String name = lessonObject.getString("name");
                    String picSmall = lessonObject.getString("picSmall");
                    String picBig = lessonObject.getString("picBig");
                    String description = lessonObject.getString("description");
                    LessonResult.Lesson lesson = new LessonResult.Lesson();
                    lesson.setmID(id);
                    lesson.setmName(name);
                    lesson.setmLearnerNumber(learner);
                    lesson.setmBigPictureUrl(picBig);
                    lesson.setmSmallPictureUrl(picSmall);
                    lesson.setmDescription(description);
                    list.add(lesson);
                }

                lessonResult.setmLessons(list);
            }

            textViewData.setText(lessonResult.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    private String getEncodeValue(String str){
        String encode=null;
        try {
            encode = URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encode;
    }
}
