package com.example.firstappp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private TableLayout tablelayout;
    private ArrayList<Cliente> listaClientes;

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

        tablelayout=findViewById(R.id.tablaClientes);
    }

    private void cargarClientes(){
        listaClientes=new ArrayList<>();
        File f=new File(getFilesDir(),"/Clientes.txt");
        try(BufferedReader br=new BufferedReader(new FileReader(f))){
            String line = br.readLine();
            while(br.readLine()!=null){
                System.out.println(line);
                String[] datosCliente=line.split(",");
                Cliente cliente=new Cliente(
                        datosCliente[0],
                        Integer.parseInt(datosCliente[1]),
                        datosCliente[2],
                        Boolean.parseBoolean(datosCliente[3]),
                        Boolean.parseBoolean(datosCliente[4])
                );
                listaClientes.add(cliente);
            }
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    private void mostrarDatosClientes(){
        TableRow.LayoutParams params = new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT, 0);

        TableRow tHeader = new TableRow(this);

        TextView titNombre = new TextView(this);
        titNombre.setText("Nombre");
        titNombre.setLayoutParams(params);

        TextView titEdad = new TextView(this);
        titNombre.setText("Edad");
        titNombre.setLayoutParams(params);
        tHeader.addView(titNombre);
        tHeader.addView(titEdad);

        tablelayout.addView(tHeader);

        for(Cliente cliente:listaClientes){
            TextView t1 = new TextView(this);
            t1.setText(cliente.getNombre());
            t1.setLayoutParams(params);

            TextView t2 = new TextView(this);
            t2.setText(String.valueOf(cliente.getEdad()));
            t2.setLayoutParams(params);

            TableRow tablerow = new TableRow(this);
            tablerow.addView(t1);
            tablerow.addView(t2);

            tablelayout.addView(tablerow);
        }

        public void volver(View view){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        public void salir(View view){
            finish();
        }
    }
}