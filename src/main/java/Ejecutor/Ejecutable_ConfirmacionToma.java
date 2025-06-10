/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejecutor;

import vista.ConfirmacionTomaView;
import javax.swing.SwingUtilities;

public class Ejecutable_ConfirmacionToma {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConfirmacionTomaView().setVisible(true);
        });
    }
}

