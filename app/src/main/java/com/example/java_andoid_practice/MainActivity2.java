package com.example.java_andoid_practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_andoid_practice.models.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cargarClientes();
    }

    public void volverPrincipal(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void salir(View view){
        finishAffinity();
    }

    private void cargarClientes(){
        listaClientes = new ArrayList<>();
        File f = new File(getFilesDir(),"/Clientes.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(f))){
        String line = null;
        while((line=br.readLine())!=null){
            System.out.println(line);
            String datos[] = line.split(",");
            String nombre = datos[0];
            int edad = Integer.parseInt(datos[1]);
            String genero = datos[2];
            boolean terminos = Boolean.parseBoolean(datos[3]);
            boolean promociones = Boolean.parseBoolean(datos[4]);
            listaClientes.add(new Cliente(nombre,edad,genero,terminos,promociones));
        }
        System.out.println(listaClientes.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}