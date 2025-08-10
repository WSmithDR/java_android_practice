package com.example.u6_c4_visor_imagenes1_inicio_clase;

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
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    private Button btnGuardarImagen;

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
        btnGuardarImagen = findViewById(R.id.btnGuardar);
        imagenSeleccionada = findViewById(R.id.imagenSeleccionada);
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if(result.getResultCode()== RESULT_OK && result.getData()!=null){
                        Uri originalUri = result.getData().getData();

                        try{
                            InputStream inputStream = getContentResolver().openInputStream(originalUri);
                            String nombreArchivo = "img_"+System.currentTimeMillis()+".jpg";
                            File file = new File(getFilesDir(),nombreArchivo);
                            FileOutputStream outputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            int length;
                            while((length=inputStream.read(buffer))>0){
                                outputStream.write(buffer,0,length);
                            }
                            inputStream.close();
                            outputStream.close();
                            imageUriSeleccionada = Uri.fromFile(file);
                            imagenSeleccionada.setImageURI(imageUriSeleccionada);
                        }catch(IOException e){
                            e.printStackTrace();
                            Toast.makeText(this,"Error al copiar la imagen",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        btnCargarImagen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        imagePickerLauncher.launch(intent);
                    }
                }
        );


        btnGuardarImagen.setOnClickListener(
                new View.OnClickListener(

                ) {
                    @Override
                    public void onClick(View v) {
                        if(imageUriSeleccionada!=null){
                            String uri = imageUriSeleccionada.toString();
                            String linea = "imagen,"+uri+"\n";
                            try{
                                BufferedWriter bw = new BufferedWriter(
                                        new OutputStreamWriter(
                                                openFileOutput("eventodata.txt",MODE_APPEND)
                                        )
                                );
                                bw.write(linea);
                                bw.close();
                                Toast.makeText(MainActivity.this,"Evento guardado",Toast.LENGTH_SHORT).show();
                            }catch(IOException e){
                                    e.printStackTrace();
                                    Toast.makeText(
                                            MainActivity.this,
                                            "Error al guardar",
                                            Toast.LENGTH_SHORT
                                    ).show();
                            }
                        }else{
                            Toast.makeText(
                                    MainActivity.this,
                                    "Debe seleccionar una imagen antes de guardar",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
        );


    }
}