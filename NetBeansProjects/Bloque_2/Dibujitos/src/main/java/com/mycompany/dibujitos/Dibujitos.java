/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dibujitos;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC114
 */
public class Dibujitos extends JFrame{

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> new VentanaFiguras().setVisible(true));
    }
}
