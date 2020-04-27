package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.Entities.Libro;
import com.example.myapplication.Entities.LibroManager;
import com.example.myapplication.database.AppDataBase;
import com.example.myapplication.database.LibrosDao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView lvLibros;
    private LibrosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        saludarUsuario();
        setUpToolbar();
        setUpAdapter();
        itemSeleccionado();

        /*SearchView searchView = findViewById(R.id.sv_libros);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*adapter.setLibros(getLibrosDb());
        adapter.notifyDataSetChanged();*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLibrosDb();
        adapter.notifyDataSetChanged();
    }

    //region seleccionarItemEnListView
    private void itemSeleccionado() {
        final ListView list = (ListView)findViewById(R.id.lv_libros);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro unLibro = (Libro)list.getItemAtPosition(position);

                Toast.makeText(HomeActivity.this, unLibro.getNombre() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    //endregion

    //region getLibros
    /*private List<Libro> getLibros() {
        List<Libro> misLibros = new ArrayList<>();

        misLibros.add(new Libro(0, "Canción de hielo y fuego", "George R. R. Martin"));
        misLibros.add(new Libro(1, "Harry Potter y el cáliz de fuego", "J. K. Rowling"));
        misLibros.add(new Libro(2, "Los juegos del hambre en llamas", "Suzanne Collins"));
        misLibros.add(new Libro(3, "Maze Runner", "James Dashner"));

        return LibroManager.getInstance().getLibros();
    }*/
    //endregion

    //region getLibrosDb
    public List<Libro> getLibrosDb(){
        AppDataBase db = AppDataBase.getInstance(this);
        LibrosDao dao = db.getLibrosDao();
        List<Libro> libros = dao.obtenerLibros();

        return libros;
    }
    //endregion

    //region setUpAdapter
    private void setUpAdapter() {
        lvLibros = findViewById(R.id.lv_libros);
        adapter = new LibrosAdapter(getLibrosDb());
        lvLibros.setAdapter(adapter);
    }
    //endregion

    //region setUpToolbar
    private void setUpToolbar() {
        Toolbar homeToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(homeToolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Mis libros");
        }
    }
    //endregion

    //region saludarUsuario
    private void saludarUsuario() {
        Bundle homeBundle = getIntent().getExtras();
        if(homeBundle != null){
            String user = homeBundle.getString("USER", "");
            String rdo = "Bienvenido " + user + "!";

            Toast.makeText(HomeActivity.this, rdo, Toast.LENGTH_LONG).show();
        }
    }
    //endregion

    //region crearMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //endregion

    //region seleccionarItemEnMenu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_add:
                goToAgregarLibro();
                break;

            case R.id.action_logoff:
                logOff();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Desloguear
    private void logOff() {
        SharedPreferences prefs = getSharedPreferences(MainActivity.APP_PREFS_NAME, MODE_PRIVATE);
        if(prefs.contains(MainActivity.KEY_CORREO) && prefs.contains(MainActivity.KEY_PASS)){
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); //Borra todoo.
            editor.apply();
        }

        Intent lofOffIntent = new Intent(this, MainActivity.class);
        startActivity(lofOffIntent);
        finish();
    }
    //endregion

    //region goToAgregarLibro
    private void goToAgregarLibro() {
        Intent agregarLibroIntent = new Intent(HomeActivity.this, AgregarLibroActivity.class);
        startActivity(agregarLibroIntent);
    }
    //endregion


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if(requestCode == 11){
            if(resultCode == RESULT_OK){
                getLibrosDb();
                adapter.notifyDataSetChanged();
            }
        }*/
    }
}
