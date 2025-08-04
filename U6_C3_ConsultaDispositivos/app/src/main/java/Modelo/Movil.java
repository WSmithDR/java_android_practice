package Modelo;

public class Movil extends DispositivoInteligente{
    private String sistemaOperativo;

    public Movil(String nombre, String sistemaOperativo) {
        super(nombre);
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }
}
