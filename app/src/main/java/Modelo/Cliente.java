package Modelo;

public class Cliente {
    private String nombre;
    private int edad;
    private String genero;
    private boolean aceptaTerminos;
    private boolean esperaPromociones;

    public Cliente(String nombre, int edad, String genero, boolean aceptaTerminos, boolean esperaPromociones) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.aceptaTerminos = aceptaTerminos;
        this.esperaPromociones = esperaPromociones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isAceptaTerminos() {
        return aceptaTerminos;
    }

    public boolean isEsperaPromociones() {
        return esperaPromociones;
    }

    @Override
    public String toString() {
        return nombre + ',' +
                edad + ','+
                genero + ',' +
                aceptaTerminos +','+
                esperaPromociones;
    }
}
