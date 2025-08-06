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
import androidx.activity.result.contract.ActivityResultContracts;
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
        btnMostrarListado = findViewById(R.id.btnMostrarListado);


        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // manejar aquí la imagen seleccionada
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {// Se asegura de que la actividad terminó correctamente (RESULT_OK) y que hay datos disponibles (la imagen seleccionada)
                        Uri originalUri = result.getData().getData(); // Obtiene la URI original de la imagen que el usuario seleccionó en la galería.
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(originalUri);//Abre un InputStream para leer los datos binarios de la image desde la URI.
                            String nombreArchivo = "img_" + System.currentTimeMillis() + ".jpg";//Crea un nombre único para la imagen usando la hora actual (en milisegundos).
                            File file = new File(getFilesDir(), nombreArchivo);//Crea un archivo dentro del almacenamiento interno de la app.
                            FileOutputStream outputStream = new FileOutputStream(file);//Abre un OutputStream para escribir en el archivo.


                            byte[] buffer = new byte[1024];//Lee la imagen por partes (en bloques de 1024 bytes) y la escribe en el archivo local. Esto permite copiar imágenes de cualquier tamaño.
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, length);
                            }
                            inputStream.close();
                            outputStream.close();
                            imageUriSeleccionada = Uri.fromFile(file);//Obtiene una URI segura para la imagen local
                            imagenSeleccionada.setImageURI(imageUriSeleccionada); // y luego la muestra en el ImageView
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Error al copiar imagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUriSeleccionada != null) {
                    String linea = "imagen," + imagenSeleccionada.toString() + "\n";
                    try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("eventodata.txt", MODE_APPEND)))) {
                        bw.write(linea);
                        Toast.makeText(MainActivity.this, "Imagen guardada en el archivo", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this, "Error al guardar imagen", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No hay imagen seleccionada", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                imagePickerLauncher.launch(intent);
            }
        });
    }
}