package com.example.pawelbaza;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "koty")
public class Kot {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Rasa;
    @ColumnInfo(name = "wiek")
    private int Wiek;
    private int Skalapieknoscina10;

    public Kot() {
    }

    @Override
    public String toString() {
        return "Rasa: " + Rasa  + " Wiek: "+Wiek + " Skalapieknoscina10: " + Skalapieknoscina10;
    }

    public Kot(String rasa, int wiek, int skalapieknoscina10) {
        Rasa = rasa;
        Wiek = wiek;
        Skalapieknoscina10 = skalapieknoscina10;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRasa() {
        return Rasa;
    }

    public void setRasa(String rasa) {
        Rasa = rasa;
    }

    public int getWiek() {
        return Wiek;
    }

    public void setWiek(int wiek) {
        Wiek = wiek;
    }

    public int getSkalapieknoscina10() {
        return Skalapieknoscina10;
    }

    public void setSkalapieknoscina10(int skalapieknoscina10) {
        Skalapieknoscina10 = skalapieknoscina10;
    }
}
