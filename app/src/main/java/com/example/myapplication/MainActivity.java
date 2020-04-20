package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbRecordarCredenciales;
    public static final String APP_PREFS_NAME = "";
    public static final String KEY_CORREO = "KEY_CORREO";
    public static final String KEY_PASS = "KEY_PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicioSesion();

        cbRecordarCredenciales = findViewById(R.id.cb_recordar);

        SharedPreferences prefs = getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE);
        String email = prefs.getString(KEY_CORREO, "");
        String pass = prefs.getString(KEY_PASS, "");

        if(!email.isEmpty() && !pass.isEmpty()){
            Intent HomeIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(HomeIntent);
            finish();
        }
    }

    private void inicioSesion() {

        final EditText et_user = findViewById(R.id.et_user);
        final EditText et_pass = findViewById(R.id.et_pass);

        ((Button)findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(et_user.getText().toString().equals("")) && !(et_pass.getText().toString().equals(""))){
                    goToHome(et_user.getText().toString(), et_pass.getText().toString());
                }
                else{
                    Toast.makeText(MainActivity.this, "Ambos campos deben ser completados!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToHome(String user, String pass) {

        boolean recordar = cbRecordarCredenciales.isChecked();

        if(recordar){
            SharedPreferences prefs = getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_CORREO, user);
            editor.putString(KEY_PASS, pass);
            editor.apply();
        }

        Intent HomeIntent = new Intent(MainActivity.this, HomeActivity.class);
        HomeIntent.putExtra("USER", user);
        startActivity(HomeIntent);
    }
}
