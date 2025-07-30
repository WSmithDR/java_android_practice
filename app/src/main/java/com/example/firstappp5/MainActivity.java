package com.example.firstappp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Modelo.Cliente;


public class MainActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editAge;
    private RadioGroup grupoGeneros;
    private CheckBox ckterminos;
    private CheckBox ckpromociones;
    private Button btncrear;
    private Button btnclear;
    private Button btnmostrar;
    private Spinner spRol;

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

        editName=findViewById(R.id.editText_name);
        editAge=findViewById(R.id.editText_age);
        grupoGeneros=findViewById(R.id.rg_generos);
        ckterminos=findViewById(R.id.ck_terminos);
        ckpromociones=findViewById(R.id.chkinformacion);
        btncrear=findViewById(R.id.btnCrear);
        btnclear=findViewById(R.id.btnborrar);
        btnmostrar=findViewById(R.id.btnMostrar);
        spRol=findViewById(R.id.spRol);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRol.setAdapter(adapter);

    }
    public void crearCliente(View view){
        String name=editName.getText().toString().trim();
        String ageStr= editAge.getText().toString().trim();
        int selectedGenderId=grupoGeneros.getCheckedRadioButtonId();
        RadioButton rdSeleccionado=findViewById(selectedGenderId);
        boolean terminosAceptados= ckterminos.isChecked();
        boolean PromocionesAceptadas=ckpromociones.isChecked();
        String rol=spRol.getSelectedItem().toString();
        if(name.isEmpty() || ageStr.isEmpty() || rdSeleccionado==null || !terminosAceptados || !PromocionesAceptadas){
            Toast.makeText(this,"Por favor llene todos los campos y acepte los términos y condiciones",Toast.LENGTH_SHORT).show();
        }else{
            int edad=Integer.parseInt(ageStr);
            String genero=rdSeleccionado.getText().toString();
            Cliente cliente=new Cliente(name,edad,genero,terminosAceptados,PromocionesAceptadas);
            Toast.makeText(this,"Cliente nuevo"+ cliente.getNombre(),Toast.LENGTH_SHORT).show();


            guardarCliente(cliente.toString());
        }
    }
    public void Borrar(View view){
       editName.setText("");
       editAge.setText("");
       grupoGeneros.clearCheck();
       ckterminos.setChecked(false);
       ckpromociones.setChecked(false);
       spRol.setSelection(0);
    }
    public void MostrarClientes(View view){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);

    }

    public void guardarCliente(String datosCliente){
        File f = new File(getFilesDir(),"/Clientes.txt");

        try(BufferedWriter bw=new BufferedWriter(new FileWriter(f,true))){
            bw.write(datosCliente);
            bw.newLine();
        }catch(IOException e){
            System.out.println(e.getStackTrace());
        }

        System.out.println("Cliente guardado correctamente");
    }
}