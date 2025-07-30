package com.example.java_android_practice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editAge;
    private RadioGroup grupoGeneros;
    private Spinner spRol;

    public EditText getEditName() {
        return editName;
    }

    public void setEditName(EditText editName) {
        this.editName = editName;
    }

    public EditText getEditAge() {
        return editAge;
    }

    public void setEditAge(EditText editAge) {
        this.editAge = editAge;
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

    public CheckBox getCkterminos() {
        return ckterminos;
    }

    public void setCkterminos(CheckBox ckterminos) {
        this.ckterminos = ckterminos;
    }

    public CheckBox getCkpromociones() {
        return ckpromociones;
    }

    public void setCkpromociones(CheckBox ckpromociones) {
        this.ckpromociones = ckpromociones;
    }

    public Button getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(Button btnCrear) {
        this.btnCrear = btnCrear;
    }

    public Button getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(Button btnClear) {
        this.btnClear = btnClear;
    }

    public Button getBtnMostrar() {
        return btnMostrar;
    }

    public void setBtnMostrar(Button btnMostrar) {
        this.btnMostrar = btnMostrar;
    }

    private CheckBox ckterminos;
    private CheckBox ckpromociones;
    private Button btnCrear;
    private Button btnClear;
    private Button btnMostrar;

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
    }
}