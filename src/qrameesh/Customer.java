package qrameesh;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{
    
    private String email;
    private String address;
    private List<Order> orders;
    
    public Customer(String name, String password, String email, String address) {
        super(name, password, false);
        this.email = email;
        this.address = address;
        orders = new ArrayList();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
    
}
