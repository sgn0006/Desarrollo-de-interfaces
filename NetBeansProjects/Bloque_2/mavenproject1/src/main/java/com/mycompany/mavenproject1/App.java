/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC114
 */
public class App {

    public static void main(String[] args) {

        //El tema se instala antes de crear ninguna ventana
        FlatLightLaf.setup();
        //Las ventanas se crean en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new VentanaContacto().setVisible(true));
    }
}
