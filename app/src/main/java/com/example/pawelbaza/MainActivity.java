package com.example.pawelbaza;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    KotyDataBase kotyDatabase;
    EditText rasaKota;
    EditText wiekKota;
    EditText skalaLadnosci;
    ListView listviw;
    Button dodaj;

    List<Kot> listaKotow;
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

        rasaKota = findViewById(R.id.rasaKota);
        wiekKota = findViewById(R.id.wiekKota);
        skalaLadnosci = findViewById(R.id.skalaLadnosci);
        listviw = findViewById(R.id.listviw);
        dodaj = findViewById(R.id.dodaj);

        listaKotow = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                listaKotow
        );
        listviw.setAdapter(arrayAdapter);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rasa = rasaKota.getText().toString();
                String wiekStr = wiekKota.getText().toString();
                String ladnoscStr = skalaLadnosci.getText().toString();
                if (rasa.isEmpty() || wiekStr.isEmpty() || ladnoscStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Kot kot = new Kot(
                        rasa,
                        Integer.parseInt(wiekStr),
                        Integer.parseInt(ladnoscStr)
                );
                kotyDatabase.zwrocKotyDAO().wstawDoBazyKota(kot);
                listaKotow.add(kot);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        listaKotow.addAll(kotyDatabase.zwrocKotyDAO().zwrocWszystkieKotyZBazy());
        arrayAdapter.notifyDataSetChanged();
    }
}
