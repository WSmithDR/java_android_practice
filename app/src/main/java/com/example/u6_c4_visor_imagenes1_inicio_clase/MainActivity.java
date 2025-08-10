package com.example.u6_c4_visor_imagenes1_inicio_clase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    private Button cargarImgBtn;
    private ImageView imagenSeleccionada;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private Uri selectedImgUri;
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


        this.cargarImgBtn = findViewById(R.id.btnCargarImagen);
        this.imagenSeleccionada = findViewById(R.id.imagenSeleccionada);
        this.cargarImgBtn = findViewById(R.id.btnCargarImagen);

    }
}