package Modelo;

public class Venta {
    private String producto;
    private int cantidad;
    private String fecha; // Formato "yyyy-MM-dd"
    private double total;

    public Venta(String producto, int cantidad, String fecha, double total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }
}
