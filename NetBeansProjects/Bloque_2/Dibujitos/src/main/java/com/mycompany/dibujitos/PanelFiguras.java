/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dibujitos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author PC114
 */
class PanelFiguras extends JPanel{
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
    
        g2.setColor(new Color(0,53,148));
        g2.fillRect(20, 20, 120, 70);
        g2.setColor(Color.ORANGE);
        g2.fillOval(170, 20, 80, 80);
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(20, 130, 250, 130);
        g2.drawRect(20, 150, 120, 50);
        g2.fillArc(170, 150, 80, 80, 0, 270);
        g2.setFont(new Font("SansSerif", Font.BOLD,18));
        g2.drawString("Hola, Graphics2D", 20, 260);
        
        
    }
}
