package com.example.ejercicio2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ejercicio2_4.db.DbSignaturess;
import com.example.ejercicio2_4.db.entidades.Signaturess;

import java.util.ArrayList;
import java.util.List;

public class FirmasCards extends AppCompatActivity {
    DbSignaturess signatures;

    private RecyclerView recyclerViewFirma;
    private RecyclerViewAdaptador adaptadorFirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmas_cards);

        recyclerViewFirma = (RecyclerView) findViewById (R.id.recyclerFirma);
        recyclerViewFirma.setLayoutManager(new LinearLayoutManager(this));

        adaptadorFirma = new RecyclerViewAdaptador (obtenerFirmas());
        recyclerViewFirma.setAdapter(adaptadorFirma);
    }

    public List<Signaturess> obtenerFirmas () {
        System.out.println(signatures.mostrarFirmas());
        return signatures.mostrarFirmas();
    }
}