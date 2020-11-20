package com.example.myapplication.Model.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.Model.Pojo.Inventory;

@Database(entities = {Inventory.class}, version = 1, exportSchema = false)
public abstract class InventoryDatabase extends RoomDatabase {

    public static InventoryDatabase instance;

    public abstract InventoryDao inventoryDao();

    private static final String DB_NAME = "inventories.db";
    private static final Object LOCK = new Object();

    public static synchronized InventoryDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, InventoryDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }
}
