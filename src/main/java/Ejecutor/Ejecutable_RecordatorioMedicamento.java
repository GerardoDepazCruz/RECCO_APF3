/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejecutor;

import vista.RecordatorioMedicamentoView;
import javax.swing.SwingUtilities;

public class Ejecutable_RecordatorioMedicamento {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecordatorioMedicamentoView().setVisible(true);
        });
    }
}

