/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class RecordatorioMedicamento {
    private int id;
    private int idPaciente;
    private String nombreMedicina;
    private String frecuencia; // "Diaria", "Semanal", etc.

    public RecordatorioMedicamento() {}

    public RecordatorioMedicamento(int id, int idPaciente, String nombreMedicina, String frecuencia) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.nombreMedicina = nombreMedicina;
        this.frecuencia = frecuencia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public String getNombreMedicina() { return nombreMedicina; }
    public void setNombreMedicina(String nombreMedicina) { this.nombreMedicina = nombreMedicina; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
}

