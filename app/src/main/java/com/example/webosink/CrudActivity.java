package com.example.webosink;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CrudActivity extends AppCompatActivity {

    // Variables para los elementos del formulario
    private EditText edtCliente, edtModelo, edtCantidad, edtPrecio;
    private Spinner spnTalla;
    private ListView lstPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        // Inicializar los controles
        edtCliente = findViewById(R.id.edtCliente);
        edtModelo = findViewById(R.id.edtModelo);
        edtCantidad = findViewById(R.id.edtCantidad);
        edtPrecio = findViewById(R.id.edtPrecio);
        spnTalla = findViewById(R.id.spnTalla);
        lstPedidos = findViewById(R.id.lstPedidos);

        // Cargar la lista de pedidos
        cargarPedidos();

        // Configurar el ListView para escuchar clics
        lstPedidos.setOnItemClickListener((parent, view, position, id) -> {
            // Obtener el ID del pedido desde el ListView
            String selectedItem = (String) parent.getItemAtPosition(position);
            String[] datos = selectedItem.split(" - ");
            edtCliente.setText(datos[0]);
            edtModelo.setText(datos[1]);
            edtCantidad.setText(datos[2]);
            edtPrecio.setText(datos[3]);
        });
    }

    // Método para cargar los pedidos en el ListView
    private void cargarPedidos() {
        DataHelper dh = new DataHelper(this);
        Cursor c = dh.getReadableDatabase().rawQuery("SELECT * FROM pedido", null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            String[] pedidos = new String[c.getCount()];
            int i = 0;
            do {
                String pedido = c.getString(1) + " - " + c.getString(2) + " - " + c.getInt(4) + " - " + c.getDouble(5);
                pedidos[i] = pedido;
                i++;
            } while (c.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pedidos);
            lstPedidos.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No hay pedidos registrados", Toast.LENGTH_LONG).show();
        }
    }

    // Método para agregar un nuevo pedido
    public void onClickAgregar(View view) {
        String cliente = edtCliente.getText().toString();
        String modelo = edtModelo.getText().toString();
        String talla = spnTalla.getSelectedItem().toString();
        String cantidad = edtCantidad.getText().toString();
        String precio = edtPrecio.getText().toString();

        if (cliente.isEmpty() || modelo.isEmpty() || cantidad.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            DataHelper dh = new DataHelper(this);
            dh.getWritableDatabase().execSQL("INSERT INTO pedido(cliente, modelo, talla, cantidad, precio) VALUES ('" +
                    cliente + "', '" + modelo + "', '" + talla + "', " + cantidad + ", " + precio + ")");
            Toast.makeText(this, "Pedido agregado correctamente", Toast.LENGTH_SHORT).show();
            cargarPedidos();  // Actualizar la lista de pedidos
        }
    }

    // Método para modificar un pedido existente
    public void onClickModificar(View view) {
        String cliente = edtCliente.getText().toString();
        String modelo = edtModelo.getText().toString();
        String talla = spnTalla.getSelectedItem().toString();
        String cantidad = edtCantidad.getText().toString();
        String precio = edtPrecio.getText().toString();

        if (cliente.isEmpty() || modelo.isEmpty() || cantidad.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            DataHelper dh = new DataHelper(this);
            dh.getWritableDatabase().execSQL("UPDATE pedido SET cliente = '" + cliente + "', modelo = '" + modelo +
                    "', talla = '" + talla + "', cantidad = " + cantidad + ", precio = " + precio + " WHERE id = ?");
            Toast.makeText(this, "Pedido modificado correctamente", Toast.LENGTH_SHORT).show();
            cargarPedidos();  // Actualizar la lista de pedidos
        }
    }

    // Método para eliminar un pedido
    public void onClickEliminar(View view) {
        DataHelper dh = new DataHelper(this);
        dh.getWritableDatabase().execSQL("DELETE FROM pedido WHERE cliente = '" + edtCliente.getText().toString() + "'");
        Toast.makeText(this, "Pedido eliminado correctamente", Toast.LENGTH_SHORT).show();
        cargarPedidos();  // Actualizar la lista de pedidos
    }
}
