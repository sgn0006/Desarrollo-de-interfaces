/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_bloque2;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author USUARIO
 */
public class VentanaPrincipal extends JFrame {

    /*
        Creamos los objetos que necesitamos
     */
    private boolean oscuro = false;

    //para contar las actualizaciones
    private int registros = 0;

    //Primera linea / Menu App
    //La barra del menu
    private JMenuBar barraMenu = new JMenuBar();

    //Las opciones principales del menú / Carpetas para que yo me entienda
    private JMenu menuArchivos = new JMenu("Archivo");
    private JMenu menuDatos = new JMenu("Datos");

    // Creamos las action para que puedan usar en el toolbar y en el menubar al mismo tiempo
    //Opciones del menú de Archivos
    private Action opcionAbrir = new AbstractAction("Abrir") {
        @Override
        public void actionPerformed(ActionEvent e) {
            abrirFichero();
        }
    };

    private Action opcionGuardar = new AbstractAction("Guardar") {
        @Override
        public void actionPerformed(ActionEvent e) {
            guardarFichero();
        }
    };

    private Action opcionNuevo = new AbstractAction("Nuevo") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Nuevo();
            actualizarVista();
        }
    };

    //Opciones del menú de Datos
    private Action barraGenerarInfo = new AbstractAction("Generar Informe") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Generar_informe();
        }
    };

    private Action barraAnadir = new AbstractAction("Añadir") {
        @Override
        public void actionPerformed(ActionEvent e) {
            actualizar();
        }
    };

    // No me gusta el tema claro
    public Action opcionNuevoTema = new AbstractAction("Cambiar tema") {
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarTema();
        }
    };

    //Segunda linea / Barra de tareas
    private JToolBar barraHerramientas = new JToolBar();

    private JMenuItem OpcionNuevo = new JMenu(opcionNuevo);
    private JMenuItem OpcionAbrir = new JMenu(opcionAbrir);
    private JMenuItem OpcionGuardar = new JMenu(opcionGuardar);
    private JMenuItem BarraAnadir = new JMenu(barraAnadir);
    private JMenuItem BarraGenerarInfo = new JMenu(barraGenerarInfo);


    //Parte izquierda
    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField textNombre = new JTextField("Nombre del paciente");

    private JLabel edadLabel = new JLabel("Edad:");
    private JSpinner edadSpinner = new JSpinner();

    private JLabel ingresoLabel = new JLabel("Dia de ingreso:");
    private JDateChooser ingresoChooser = new JDateChooser();

    private JLabel estanciaLabel = new JLabel("Tiempo de estancia(dias):");
    private JSpinner estanciaSpinner = new JSpinner();

    private JLabel affeccionLabel = new JLabel("Affeción:");
    private JTextField affeccionText = new JTextField("");

    private JLabel espacieLabel = new JLabel("Especie del paciente:");
    private JTextField especieText = new JTextField("Gato, Perro...");

    private JLabel notasLabel = new JLabel("Notas:");
    private JTextArea notasText = new JTextArea(5, 20);
    private JScrollPane scrollText = new JScrollPane(notasText);

    private JButton actualizarButton = new JButton("Actualizar");

    //Grafico
    private JPanel panelGrafico = new JPanel();
    private JLabel labelGrafico = new JLabel();

    //Label de abajo
    private JLabel labelEstado = new JLabel();

    public VentanaPrincipal() {
        super("Entrada de`pacientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Para poner cosas en la misma linea que el super
        JPanel panelSuper = new JPanel(new MigLayout());
                
        JPanel panel = new JPanel(new MigLayout("wrap 2,insets 20, gap 12 10", "[right] [grow, fill]", ""));

        actualizarButton.addActionListener(e -> actualizar());

        ingresoChooser.setMinSelectableDate(new Date());
        ingresoChooser.setDateFormatString("DD/MM/YYYY");

        notasText.setLineWrap(true);
        notasText.setWrapStyleWord(true);

        //Añadimos los elementos
        //Barra del menu
        setJMenuBar(barraMenu);

        //Opciones principales
        barraMenu.add(menuArchivos);
        barraMenu.add(menuDatos);

        //Menú de archivos 
        menuArchivos.add(OpcionNuevo);
        menuArchivos.add(OpcionAbrir);
        menuArchivos.add(OpcionGuardar);
        menuArchivos.add(opcionNuevoTema);

        //Menú de datos
        menuDatos.add(barraAnadir);
        menuDatos.add(barraGenerarInfo);

        //Metemos las cosas en el toolbar
        barraHerramientas.add(new JButton(opcionNuevo));
        barraHerramientas.add(new JButton(opcionAbrir));
        barraHerramientas.add(new JButton(opcionGuardar));
        //importante el palillo
        barraHerramientas.addSeparator();
        barraHerramientas.add(new JButton(barraAnadir));
        barraHerramientas.add(new JButton(barraGenerarInfo));
        
        //aplicación en sí
        panel.add(nombreLabel);
        panel.add(textNombre);

        panel.add(edadLabel);
        panel.add(edadSpinner);

        panel.add(ingresoLabel);
        panel.add(ingresoChooser);

        panel.add(espacieLabel);
        panel.add(especieText);

        panel.add(notasLabel, "top");
        panel.add(scrollText, "grow,push");

        panelSuper.add(barraHerramientas,BorderLayout.SOUTH);
        labelGrafico.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));
        add(panelSuper, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        add(labelEstado, BorderLayout.SOUTH);
        add(panel);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Este no guarda nada
    private void Nuevo() {
        textNombre.setText("");
        edadSpinner.setValue((int) 0);
        ingresoChooser.setDate(null);
        especieText.setText("");
        notasText.setText("");
        labelEstado.setText(registros + " caracteres");
    }

    //Alterna entre el tema claro y tema oscuro
    private void cambiarTema() {
        oscuro = !oscuro;
        if (oscuro) {
            FlatDarkLaf.setup();
        } else {
            FlatLightLaf.setup();
        }
        FlatLaf.updateUI(); //repinta lo que ya esta en pantalla
    }

    private void abrirFichero() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Ficheros de texto (txt)", "txt"));
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                textNombre.setText(Files.readString(selector.getSelectedFile().toPath()));
                edadSpinner.setValue(Files.readString(selector.getSelectedFile().toPath()));
                /*
                No tengo ni idea de como puedo extraer una fecha y no soy lo sificientemente listo para entender la explicación de gemini
                ingresoChooser.setDate(Files.getDate(selector.getSelectedFile().toPath()));
                */
                especieText.setText(Files.readString(selector.getSelectedFile().toPath()));
                notasText.setText(Files.readString(selector.getSelectedFile().toPath()));
                Nuevo();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se ha podido abrir el fichero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void guardarFichero() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Datos guardados en: " + archivo.getName());
            // Aquí puedes implementar la escritura real en el .txt si lo necesitas
        }
    }
    

    //Menu Datos
    //Este guarda lo que tengas puesto
    private void actualizar() {
        registros++;
        String fecha = "";
        int edad = (int) edadSpinner.getValue();
        if (ingresoChooser.getDate() != null) {
            fecha = new SimpleDateFormat("dd/mm/yyyy").format(ingresoChooser.getDate());
        }
        int estancia = (int) estanciaSpinner.getValue();

        Animal recluso = new Animal(textNombre.getText(), edad, estancia, ingresoChooser.getDate().toString(),
                affeccionText.getText(), especieText.getText(), notasText.getText());

        textNombre.setText("");
        edadSpinner.setValue((int) 0);
        ingresoChooser.setDate(null);
        especieText.setText("");
        notasText.setText("");
        labelEstado.setText(registros + " caracteres");
        
        actualizarVista();
    }

    private void actualizarVista() {
 
        String texto = "";
        for (Rendimiento r : rendimientos.getLista()) {
            texto = texto + r.getFecha().format(formatoFecha) + " · " + r.getEquipo()
                    + " · " + r.getGanados() + "G " + r.getEmpatados() + "E " + r.getPerdidos() + "P"
                    + " · " + r.getPuntos() + " pts\n";
        }
        textAreaLista.setText(texto);
 
        labelEstado.setText(String.format("%d equipos · media de %.1f puntos · máximo de %d puntos",
                rendimientos.getLista().size(), rendimientos.getMediaPuntos(), rendimientos.getMaximo()));
 
        // Sin repaint() el dibujo no cambiaría hasta redimensionar la ventana
        panelRendimiento.repaint();
    }
    
    private void Generar_informe() {
        JOptionPane.showMessageDialog(
                this,
                "No está terminado",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
