package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Entities.Libro;
import com.example.myapplication.Entities.LibroManager;

public class AgregarLibroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        SetUpToolbar();
        agregarLibro();
        limpiarCampos();
    }

    private void limpiarCampos() {
        ((Button)findViewById(R.id.btn_clean)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)findViewById(R.id.et_add_name)).getText().clear();
                ((EditText)findViewById(R.id.et_add_autor)).getText().clear();
            }
        });
    }

    private void agregarLibro() {
        final EditText et_nombre = findViewById(R.id.et_add_name);
        final EditText et_autor = findViewById(R.id.et_add_autor);

        ((Button)findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(et_nombre.getText().toString().equals("")) && !(et_autor.getText().toString().equals(""))){

                    Libro unLibro = new Libro();

                    unLibro.setNombre(et_nombre.getText().toString());
                    unLibro.setAutor(et_autor.getText().toString());

                    LibroManager.getInstance().agregarLibro(unLibro);

                    Toast.makeText(v.getContext(), unLibro.getNombre() + " - " + unLibro.getAutor(), Toast.LENGTH_SHORT).show();

                    finish();
                }else{
                    Toast.makeText(v.getContext(), "Te faltó completar algún campo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SetUpToolbar() {
        Toolbar miToolbar = findViewById(R.id.agregarLibroToolbar);
        setSupportActionBar(miToolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Agregar libro");
        }
    }
}
