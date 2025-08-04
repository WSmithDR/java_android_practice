package Modelo;

public class Smartwatch extends DispositivoInteligente{
    private boolean tieneGPS;

    public Smartwatch(String nombre, boolean tieneGPS) {
        super(nombre);
        this.tieneGPS = tieneGPS;
    }

    public boolean isTieneGPS() {
        return tieneGPS;
    }
}
