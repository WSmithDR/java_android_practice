package com.example.u6_c3_consultadispositivos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Color;

import Modelo.DispositivoInteligente;
import Modelo.Movil;
import Modelo.Smartwatch;
import Modelo.Tablet;

public class MainActivity extends AppCompatActivity {
    private Spinner spTipo;
    private LinearLayout contenido;
    private List<Movil> moviles;
    private List<Tablet> tablets;
    private List<Smartwatch> smartwatches;

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


        spTipo = findViewById(R.id.spTipo);
        contenido = findViewById(R.id.contenidoDinamico);
        contenido.setPadding(0, 36, 0, 16);
        // inicializa el spinner con los tipos dispositivos inteligentes usando el array de strings R.array.device_types que se definió en el archivo strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.device_types, android.R.layout.simple_spinner_item);//crea el adaptador con el array de strings
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//define define cómo se verán los elementos del Spinner cuando el usuario haga clic para ver las opciones desplegables
        spTipo.setAdapter(adapter); //le dice al Spinner que use el ArrayAdapter que hemos configurado para manejar sus datos

        //llena las listas de dispositivos
        cargarDatos();

        // define item selected listener para el spinner
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    agregarSegundoSpinner(position);
                }
            }
            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }
        });
    }


    /***
     * Este método llena los arraylist con objetos moviles, tablets o smartwatches segun corresponda.
     */
    private void cargarDatos() {
        moviles = new ArrayList<>();
        moviles.add(new Movil("Seleccione un modelo", "--"));
        moviles.add(new Movil("Samsung Galaxy S21", "Android"));
        moviles.add(new Movil("iPhone 13", "iOS"));
        moviles.add(new Movil("Xiaomi 13T", "Android"));
        moviles.add(new Movil("Realme GT2 Pro", "Android"));
        moviles.add(new Movil("Google Pixel 8", "Android"));


        tablets = new ArrayList<>();
        tablets.add(new Tablet("Seleccione un modelo", 0));
        tablets.add(new Tablet("iPad Pro", 12));
        tablets.add(new Tablet("Samsung Galaxy Tab S7", 11));
        tablets.add(new Tablet("OnePlus Pad", 10));
        tablets.add(new Tablet("Lenovo Tab P11 Pro", 10));
        tablets.add(new Tablet("Microsoft Surface Pro 9", 10));

        smartwatches = new ArrayList<>();
        smartwatches.add(new Smartwatch("Seleccione un modelo", false));
        smartwatches.add(new Smartwatch("Apple Watch", true));
        smartwatches.add(new Smartwatch("Samsung Galaxy Watch", true));
        smartwatches.add(new Smartwatch("Fitbit Versa 2", false));
        smartwatches.add(new Smartwatch("Huawei Watch GT 2", true));
    }

    /***
     * Este método crea el nuevo Spinner cuando se selecciona un elemento en el spinner anterior.
     * lo agrega al linearlayout
     * le agrega control de eventos
     *
     * @param indiceSelspRol
     */
    private void agregarSegundoSpinner (int indiceSelspRol) {
        contenido.removeAllViews();
        Spinner newSpinner = new Spinner(this);
        ArrayAdapter<? extends DispositivoInteligente> spinnerAdapter;
        switch (indiceSelspRol){
            case 1:
                spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,moviles);
                break;
            case 2:
                spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tablets);
                break;
            case 3:
                spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,smartwatches);
                break;
            default:
                return;

        }

        newSpinner.setAdapter(spinnerAdapter);
        contenido.addView(newSpinner);

        newSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionNewSpinner, long l) {
                if(positionNewSpinner != 0){
                    llenarDatos(indiceSelspRol, positionNewSpinner);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    };

    /***
     * Este metodo crear los textviewsm imageview y button y los agrega al linearlayout
     * @param indiceSelspRol
     * @param indiceSelnewSpinner
     */
    private void llenarDatos(int indiceSelspRol, int indiceSelnewSpinner) {
        contenido.removeViews(1,contenido.getChildCount()-1);
        TextView textView1 = new TextView(this);
        TextView textView2 = new TextView(this);
        ImageView imageView = new ImageView(this);
        Button buttonSalir = new Button(this);
        buttonSalir.setText("Salir");
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }

        });

        // Configurar las propiedades del botón
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,// Ancho del botón se ajusta al contenido
                LinearLayout.LayoutParams.WRAP_CONTENT // Alto del botón se ajusta al contenido
        );
        layoutParams.gravity = Gravity.CENTER; // Centrar el botón horizontalmente
        buttonSalir.setLayoutParams(layoutParams);
        buttonSalir.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        buttonSalir.setTextColor(Color.WHITE);

        textView1.setPadding(16,36,0,16);
        textView2.setPadding(16,36,0,16);

        switch (indiceSelspRol) {
            case 1:
                Movil selecttedMovil = moviles.get(indiceSelnewSpinner);
                textView1.setText("Nombre: " + selecttedMovil.getNombre());
                textView2.setText("Sistema Operativo: " + selecttedMovil.getSistemaOperativo());
                imageView.setImageResource(R.drawable.phone1);
                break;
            case 2:
                Tablet selectedTablet = tablets.get(indiceSelnewSpinner);
                textView1.setText("Nombre: " + selectedTablet.getNombre());
                textView2.setText("Tamaño de Pantalla: " + selectedTablet.getTamanoPantalla());
                imageView.setImageResource(R.drawable.phone1);
                break;
            case 3:
                Smartwatch selecttedSmartwatch = smartwatches.get(indiceSelnewSpinner);
                textView1.setText("Nombre: " + selecttedSmartwatch.getNombre());
                textView2.setText("Tiene GPS?: " + (selecttedSmartwatch.isTieneGPS() ? "Sí" : "No"));
                imageView.setImageResource(R.drawable.phone1);
                break;

            default:
                return;
        }

        contenido.addView(textView1);
        contenido.addView(textView2);
        contenido.addView(imageView);
        contenido.addView(buttonSalir);


    };
}