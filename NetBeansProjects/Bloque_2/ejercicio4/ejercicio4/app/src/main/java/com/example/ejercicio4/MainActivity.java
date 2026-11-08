package com.example.ejercicio4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button botonIr;
    private EditText campoNombre, campoApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asociar objetos Java a XML
        botonIr = findViewById(R.id.boton);
        campoNombre = findViewById(R.id.inputNombre);
        campoApellido = findViewById(R.id.inputApellido);

        //eventos

        botonIr.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString();
            String apellido = campoApellido.getText().toString();

            Intent abrirPantala = new Intent(MainActivity.this, MainActivity2.class);
            //this es para decir estoy en la misma clase, class es para ir a la clase

            //Metodo 1, suando putExtra
            abrirPantala.putExtra("nombre", nombre);
            abrirPantala.putExtra("apellido", apellido);

            //metodo 3, usando SharedPreferences (por dentro usa un archivo XML para guardar las preferencias en el dispositivo local)
            //SharedPreferences prefs = getSharedPreferences("Datos", MODE_PRIVATE);
            //SharedPreferences.Editor editor = prefs.edit();
            //editor.putString("nombre", nombre);
            //editor.putString("apellido", apellido);
            //editor.apply();
            //editor.apply();

            startActivity(abrirPantala);
        });

    }
}