package com.example.ejercicio2_4.db.entidades;

import java.sql.Blob;

public class Signaturess {
    private int id;
    private byte[] firma;
    String descripcion;

    public Signaturess(){
    }

    public Signaturess(int id, byte[] firma, String descripcion) {
        this.id = id;
        this.firma = firma;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}