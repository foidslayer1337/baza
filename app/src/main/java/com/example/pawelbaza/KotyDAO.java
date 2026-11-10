package com.example.pawelbaza;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface KotyDAO {
    @Insert
    public void wstawDoBazyKota(Kot kot);
    @Insert
    public void wstawListeKotowDoBazy(Kot ... kot);
    @Delete
    public void usunZBazy(Kot kot);
    @Update
    public void aktualizujKotaWBazi(Kot kot);

    @Query("Select * from koty")
    public List<Kot> zwrocWszystkieKotyZBazy();

}
