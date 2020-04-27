package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Entities.Libro;
import com.example.myapplication.Entities.LibroManager;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.LibrosDao;

public class AgregarLibroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        SetUpToolbar();
        //agregarLibro();
        agregarLibroDb();
        limpiarCampos();
    }

    //region agregarLibroDb
    private void agregarLibroDb() {
        final String name = ((EditText)findViewById(R.id.et_add_name)).getText().toString();
        final String autor = ((EditText)findViewById(R.id.et_add_autor)).getText().toString();

        ((Button)findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libro unLibro = new Libro(name, autor);

                AppDataBase db = AppDataBase.getInstance(AgregarLibroActivity.this);
                LibrosDao dao = db.getLibrosDao();
                dao.crearLibro(unLibro);

                Toast.makeText(AgregarLibroActivity.this, "Libro agregado!", Toast.LENGTH_SHORT).show();

                //setResult(RESULT_OK);

                finish();
            }
        });
    }
    //endregion

    //region LimpiarCampos
    private void limpiarCampos() {
        ((Button)findViewById(R.id.btn_clean)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)findViewById(R.id.et_add_name)).getText().clear();
                ((EditText)findViewById(R.id.et_add_autor)).getText().clear();
            }
        });
    }
    //endregion

    //region agregarLibro
    /*private void agregarLibro() {
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
    }*/
    //endregion

    //region setUpToolbar
    private void SetUpToolbar() {
        Toolbar miToolbar = findViewById(R.id.agregarLibroToolbar);
        setSupportActionBar(miToolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Agregar libro");
        }
    }
    //endregion
}
