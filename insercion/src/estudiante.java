public class estudiante {
    private String cedula;
    private String nombre;
    private double b1;
    private double b2;

    public estudiante(String cedula, String nombre, double b1, double b2) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.b1 = b1;
        this.b2 = b2;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public double getB1() {
        return b1;
    }

    public double getB2() {
        return b2;
    }
}

