/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author PC114
 */
public class VentanaContacto2 extends JFrame {

    private JLabel NombreApellidos = new JLabel("Nombre y Apellidos");
    private JTextField TextNobreApellidos = new JTextField(20);

    private JLabel Email = new JLabel("Email");
    private JTextField TextEmail = new JTextField(20);

    private JLabel Nacimiento = new JLabel("Fecha de Nacimiento");
    private JDateChooser DateNacimiento = new JDateChooser();

    private JLabel Genero = new JLabel("Fecha de Nacimiento");
    private JRadioButton MasculinoButton = new JRadioButton("Masculino");
    private JRadioButton FemeninoButton = new JRadioButton("Femenino");
    private ButtonGroup buttonGroupGenero = new ButtonGroup();

    private JLabel Titulacion = new JLabel("Titulación");
    private JComboBox<String> TextTitulacion = new JComboBox<>(new String[]{"FP Grado Medio", "Bachillerato", "Prueba de acceso"});

    private JLabel Observaciones = new JLabel("Observaciones");
    private JTextArea TextObserva = new JTextArea(5, 20);
    private JScrollPane scrollObserva = new JScrollPane(TextObserva);

    private JButton Guardar = new JButton("Guardar");
    private JButton Imprimir = new JButton("Imprimir");

    public VentanaContacto2() {
        super("Formulario de matriculación del alumnado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new MigLayout("wrap 2, insets 20, gap 12 10", "[right] [grow, fill]", ""));

        buttonGroupGenero.add(MasculinoButton);
        buttonGroupGenero.add(FemeninoButton);
        Guardar.addActionListener(e -> guardar());

        DateNacimiento.setDateFormatString("dd/mm/yyyy");
        DateNacimiento.setMaxSelectableDate(new Date());

        TextObserva.setLineWrap(true);
        TextObserva.setWrapStyleWord(true);

        panel.add(NombreApellidos);
        panel.add(TextNobreApellidos);

        panel.add(Email);
        panel.add(TextEmail);

        panel.add(Nacimiento);
        panel.add(DateNacimiento, "growx 0");

        panel.add(Genero);
        panel.add(MasculinoButton, "split 2, growx 0");
        panel.add(FemeninoButton, "growx 0, gapright push");

        panel.add(Titulacion);
        panel.add(TextTitulacion);

        panel.add(Observaciones, "top");
        panel.add(TextObserva, "growy, pushy");
        panel.add(scrollObserva);

        panel.add(Guardar, "skip, slpip 2, growx 0, gapleft push, sg botones, gaptop10");
        panel.add(Imprimir, "growx 0, sg botones , gaptop 10");

        add(panel);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private void guardar() {
        String fecha = "";
        if (DateNacimiento.getDate() != null) {
            fecha = new SimpleDateFormat("dd/mm/yyyy").format(DateNacimiento.getDate());
        }        String genero = "";
        if (MasculinoButton.isSelected()) {
            genero = "masculino";
        } else if (FemeninoButton.isSelected()) {
            genero = "femenino";
        }

        Persona persona = new Persona(TextNobreApellidos.getText(), TextEmail.getText(), fecha, 
                TextTitulacion.getSelectedItem().toString(), TextObserva.getText(), genero);
    }

}
