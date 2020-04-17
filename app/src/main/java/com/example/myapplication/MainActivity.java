package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicioSesion();
    }

    private void inicioSesion() {

        final EditText et_user = findViewById(R.id.et_user);
        final EditText et_pass = findViewById(R.id.et_pass);

        ((Button)findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(et_user.getText().toString().equals("")) && !(et_pass.getText().toString().equals(""))){
                    goToHome(et_user.getText().toString());
                }
                else{
                    Toast.makeText(MainActivity.this, "Ambos campos deben ser completados!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToHome(String user) {
        Intent HomeIntent = new Intent(MainActivity.this, HomeActivity.class);
        HomeIntent.putExtra("USER", user);
        startActivity(HomeIntent);
    }
}
