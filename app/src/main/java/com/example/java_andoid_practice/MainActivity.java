package com.example.java_andoid_practice;

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

import com.example.java_andoid_practice.models.Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAge;
    private RadioGroup grupoGeneros;
    private Spinner spRol;
    private CheckBox chkTerminos;
    private CheckBox chkInformacion;
    private Button btnCrear;
    private Button btnBorrar;

    public Button getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(Button btnCrear) {
        this.btnCrear = btnCrear;
    }

    public Button getBtnBorrar() {
        return btnBorrar;
    }

    public void setBtnBorrar(Button btnBorrar) {
        this.btnBorrar = btnBorrar;
    }

    public Button getBtnMostrar() {
        return btnMostrar;
    }

    public void setBtnMostrar(Button btnMostrar) {
        this.btnMostrar = btnMostrar;
    }

    private Button btnMostrar;

    public EditText getEditTextName() {
        return editTextName;
    }

    public void setEditTextName(EditText editTextName) {
        this.editTextName = editTextName;
    }

    public EditText getEditTextAge() {
        return editTextAge;
    }

    public void setEditTextAge(EditText editTextAge) {
        this.editTextAge = editTextAge;
    }

    public RadioGroup getGrupoGeneros() {
        return grupoGeneros;
    }

    public void setGrupoGeneros(RadioGroup grupoGeneros) {
        this.grupoGeneros = grupoGeneros;
    }

    public Spinner getSpRol() {
        return spRol;
    }

    public void setSpRol(Spinner spRol) {
        this.spRol = spRol;
    }

    public CheckBox getChkTerminos() {
        return chkTerminos;
    }

    public void setChkTerminos(CheckBox chkTerminos) {
        this.chkTerminos = chkTerminos;
    }

    public CheckBox getChkInformacion() {
        return chkInformacion;
    }

    public void setChkInformacion(CheckBox chkInformacion) {
        this.chkInformacion = chkInformacion;
    }


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

        editTextName = findViewById(R.id.editText_name);
        editTextAge = findViewById(R.id.editText_age);
        grupoGeneros = findViewById(R.id.rg_generos);
        chkTerminos = findViewById(R.id.chk_terminos);
        chkInformacion = findViewById(R.id.chkinformacion);
        btnCrear = findViewById(R.id.btnCrear);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnMostrar = findViewById(R.id.btnMostrar);
        spRol=findViewById(R.id.spRol);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.roles_array, android.R.layout.simple_spinner_item);
        spRol.setAdapter(adapter);
    }

    public void crearCliente(View view){
        String name = editTextName.getText().toString().trim();
        String ageStr =editTextAge.getText().toString().trim();
        int selectedGenderId = grupoGeneros.getCheckedRadioButtonId();
        RadioButton rdseleccionado = findViewById(selectedGenderId);
        boolean terminosAceptados = chkTerminos.isChecked();
        boolean promocionesAceptadas = chkInformacion.isChecked();
        String rol = spRol.getSelectedItem().toString();

        //validaciones
        if(name.isEmpty() || ageStr.isEmpty() || rdseleccionado==null || !terminosAceptados || !promocionesAceptadas){
            Toast.makeText(this,"Por favor llene todos los campos y acepte los terminos y condiciones.",Toast.LENGTH_SHORT).show();
        }else{
            int edad = Integer.parseInt(ageStr);
            String genero = rdseleccionado.getText().toString();
            Cliente c = new Cliente(name, edad,genero,rol, terminosAceptados,promocionesAceptadas);
            Toast.makeText(this,String.format("Cliente nuevo %s",c.getNombre()),Toast.LENGTH_SHORT).show();
            guardarCliente(c.toString());
            presentarClientes();
            borrar(view);
        }

    }

    public void guardarCliente(String line){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(getFilesDir(),"/Clientes.txt"),true))){
            bw.write(line+"\n");
            System.out.println("Cliente guardado");
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void presentarClientes(){
        try(BufferedReader br = new BufferedReader(new FileReader(new File(getFilesDir(),"/Clientes.txt")))){
            String line = null;
            while((line = br.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrar(View view){
        editTextName.setText("");
        editTextAge.setText("");
        grupoGeneros.clearCheck();
        chkTerminos.setChecked(false);
        chkInformacion.setChecked(false);
        spRol.setSelection(0);
    }

    public void mostrarClientes(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}