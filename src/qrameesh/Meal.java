package qrameesh;

import java.io.Serializable;

public class Meal implements Serializable{

    private static final long serialVersionUID = 1L;
    String name;
    String recipee;
    double price;
    int prepareTime;
    String imagePath;
    int timesOrdered;

    
    public Meal(String name, String recipee, double price, int prepareTime, String imagePath) {
        this.name = name;
        this.recipee = recipee;
        this.price = price;
        this.prepareTime = prepareTime;
        this.imagePath = imagePath;
        timesOrdered = 0;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getRecipee() {
        return recipee;
    }

    public void setRecipee(String recipee) {
        this.recipee = recipee;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(int prepareTime) {
        this.prepareTime = prepareTime;
    }
    
    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void updateTimesOrdered(int timesOrdered) {
        this.timesOrdered += timesOrdered;
    }
    
}
