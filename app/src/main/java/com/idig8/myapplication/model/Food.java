package com.idig8.myapplication.model;

public class Food {
    private String name;
    private int price;
    private int pic;
    private boolean hot;
    private boolean fish;
    private boolean sour;

    public Food() {
    }

    public Food(String name, int price, int pic, boolean hot, boolean fish, boolean sour) {
        this.name = name;
        this.price = price;
        this.pic = pic;
        this.hot = hot;
        this.fish = fish;
        this.sour = sour;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPic() {
        return pic;
    }

    public boolean isHot() {
        return hot;
    }

    public boolean isFish() {
        return fish;
    }

    public boolean isSour() {
        return sour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public void setSour(boolean sour) {
        this.sour = sour;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", pic=" + pic +
                ", hot=" + hot +
                ", fish=" + fish +
                ", sour=" + sour +
                '}';
    }
}
