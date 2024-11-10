package com.example.webosink;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "webosink.db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla
    private static final String TABLE_PEDIDO = "pedido";

    // Sentencia SQL para crear la tabla 'pedido'
    private static final String CREATE_TABLE_PEDIDO = "CREATE TABLE " + TABLE_PEDIDO + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "cliente TEXT, "
            + "modelo TEXT, "
            + "talla TEXT, "
            + "cantidad INTEGER, "
            + "precio REAL);";

    // Constructor
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método para crear la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla 'pedido'
        db.execSQL(CREATE_TABLE_PEDIDO);
    }

    // Método para actualizar la base de datos (si se cambia la estructura)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si existe una versión anterior, la eliminamos y creamos la nueva
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDO);
        onCreate(db);
    }

    // Método para abrir la base de datos en modo escritura
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    // Método para abrir la base de datos en modo solo lectura
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
}
