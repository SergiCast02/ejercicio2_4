package com.example.ejercicio2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ejercicio2_4.db.DbSignaturess;
import com.example.ejercicio2_4.db.entidades.Signaturess;

import java.util.ArrayList;

public class FirmasCards extends AppCompatActivity {
    RecyclerView listaFirmas;
    ArrayList<Signaturess> listaArrayFirmas;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmas_cards);

        listaFirmas = findViewById(R.id.rvFirma);
        listaFirmas.setLayoutManager(new LinearLayoutManager(this));

        DbSignaturess db = new DbSignaturess(FirmasCards.this);

        listaArrayFirmas = new ArrayList<>();

        FirmaAdapter adapter = new FirmaAdapter(db.mostrarFirmas());
        listaFirmas.setAdapter(adapter);
    }


}