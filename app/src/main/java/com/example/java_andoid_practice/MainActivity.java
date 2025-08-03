package com.example.java_andoid_practice;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAge;
    private RadioGroup grupoGeneros;
    private Spinner spRol;
    private CheckBox chkTerminos;
    private CheckBox chkInformacion;

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
    }
}