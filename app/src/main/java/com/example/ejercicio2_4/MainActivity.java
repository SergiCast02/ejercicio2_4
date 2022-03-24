package com.example.ejercicio2_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ejercicio2_4.db.DbSignaturess;

public class MainActivity extends AppCompatActivity {
    Lienzo lienzo_firma;
    EditText txt_descripcion;
    Button btn_verfirmas;
    Button btn_salvarfirma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_salvarfirma = findViewById(R.id.salvar);
        btn_salvarfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo_firma = findViewById(R.id.imgfirma);
                txt_descripcion = findViewById(R.id.descripcion);

                String descripcion = txt_descripcion.getText().toString();
                lienzo_firma.buildDrawingCache();
                Bitmap firma = lienzo_firma.getDrawingCache();

                if(descripcion.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese una descripción", Toast.LENGTH_SHORT).show();
                } else {
                    DbSignaturess dbFirmas = new DbSignaturess(MainActivity.this);
                    long id = dbFirmas.insertarFirma(firma, descripcion);
                    if (id>0){
                        Toast.makeText(getApplicationContext(),"Registro Guardado", Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                        startActivity(getIntent()); //Recargar Activity
                    }
                    else{Toast.makeText(getApplicationContext(),"No se guardó el Registro", Toast.LENGTH_SHORT).show();;}
                }
            }
        });

        btn_verfirmas = findViewById(R.id.verfirmas);
        btn_verfirmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FirmasCards.class);
                startActivity(i);
            }
        });
    }

    private void limpiar(){
        lienzo_firma.NuevoDibujo();
        txt_descripcion.setText("");
    }



}