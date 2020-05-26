package com.idig8.myapplication.model;

public class Person {

    private String mame;
    private String sex;
    private Food food;

    public Person(String mame, String sex, Food food) {
        this.mame = mame;
        this.sex = sex;
        this.food = food;
    }

    public Person() {
    }

    public String getMame() {
        return mame;
    }

    public void setMame(String mame) {
        this.mame = mame;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mame='" + mame + '\'' +
                ", sex='" + sex + '\'' +
                ", food=" + food +
                '}';
    }
}
