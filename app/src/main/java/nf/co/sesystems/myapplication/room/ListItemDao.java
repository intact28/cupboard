package nf.co.sesystems.myapplication.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nf.co.sesystems.myapplication.ListItem;

@Dao
public interface ListItemDao {
    // Adds a person to the database
    @Insert
    void insertAll(ListItem... listItems);

    // Removes a person from the database
    @Delete
    void delete(ListItem listItem);

    // Gets all people in the database
    @Query("SELECT * FROM listItem")
    List<ListItem> getAllItems();
    
}
