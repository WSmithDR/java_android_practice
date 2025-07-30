package com.example.firstappp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Modelo.Cliente;

public class MainActivity2 extends AppCompatActivity {
    private TableLayout tableLayout;
    private ArrayList<Cliente> listaCliente;

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
        tableLayout=findViewById(R.id.tablaClientes);
        cargarCliente();
        mostrarDatosCliente();
    }

    public void cargarCliente(){
        listaCliente= new ArrayList<>();
        File f= new File(getFilesDir(),"/Cliente.txt");
        try(BufferedReader reader=new BufferedReader(new FileReader(f))){
            String line=reader.readLine();
            while(reader.readLine()!=null){
                System.out.println(line);
                String datos[]=line.split(",");
                String name= datos[0];
                int edad= Integer.valueOf(datos[1]);
                String gen= datos[2];
                boolean terminos=Boolean.valueOf(datos[3]);
                boolean prom=Boolean.valueOf(datos[4]);
                listaCliente.add(new Cliente(name,edad,gen,terminos,prom));


            }
        }catch (IOException e){
            System.out.println("Se cayó al leer");
        }
    }
    private void mostrarDatosCliente(){
        TableRow.LayoutParams LayoutParams=new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1);
        TableRow theader=new TableRow(this);
        TextView titNombre=new TextView(this);
        titNombre.setText("Nombre");
        titNombre.setLayoutParams(LayoutParams);

        TextView titEdad=new TextView(this);
        titEdad.setText("Edad");
        titEdad.setLayoutParams(LayoutParams);

        theader.addView(titNombre);
        theader.addView(titEdad);

        tableLayout.addView(theader);

        for(Cliente c:listaCliente){
            TextView t1=new TextView(this);
            t1.setText(c.getNombre());
            t1.setLayoutParams(LayoutParams);

            TextView t2=new TextView(this);
            t2.setText(c.getEdad());
            t2.setLayoutParams(LayoutParams);

            TableRow tableRow=new TableRow(this);
            tableRow.addView(t1);
            tableRow.addView(t2);

            tableLayout.addView(tableRow);

        }
    }
    public void volver(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public void salir(View view){
        finishAffinity();
    }

}