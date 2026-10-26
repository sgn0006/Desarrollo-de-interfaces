/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author PC114
 */
public class VentanaContacto extends JFrame {

    private JLabel titulo = new JLabel("Escribe a secretaria");

    private JLabel labelNombre = new JLabel("Nombre");
    private JTextField nombre = new JTextField(20);

    private JLabel labelCorreo = new JLabel("Correo");
    private JTextField correo = new JTextField(20);

    private JLabel labelAsunto = new JLabel("Asunto");
    private JComboBox<String> asunto = new JComboBox<>(new String[]{"Informacion sobre ciclos", "Matricula", "Beca"});

    private JLabel labelMensaje = new JLabel("Mensaje");
    private JTextArea mensaje = new JTextArea(5, 20);

    private JLabel labelPrivacidad = new JLabel("Privacidad");
    private JCheckBox privacidad = new JCheckBox("He leido y aceptado la politica de privacidad");

    private JButton limpiar = new JButton("Limpiar");
    private JButton enviar = new JButton("Enviar");

    public VentanaContacto() {
        super("Contacto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new MigLayout("wrap 2, insets 20, gap 10", "[right] [grow, fill]", ""));

        titulo.putClientProperty(FlatClientProperties.STYLE_CLASS, "h2");
        correo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "nombre@example.com");
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);

        panel.add(titulo, "span 2, align left, gapbottom 10");
        panel.add(labelNombre);
        panel.add(nombre);
        panel.add(labelCorreo);
        panel.add(correo);
        panel.add(labelAsunto);
        panel.add(asunto);
        panel.add(labelMensaje, "top");
        panel.add(mensaje);
        panel.add(privacidad, "skip, growy, pushy");
        panel.add(limpiar, "skip, split 2");
        panel.add(enviar);

        add(panel);

        pack();

        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        limpiar.addActionListener(e -> limpiar());
        enviar.addActionListener(e -> enviar());
    }

    private void limpiar() {
        nombre.setText("");
        correo.setText("");
        asunto.setSelectedIndex(0);
        mensaje.setText("");
        privacidad.setSelected(false);
    }

    private void enviar() {
        if (!privacidad.isSelected()) {
            JOptionPane.showMessageDialog(this, "Debes de aceptar la politica de privacidad");
            return;
        }
        JOptionPane.showMessageDialog(this, "Mensaje enviado a secretaria");
        limpiar();
    }
}
