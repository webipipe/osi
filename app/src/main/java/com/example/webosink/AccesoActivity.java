package com.example.webosink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

//Declara la clase AccesoActivity que extiende AppCompatActivity.
public class AccesoActivity extends AppCompatActivity {
    //Método onCreate, llamado cuando la actividad se crea por primera vez.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Llama al método onCreate de la clase base para inicializar la actividad correctamente.
        super.onCreate(savedInstanceState);
        //Establece el diseño de la actividad con el archivo XML correspondiente (activity_acceso).
        setContentView(R.layout.activity_acceso);
    }

    //Este método será llamado cuando se haga clic en el botón y texto
    public void onClickVentana(View view) {
        //Crea un nuevo Intent, que es una forma de iniciar otra actividad. 'this' se refiere al contexto actual, y 'VentanaActvity.class' especifica la actividad que se quiere abrir.
        Intent intent = new Intent(this, VentanaActvity.class);
        //Llama al método startActivity() para iniciar la actividad especificada en el Intent (VentanaActvity). Esto cambia la pantalla actual a la nueva actividad.
        startActivity(intent);
    }

}


