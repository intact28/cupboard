package nf.co.sesystems.myapplication;

import java.io.Serializable;

public class ListItem implements Serializable {

    private String name = "";
    private String note = "";
    private int quantity = 0;

    public ListItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ListItem(String name, int quantity, String note){
        this.name = name;
        this.quantity = quantity;
        this.note = note;
    }
}
