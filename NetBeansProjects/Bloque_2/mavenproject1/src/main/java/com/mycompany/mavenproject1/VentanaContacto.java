/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Panel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author PC114
 */




public class VentanaContacto extends JFrame{
        
    private JLabel titulo = new JLabel("Escribe a secretaría");
        
        private JLabel titulo = new JLabel("Nombre");
        
        private final JTextField JTextField_nombre = new JTextField(20);
        
        private JLabel correo = new JLabel("Correo");
        private final JTextField JTextField_correo = new JTextField(20);
        
        private JLabel asunto = new JLabel("Asunto");
        private final JTextField JTextField_asunto = new JTextField(20);
        
        private final JComboBox<String> asunto = new JComboBox<>(
        new String[] {"Información sobre ciclos", "Matricula", "Beca"});
        private final JTextArea mensaje = new JTextArea(5,20);
        private final JCheckBox privacidad = new JCheckBox('He leido y aceptado los terminos de privacidad');
        
        JPanel panel = new JPanel(new MigLayout("debug, warp 2, inserts 20, gap 10", "[right][grow,fill]", ""));
        
        titulo.putClientPropety(FlatClientProperties.STYLE_CLASS, "h2");
        JTextField_correo.putClientPropety(FlatClientProperties.PLACEHOLDER_TEXT, "nombre@correo.es");
        JTextField_mensaje.setLineWarp(true);
        JTextField_mensaje.setLineWarpStyleWord(true);
        
        Panel.add(Label);
        
        add(panel);
        pack();
}
