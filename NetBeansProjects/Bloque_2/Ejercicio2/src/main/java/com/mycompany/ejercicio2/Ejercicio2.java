/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC114
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        FlatLightLaf.setup();
        
        SwingUtilities.invokeLater(() -> new VentanaContacto2().setVisible(true)); 
    }
}
