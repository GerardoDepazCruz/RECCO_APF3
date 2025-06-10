/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Paciente {
    private String nombre;
    private String dni;
    private int edad;
    private String tipoDiabetes;
    private String contactoEmergencia;

    public Paciente(String nombre, String dni, int edad, String tipoDiabetes, String contactoEmergencia) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.tipoDiabetes = tipoDiabetes;
        this.contactoEmergencia = contactoEmergencia;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public int getEdad() { return edad; }
    public String getTipoDiabetes() { return tipoDiabetes; }
    public String getContactoEmergencia() { return contactoEmergencia; }
}


