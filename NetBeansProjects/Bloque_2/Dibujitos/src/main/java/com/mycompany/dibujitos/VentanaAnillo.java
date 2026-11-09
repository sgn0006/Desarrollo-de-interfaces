/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dibujitos;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author PC114
 */
public class VentanaAnillo extends JFrame{
    
    private Anillos_olimpicos Anillos_olimpicos = new Anillos_olimpicos();
    
    public VentanaAnillo() {
        super("Figuras basicas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(Anillos_olimpicos);
        
        
        setSize(300,320);
        setLocationRelativeTo(null);
    }
}
