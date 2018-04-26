package nf.co.sesystems.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class ListItem implements Serializable {

    @PrimaryKey(autoGenerate = true) @NonNull
    private long lisitemId = 0;
    private String name = "";
    private String note = "";
    private int quantity = 0;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public long getLisitemId() {
        return lisitemId;
    }

    public void setLisitemId(@NonNull long lisitemId) {
        this.lisitemId = lisitemId;
    }

    public void incrementQuantity() {
        this.quantity = this.quantity + 1;
    }

    public void decrementQuantity() {
        this.quantity = this.quantity - 1;
    }

    public ListItem(String name, int quantity, String note, String id){
        this.name = name;
        this.quantity = quantity;
        this.note = note;
        this.id = id;
    }
}
