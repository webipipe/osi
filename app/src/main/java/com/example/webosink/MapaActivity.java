package com.example.webosink;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import androidx.appcompat.app.AppCompatActivity;

public class MapaActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa); // Asegúrate de que esto sea el nombre correcto de tu layout

        // Carga la configuración del mapa usando las preferencias predeterminadas.
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        // Obtiene la referencia al componente MapView del layout.
        mapView = findViewById(R.id.mapView);
        // Establece la fuente de azulejos del mapa a MAPNIK (mapa estándar).
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        // Activa los controles de zoom en el mapa.
        mapView.setBuiltInZoomControls(true);
        // Activa el control multitáctil para el mapa (zoom con dos dedos).
        mapView.setMultiTouchControls(true);

        // Coordenadas de tienda.
        double TiendaZapas = -33.49320; // Latitud
        double TiendaZapas3 = -70.67640; // Longitud d.

        // Crear objetos GeoPoint para las coordenadas definidas.
        GeoPoint IpZapas = new GeoPoint(TiendaZapas, TiendaZapas3);
        // Configura la vista inicial del mapa centrada en la tienda
        mapView.getController().setZoom(15.0);
        // Centra el mapa en el punto de Santiago.
        mapView.getController().setCenter(IpZapas);

        // Crear un marcador para Tienda de zapas.
        Marker Zapasmarc = new Marker(mapView);
        Zapasmarc.setPosition(IpZapas);
        Zapasmarc.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Zapasmarc.setTitle("Tienda Zapas");
        Zapasmarc.setSnippet("Zapas");



        // Agregar el marcador al mapa.
        mapView.getOverlays().add(Zapasmarc);

        // Coordenadas De Metro 
        double MetroLat = -33.4461573; // Latitud
        double MetroLon = -70.6604831; // Longitud

        // Crear objeto GeoPoint para el .
        GeoPoint metropoin = new GeoPoint(MetroLat, MetroLon);

        // Crear un marcador para el .
        Marker marcadorMetro = new Marker(mapView);
        marcadorMetro.setPosition(metropoin);
        marcadorMetro.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorMetro.setTitle("Metro");
        marcadorMetro.setSnippet("Metro");

        // Agregar el marcador del metro al mapa.
        mapView.getOverlays().add(marcadorMetro);
 // Crear una línea que conecte los 2 Marcadores
         Polyline linea = new Polyline();
         // Añade el punto del Ip y el parque a la línea.
         linea.addPoint(IpZapas);
         linea.addPoint(metropoin);
         // Establece el color de la línea (azul en formato ARGB).
         linea.setColor(0xFF0000FF);
         // Establece el ancho de la línea a 5 píxeles.
         linea.setWidth(5);
         // Añadir la línea al mapa.
         mapView.getOverlayManager().add(linea);






        // Configurar el Spinner para cambiar el tipo de mapa.
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte", "Mapa Topográfico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);

        // Listener para detectar cambios en la selección del Spinner.
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mapView.setTileSource(TileSourceFactory.MAPNIK);
                        break;
                    case 1:
                        mapView.setTileSource(new XYTileSource(
                                "PublicTransport",
                                0, 18, 256, ".png", new String[]{
                                "https://tile.memomaps.de/tilegen/"}));
                        break;
                    case 2:
                        mapView.setTileSource(new XYTileSource(
                                "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                                "https://a.tile.opentopomap.org/",
                                "https://b.tile.opentopomap.org/",
                                "https://c.tile.opentopomap.org/"}));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }

        });
    }



 














}
