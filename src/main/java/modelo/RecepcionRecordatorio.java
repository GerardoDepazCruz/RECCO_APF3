/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

public class RecepcionRecordatorio {
    private int id;
    private int idPaciente;
    private Date fechaEnvio;
    private boolean recibido;

    public RecepcionRecordatorio() {}

    public RecepcionRecordatorio(int id, int idPaciente, Date fechaEnvio, boolean recibido) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.fechaEnvio = fechaEnvio;
        this.recibido = recibido;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public Date getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Date fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public boolean isRecibido() { return recibido; }
    public void setRecibido(boolean recibido) { this.recibido = recibido; }
}

