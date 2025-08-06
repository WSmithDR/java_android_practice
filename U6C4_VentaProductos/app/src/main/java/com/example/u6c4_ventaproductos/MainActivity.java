package com.example.u6c4_ventaproductos;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Modelo.Venta;
public class MainActivity extends AppCompatActivity {
    private EditText editTextDate;
    private LinearLayout linearLayoutVentas;
    private List<Venta> ventasList;

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

        //Enalzar las variables de instancia con los views de la activity.
        editTextDate = findViewById(R.id.editTextDate);
        linearLayoutVentas = findViewById(R.id.linearLayoutVentas);

        // Inicializar la lista de ventas
        ventasList = generarVentas();

        // Configurar el DatePicker
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePick = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anioSel, int mesSel, int daySel) {
                        String fechaSeleccionada = anioSel + "-" + (mesSel + 1) + "-" + daySel;
                        editTextDate.setText(fechaSeleccionada);
                        mostrarVentasPorFecha(fechaSeleccionada);
                    }
                }, year, month, day);
                datePick.show();
            }

            ;

        });
    }

    private void mostrarVentasPorFecha(String fecha) {
        linearLayoutVentas.removeAllViews();
        for (Venta venta : ventasList) {
            if (venta.getFecha().equals(fecha)) {
                LinearLayout itemLayout = new LinearLayout(this);
                itemLayout.setOrientation(LinearLayout.VERTICAL);
                itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                itemLayout.setPadding(8, 8, 8, 8);

                TextView tvProducto = new TextView(this);
                tvProducto.setText("Producto: " + venta.getProducto());
                tvProducto.setTextSize(16);
                tvProducto.setTypeface(tvProducto.getTypeface(), Typeface.BOLD);

                TextView tvCantidad = new TextView(this);
                tvCantidad.setText("Cantidad: " + venta.getCantidad());
                tvCantidad.setTextSize(14);

                TextView tvFecha = new TextView(this);
                tvFecha.setText("Fecha: " + venta.getFecha());
                tvFecha.setTextSize(14);

                TextView tvPrecio = new TextView(this);
                tvPrecio.setText("Total: " + venta.getTotal());
                tvPrecio.setTextSize(14);

                itemLayout.addView(tvProducto);
                itemLayout.addView(tvCantidad);
                itemLayout.addView(tvFecha);
                itemLayout.addView(tvPrecio);

                linearLayoutVentas.addView(itemLayout);

            }


        }
    }

    private List<Venta> generarVentas() {
        ArrayList<Venta> ventas = new ArrayList<>();

        ventas.add(new Venta("Pan", 3, "2025-8-6", 4.5));
        ventas.add(new Venta("Leche", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Huevos", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Queso", 1, "2025-8-6", 3.0));
        ventas.add(new Venta("Mantequilla", 4, "2025-8-6", 6.0));
        ventas.add(new Venta("Jugo de naranja", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Manzanas", 5, "2025-8-6", 7.5));
        ventas.add(new Venta("Plátanos", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Pollo", 3, "2025-8-6", 9.0));
        ventas.add(new Venta("Carne de res", 2, "2025-8-6", 6.0));
        ventas.add(new Venta("Pescado", 1, "2025-8-6", 4.5));
        ventas.add(new Venta("Arroz", 3, "2025-8-6", 4.5));
        ventas.add(new Venta("Frijoles", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Pasta", 4, "2025-8-6", 6.0));
        ventas.add(new Venta("Tomates", 5, "2025-8-6", 7.5));
        ventas.add(new Venta("Cebollas", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Ajo", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Papas", 3, "2025-8-6", 4.5));
        ventas.add(new Venta("Zanahorias", 4, "2025-8-6", 6.0));
        ventas.add(new Venta("Lechuga", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Brócoli", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Pepinos", 5, "2025-8-6", 7.5));
        ventas.add(new Venta("Pimientos", 3, "2025-8-6", 4.5));
        ventas.add(new Venta("Yogur", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Helado", 4, "2025-8-6", 6.0));
        ventas.add(new Venta("Galletas", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Cereal", 5, "2025-8-6", 7.5));
        ventas.add(new Venta("Café", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Té", 3, "2025-8-6", 4.5));
        ventas.add(new Venta("Azúcar", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Sal", 4, "2025-8-6", 6.0));
        ventas.add(new Venta("Pimienta", 5, "2025-8-6", 7.5));
        ventas.add(new Venta("Aceite de oliva", 1, "2025-8-6", 1.5));
        ventas.add(new Venta("Vinagre", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Salsa de tomate", 4, "2025-8-6", 6.0));
        ventas.add(new Venta("Mostaza", 3, "2025-8-6", 4.5));
        ventas.add(new Venta("Mayonesa", 2, "2025-8-6", 3.0));
        ventas.add(new Venta("Pan de molde", 5, "2025-8-6", 7.5));
        ventas.add(new Venta("Tortillas", 1, "2025-8-6", 1.5));


        return ventas;
    }

    ;
};