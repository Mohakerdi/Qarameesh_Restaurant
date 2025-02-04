package qrameesh;

import java.io.Serializable;

public abstract class Person implements Serializable{

    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected String password;
    protected boolean CellEditable;
    
    public Person(){
        
    }

    public Person(String name, String password, boolean CellEditable) {
        this.name = name;
        this.password = password;
        this.CellEditable = CellEditable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCellEditable() {
        return CellEditable;
    }

    public void setCellEditable(boolean CellEditable) {
        this.CellEditable = CellEditable;
    }
    
    
}
