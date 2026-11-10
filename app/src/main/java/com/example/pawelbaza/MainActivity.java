package com.example.pawelbaza;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class MainActivity extends AppCompatActivity {
    KotyDataBase kotyDatabase;
    EditText rasaKota;
    EditText wiekKota;
    EditText skalaLadnosci;
    ListView listviw;
    Button dodaj;
    ArrayAdapter<Kot> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    kotyDatabase = Room.databaseBuilder(
            getApplicationContext(),
            KotyDataBase.class,
            "koty_db"
    )
            .allowMainThreadQueries()
            .build();
    kotyDatabase.zwrocKotyDAO().zwrocWszystkieKotyZBazy();
    rasaKota = findViewById(R.id.rasaKota);
    wiekKota = findViewById(R.id.wiekKota);
    skalaLadnosci = findViewById(R.id.skalaLadnosci);
    listviw = findViewById(R.id.listviw);
    dodaj = findViewById(R.id.dodaj);
    dodaj.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String rasa = rasaKota.getText().toString();
            int wiek = Integer.parseInt(wiekKota.getText().toString());
            int skala = Integer.parseInt(skalaLadnosci.getText().toString());
            Kot kot = new Kot(rasa,wiek,skala);
            kotyDatabase.zwrocKotyDAO().wstawDoBazyKota(kot);
        }
    });
    }
}