package com.example.pawelbaza;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Kot.class}, version = 2)
public abstract class KotyDataBase extends RoomDatabase {
    public abstract KotyDAO zwrocKotyDAO();
}
