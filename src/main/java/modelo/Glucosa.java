package modelo;

public class Glucosa {
    private int id;
    private int idPaciente;
    private String fecha;
    private double nivelGlucosa;
    private String observaciones;

    // Constructor
    public Glucosa(int id, int idPaciente, String fecha, double nivelGlucosa, String observaciones) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.nivelGlucosa = nivelGlucosa;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getNivelGlucosa() {
        return nivelGlucosa;
    }

    public void setNivelGlucosa(double nivelGlucosa) {
        this.nivelGlucosa = nivelGlucosa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
