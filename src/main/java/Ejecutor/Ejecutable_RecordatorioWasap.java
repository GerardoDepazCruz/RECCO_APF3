/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejecutor;

import vista.RecepcionRecordatorioView;
import javax.swing.SwingUtilities;

public class Ejecutable_RecordatorioWasap {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RecepcionRecordatorioView().setVisible(true);
        });
    }
}
