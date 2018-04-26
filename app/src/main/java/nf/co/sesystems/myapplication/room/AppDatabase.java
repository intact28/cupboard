package nf.co.sesystems.myapplication.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import nf.co.sesystems.myapplication.ListItem;

@Database(entities = {ListItem.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ListItemDao ListItemDao();
}
