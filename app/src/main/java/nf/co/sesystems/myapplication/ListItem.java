package nf.co.sesystems.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class ListItem implements Serializable {

    @PrimaryKey @NonNull
    private String name = "";
    private String note = "";
    private int quantity = 0;

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

    public void incrementQuantity() {
        this.quantity = this.quantity + 1;
    }

    public void decrementQuantity() {
        this.quantity = this.quantity - 1;
    }

    public ListItem(String name, int quantity, String note){
        this.name = name;
        this.quantity = quantity;
        this.note = note;
    }
}
