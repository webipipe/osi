package com.example.webosink;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Configuramos el VideoView para la reproducción local
        VideoView videoView = findViewById(R.id.videoView);

        // Construimos una URI del video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.trailer;

        // Convertimos la cadena en un URI
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // Agregamos controles de reproducción del video
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        // Vinculamos el controlador de video para mostrarlo en la app
        mediaController.setAnchorView(videoView);
        videoView.start();

        // Configuración del WebView para cargar video de YouTube
        WebView webView = findViewById(R.id.webView);

        // Configuramos el WebView para habilitar JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Agregamos la URL del video en formato embed
        String videoUrl = "https://www.youtube.com/embed/26LWvKv6Hfc";

        // Cargamos la URL en el WebView
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(videoUrl);

        // Configuración para la reproducción de sonido MP3
        findViewById(R.id.reproducir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creamos un MediaPlayer para reproducir el sonido
                MediaPlayer mediaPlayer = MediaPlayer.create(VideoActivity.this, R.raw.sonido);
                // Iniciamos la reproducción
                mediaPlayer.start();

                // Listener para liberar recursos cuando el sonido termine
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release(); // Liberamos recursos cuando el sonido termine
                    }
                });
            }
        });
    }
}
