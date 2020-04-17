package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Entities.Libro;

import java.util.List;

public class LibrosAdapter extends BaseAdapter {

    private List<Libro> misLibros;

    public LibrosAdapter() {
    }

    public LibrosAdapter(List<Libro> misLibros) {
        this.misLibros = misLibros;
    }

    @Override
    public int getCount() {
        return misLibros.size();
    }

    @Override
    public Object getItem(int position) {
        return misLibros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return misLibros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_libro, parent, false);

        Libro libro = misLibros.get(position);

        TextView tv_nombre = v.findViewById(R.id.tv_nombre);
        TextView tv_autor = v.findViewById(R.id.tv_autor);

        tv_nombre.setText(libro.getNombre());
        tv_autor.setText(libro.getAutor());

        return v;
    }

    public void setLibros(List<Libro> libros){
        this.misLibros = libros;
    }
}
