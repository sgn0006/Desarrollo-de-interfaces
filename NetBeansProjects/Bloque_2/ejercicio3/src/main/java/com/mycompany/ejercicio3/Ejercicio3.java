package com.mycompany.ejercicio3;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;

public class Ejercicio3 {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new VentanaNotas().setVisible(true));
    }
}
