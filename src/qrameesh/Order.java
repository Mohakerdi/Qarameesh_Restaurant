package qrameesh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    private Customer customer;
    private List <Meal> meals;
    private int totTime;
    private double totalCoast;
    private EnOrderType type;
    private EnOrderStat stat;
    private Date date;

    public Order(Customer customer, EnOrderType type, double totalCoast, int totTime) {
        this.customer = customer;
        this.meals = new ArrayList();
        this.totalCoast = totalCoast;
        this.type = type;
        this.totTime = totTime;
    }

    public int getTotTime() {
        return totTime;
    }

    public void setTotTime(int totTime) {
        this.totTime = totTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public double getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(double totalCoast) {
        this.totalCoast = totalCoast;
    }

    public EnOrderType getType() {
        return type;
    }

    public void setType(EnOrderType type) {
        this.type = type;
    }

    public EnOrderStat getStat() {
        return stat;
    }

    public void setStat(EnOrderStat stat) {
        this.stat = stat;
    }   
    
}
