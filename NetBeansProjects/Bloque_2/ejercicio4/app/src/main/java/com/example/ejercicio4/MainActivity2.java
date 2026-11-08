package com.example.ejercicio4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private Button botonV;
    private TextView nombre;
    private TextView apellido;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.activity_main2);

        botonV = findViewById(R.id.volver);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);

        Intent intent = getIntent();
        String textoNombre = intent.getStringExtra("nombre");
        String textoApellido = intent.getStringExtra("apellido");

        SharedPreferences prefs = getSharedPreferences("Datos", MODE_PRIVATE);
        nombre.setText(prefs.getString("nombre", ""));
        apellido.setText(prefs.getString("apellido", ""));





    }
}
