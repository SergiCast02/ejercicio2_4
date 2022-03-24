package com.example.ejercicio2_4.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.ejercicio2_4.db.entidades.Signaturess;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DbSignaturess extends DbHelper{
    Context context;

    public DbSignaturess(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarFirma(Bitmap imagen, String descripcion){
        long id = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
        imagen.compress(Bitmap.CompressFormat.PNG, 0 , baos);
        byte[] blob = baos.toByteArray();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "INSERT INTO "+ TABLE_FIRMAS + " (imagen, descripcion) VALUES(?,?)";
        SQLiteStatement insert = db.compileStatement(sql);
        insert.clearBindings();
        insert.bindBlob(1, blob);
        insert.bindString(2, descripcion);
        id = insert.executeInsert();
        db.close();

        return id;
    }

    public Bitmap buscarImagen(long id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = String.format("SELECT * FROM " + TABLE_FIRMAS + " WHERE id = %d", id);
        Cursor cursor = db.rawQuery(sql, new String[] {});
        Bitmap bitmap = null;
        if(cursor.moveToFirst()){
            byte[] blob = cursor.getBlob(1);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return bitmap;
    }

    public ArrayList<Signaturess> mostrarFirmas(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Signaturess> listaFirmas = new ArrayList<>();
        Signaturess firma = null;
        Cursor cursorFirmas = null;

        cursorFirmas = db.rawQuery("SELECT * FROM " + TABLE_FIRMAS,null);

        if(cursorFirmas.moveToFirst()){
            do{
                firma = new Signaturess();
                firma.setId(cursorFirmas.getInt(0));
                firma.setFirma(cursorFirmas.getBlob(1));
                firma.setDescripcion(cursorFirmas.getString(2));
                listaFirmas.add(firma);
            }while (cursorFirmas.moveToNext());
        }

        cursorFirmas.close();

        return listaFirmas;
    }


    public Signaturess verFirmas(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Signaturess firma = null;
        Cursor cursorFirmas = null;

        cursorFirmas = db.rawQuery("SELECT * FROM " + TABLE_FIRMAS + " WHERE id = " + id + " LIMIT 1",null);

        if(cursorFirmas.moveToFirst()){
            firma = new Signaturess();
            firma.setId(cursorFirmas.getInt(0));
            firma.setFirma(cursorFirmas.getBlob(1));
            firma.setDescripcion(cursorFirmas.getString(2));
        }

        cursorFirmas.close();

        return firma;
    }

    public boolean eliminarFirma(int id) {

        boolean isCorrecto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_FIRMAS + " WHERE id = '" + id + "'");
            isCorrecto = true;
        } catch (Exception ex) {
            ex.toString();
            isCorrecto = false;
        } finally {
            db.close();
        }

        return isCorrecto;
    }
}
