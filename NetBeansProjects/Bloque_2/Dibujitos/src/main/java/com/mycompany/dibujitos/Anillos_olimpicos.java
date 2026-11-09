/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dibujitos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author PC114
 */
public class Anillos_olimpicos extends JPanel {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D G2A = (Graphics2D) g;
                        
        G2A.setStroke(new BasicStroke(4));
        G2A.setColor(new Color(0, 129, 200));
        G2A.drawOval(150, 20, 80, 80);
        G2A.setColor(new Color(0, 0, 0));
        G2A.drawOval(170, 20, 80, 80);
        G2A.setColor(new Color(238, 51, 78));
        G2A.drawOval(190, 20, 80, 80);
        G2A.setColor(new Color(252, 177, 49));
        G2A.drawOval(160, -20, 80, 80);
        G2A.setColor(new Color(0, 166, 81));
        G2A.drawOval(180, -20, 80, 80);
    }
}
