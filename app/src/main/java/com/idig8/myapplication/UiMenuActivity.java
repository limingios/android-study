package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
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
    List<Food> foods;
    private List<Food> foodResult;
    boolean isHot;
    boolean isFish;
    boolean isSour;
    int price;
    int mCurrentIndex;
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
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    person.setMame(s.toString());
                }
            }
        });


        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.maleRadioButton:
                        person.setSex("男");
                        break;
                    case R.id.femaleRadioButton:
                        person.setSex("女");
                        break;
                }
            }
        });

        mHotCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHot = isChecked;

            }
        });

        mFishCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isFish = isChecked;
            }
        });

        mSourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSour = isChecked;
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                price= seekBar.getProgress();
                Toast.makeText(UiMenuActivity.this,"价格："+price,Toast.LENGTH_SHORT).show();
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });


        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mToggleButton.isChecked()){
                    mCurrentIndex++;
                    if(mCurrentIndex<foodResult.size()){
                        mFoodImageView.setImageResource(foodResult.get(mCurrentIndex).getPic());
                    }
                }else{
                    if(mCurrentIndex<foodResult.size()) {
                        String footname = foodResult.get(mCurrentIndex).getName();
                        String personname = person.getMame();
                        String personsex = person.getSex();
                        Toast.makeText(UiMenuActivity.this, "菜名：" + footname+", 人名："+personname+",性别："+personsex, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UiMenuActivity.this, "没有啦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }

    private void search() {
        //每次都清空结果列秒
        //便利所有的菜
        //符合调价，加入结果的列表中。
        if(foodResult == null){
            foodResult = new ArrayList<>();
        }
        foodResult.clear();
        mCurrentIndex = 0;

        for(int i =0;i<foods.size();i++){
            Food food =  foods.get(i);
            if(food!= null){
                if(food.getPrice()<price && (food.isHot()==isHot|| food.isFish()==isFish|| food.isSour()==isSour)){
                    foodResult.add(food);
                }
            }
        }
        if(mCurrentIndex<foodResult.size()){
            mFoodImageView.setImageResource(foodResult.get(mCurrentIndex).getPic());
        }
    }

    private void initData() {
        foods = new ArrayList<Food>();
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