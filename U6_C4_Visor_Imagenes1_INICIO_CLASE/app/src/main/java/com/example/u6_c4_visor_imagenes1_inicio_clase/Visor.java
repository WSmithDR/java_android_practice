package com.example.u6_c4_visor_imagenes1_inicio_clase;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class Visor extends AppCompatActivity {
    LinearLayout contenedorImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contenedorImagenes = findViewById(R.id.contenedorImagenes);
        cargarImagenes();
    }



    /***
     * Este metodo lee el archivo eventodata.txt y obtiene la ruta de la imagen, la convierte en una URI y la carga en un imageView
     * Tambien muestra la informacion adicional de la imagem
     * La imagen y el texto adicional van en un linearLayout interno que agrupe estos elementos al mostrarlos
     */
    private void cargarImagenes(){

        try (FileInputStream fis = openFileInput("eventodata.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis));){

            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",", 3); // Soporta texto adicional


                String uriStr = partes[1];
                String texto = partes[0];


                Uri uri = Uri.parse(uriStr);

                LinearLayout layoutInterno = new LinearLayout(this);
                layoutInterno.setOrientation(LinearLayout.VERTICAL);
                layoutInterno.setPadding(0, 0, 0, 40);

                ImageView imageView = new ImageView(this);
                imageView.setImageURI(uri);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageURI(uri);

                TextView textView = new TextView(this);
                textView.setText(texto);
                textView.setTextSize(16);

                layoutInterno.addView(imageView);
                layoutInterno.addView(textView);

                contenedorImagenes.addView(layoutInterno);
            }


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show();
        }
    }
}