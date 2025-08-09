package Modelo;

public class Tablet extends  DispositivoInteligente{
    private int tamanoPantalla;

    public Tablet(String nombre, int tamanoPantalla) {
        super(nombre);
        this.tamanoPantalla = tamanoPantalla;
    }

    public int getTamanoPantalla() {
        return tamanoPantalla;
    }


}
