/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

public class ConfirmacionToma {
    private int id;
    private int idPaciente;
    private String mensaje;
    private Date fechaEnvio;
    private boolean enviadoPorPaciente;

    public ConfirmacionToma() {}

    public ConfirmacionToma(int id, int idPaciente, String mensaje, Date fechaEnvio, boolean enviadoPorPaciente) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.enviadoPorPaciente = enviadoPorPaciente;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public boolean isEnviadoPorPaciente() { return enviadoPorPaciente; }
    public void setEnviadoPorPaciente(boolean enviadoPorPaciente) { this.enviadoPorPaciente = enviadoPorPaciente; }
}

