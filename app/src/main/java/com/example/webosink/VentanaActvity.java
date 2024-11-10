package com.example.webosink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class VentanaActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana);

        // Configuramos el listener para el botón "Ver Video"
        Button btnVerVideo = findViewById(R.id.btn_ver_video);
        btnVerVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un Intent para abrir VideoActivity
                Intent intent = new Intent(VentanaActvity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickComprar(View view) {
        // Crea un nuevo Intent para ir a la actividad de compra
        Intent intent = new Intent(this, CompraActivity.class);
        startActivity(intent);
    }

    public void irAMapa(View view) {
        // Crea un Intent para ir a la actividad del mapa
        Intent intent = new Intent(this, MapaActivity.class);
        startActivity(intent);
    }

    public void irCrud(View view) {
        // Crea un Intent para ir a la actividad CRUD
        Intent intent = new Intent(this, CrudActivity.class);
        startActivity(intent);
    }
}
