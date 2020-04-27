package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.Entities.Libro;

import java.util.List;

@Dao
public interface LibrosDao {

    @Query("SELECT * FROM libros")
    List<Libro> obtenerLibros();

    @Query("SELECT * FROM libros WHERE id = :unId")
    Libro obtenerLibroPorId(long unId);

    @Query("UPDATE libros SET Autor = :autor WHERE id = :id")
    void actualizarAutore(String autor, long id);

    @Insert
    void crearLibro(Libro unLibro);

    @Update
    void actualizarLibro(Libro unLibro);

    @Delete
    void eliminarLibro(Libro unLibro);
}
