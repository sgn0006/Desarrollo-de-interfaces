/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dibujitos;

import javax.swing.JFrame;

/**
 *
 * @author PC114
 */
public class VentanaFiguras extends JFrame{
    private PanelFiguras panelFiguras = new PanelFiguras();
    
    public VentanaFiguras() {
        super("Figuras basicas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        add(panelFiguras);
        
        
        setSize(300,320);
        setLocationRelativeTo(null);
    }
}
