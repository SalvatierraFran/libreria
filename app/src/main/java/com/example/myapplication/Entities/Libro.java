package com.example.myapplication.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "libros")
public class Libro {

    @PrimaryKey(autoGenerate = true)
    int id;
    String nombre;
    String Autor;

    public Libro()
    {    }

    public Libro(String nombre, String autor){
        this.nombre = nombre;
        this.Autor = autor;
    }

    public Libro(int id, String nombre, String autor) {
        this.id = id;
        this.nombre = nombre;
        Autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }
}
