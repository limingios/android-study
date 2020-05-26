package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.idig8.myapplication.model.Food;
import com.idig8.myapplication.model.Person;

import java.util.ArrayList;
import java.util.List;

public class UiMenuActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private RadioGroup mSexRadioGroup;
    private CheckBox mHotCheckBox,mFishCheckBox,mSourCheckBox;
    private SeekBar mSeekBar;
    private Button mSearchButton;
    private ImageView mFoodImageView;
    private ToggleButton mToggleButton;
    private Person person;
    private List<Food> foodResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_menu);

        //初始化控件
        findViews();
        //初始化数据
        initData();
        // 为控件添加监听器，实现基本功能
        setlisteners();

    }

    private void setlisteners() {


    }

    private void initData() {
        List<Food> foods = new ArrayList<Food>();
        foods.add(new Food("麻辣香锅",55,R.mipmap.aa,true,true,true));
        foods.add(new Food("新鲜鱼",70,R.mipmap.bb,true,false,true));

        person =new Person();
        foodResult = new ArrayList<Food>();
    }

    private void findViews(){
        mNameEditText = findViewById(R.id.nameEditText);
        mSexRadioGroup = findViewById(R.id.sexRadioGroup);
        mHotCheckBox = findViewById(R.id.hotCheckBox);
        mFishCheckBox = findViewById(R.id.fishCheckBox);
        mSourCheckBox = findViewById(R.id.sourCheckBox);
        mSeekBar = findViewById(R.id.seekBar);
        mSearchButton = findViewById(R.id.searchButton);
        mToggleButton = findViewById(R.id.showToggleButton);
        mFoodImageView = findViewById(R.id.foodImageView);
    }
}