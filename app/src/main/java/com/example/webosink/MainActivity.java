package com.example.webosink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaro Variables que el usuario utilizará
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazo las variables con los id correspondientes del activity_main
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);
        spinner = findViewById(R.id.spinnerRoles);
        progressBar = findViewById(R.id.progressBar);


        // Arreglo de elementos que aparecerán en mi Spinner
        String[] roles = {"Administrador", "Usuario"};

        // Creamos un ArrayAdapter para poblar el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el ArrayAdapter al Spinner
        spinner.setAdapter(adapter);
    }

    // Este método será llamado cuando se haga clic en el botón de login tiene que ser el mismo nombre del onClick
    public void onClickAcceder(View view) {
        progressBar.setVisibility(View.VISIBLE);
        // Declaramos variables para obtener los valores ingresados en los campos
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        // Si está vacío, saltará un Toast
        if (user.isEmpty()) {
            Toast.makeText(this, "El campo de usuario está vacío", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);  // Ocultar la ProgressBar si hay error
            return;
        }
        // Si está vacío, saltará un Toast
        if (pass.isEmpty()) {
            Toast.makeText(this, "El campo de contraseña está vacío", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);  // Ocultar la ProgressBar si hay error
            return;
        }

        // Crear un nuevo hilo para hacer la verificación en segundo plano
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Simula la verificación de credenciales
                try {
                    Thread.sleep(5000); // Esperar 5 segundos para Verificar el usuario

                    // Verifico las credenciales y dependiendo del usuario me enviará a diferentes activities
                    runOnUiThread(new Runnable() { // Necesario para actualizar la UI desde otro hilo
                        @Override
                        public void run() {
                            if (user.equals("usuario1") && pass.equals("1234") && rol.equals("Usuario")) {
                                // Iniciar la actividad de AccesoActivity
                                Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
                                startActivity(intent);
                            } else {
                                // Si las credenciales son incorrectas, mostrar un mensaje
                                Toast.makeText(MainActivity.this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
                                // Ocultar la ProgressBar después de la operación
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}



