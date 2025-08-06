package com.example.u6_c4_visor_imagenes1_inicio_clase;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;



public class MainActivity extends AppCompatActivity {
    private Button btnCargarImagen;
    private ImageView imagenSeleccionada;
    private static final int REQUEST_IMAGE_PICK = 100;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Button btnGuardar;
    private Button btnMostrarListado;
    private Uri imageUriSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnCargarImagen = findViewById(R.id.btnCargarImagen);
        imagenSeleccionada = findViewById(R.id.imagenSeleccionada);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnMostrarListado=findViewById(R.id.btnMostrarListado);




    }
}