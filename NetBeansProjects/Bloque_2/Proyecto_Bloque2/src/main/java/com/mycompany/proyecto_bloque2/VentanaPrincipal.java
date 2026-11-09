/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_bloque2;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.KeyStroke;
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
    private int actualizacion = 0;

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
            opcionAbrir.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control 4"));
        }
    };

    private Action opcionGuardar = new AbstractAction("Guardar") {
        @Override
        public void actionPerformed(ActionEvent e) {
            guardarFichero();
            opcionGuardar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control 4"));
        }
    };

    private Action opcionNuevo = new AbstractAction("Nuevo") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Nuevo();
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
            opcionNuevoTema.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("control 4"));
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
    private JMenuItem OpcionNuevo = new JMenuItem(opcionNuevo);
    private JMenuItem OpcionAbrir = new JMenuItem(opcionAbrir);
    private JMenuItem OpcionGuardar = new JMenuItem(opcionGuardar);
    private JMenuItem BarraAnadir = new JMenuItem(barraAnadir);
    private JMenuItem BarraGenerarInfo = new JMenuItem(barraGenerarInfo);

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
    private JComboBox<String> especieText = new JComboBox<>(new String[]{"Kiwi", "Cóndor", "Pulpo", "Carnotaurus", "Hipocampo", "Doble Elefante Telepata de guerra"});

    private JLabel notasLabel = new JLabel("Notas:");
    private JTextArea notasText = new JTextArea(5, 20);
    private JScrollPane scrollText = new JScrollPane(notasText);

    private JButton actualizarButton = new JButton("Actualizar");
    private JTextArea logs = new JTextArea("");
    private JScrollPane scrollLogs = new JScrollPane(logs);

    private JLabel vacio1 = new JLabel("");
    private JLabel vacio2 = new JLabel("");

    //Grafico
    // Creamos el objeto que almacena los datos
    private Datos datos = new Datos();

    // Creamos el panel que dibuja la gráfica
    private PanelGrafico panelGrafico = new PanelGrafico(datos);

    //Label de abajo
    private JLabel labelEstado = new JLabel(actualizacion + " actualizaciones");

    //Para el log
    private String nombre = logs.getText();

    public VentanaPrincipal() {
        super("Entrada de pacientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Creo los paneles que servirán para estructurar la aplicación
        JPanel panelNorte = new JPanel(new MigLayout());
        JPanel panelOeste = new JPanel(new MigLayout("wrap 2,insets 20, gap 12 10", "[right] [grow, fill]", ""));
        JPanel panelSur = new JPanel(new MigLayout("insets 4 12 4 8", "[] push []", ""));

        //Datos de ejemplo para poder probar la aplicación (orientativos)
        LocalDate fechaHoy = LocalDate.now();
        String fecha = fechaHoy.toString();
        datos.add(new Animal("Fran",3,7,fecha,"desconocida","Kiwi",""));
        datos.add(new Animal("Alberto",1000000000,8,fecha,"desconocida","Carnotaurus",""));
        datos.add(new Animal("Pepe",150,15,fecha,"desconocida","Doble Elefante Telepata de guerra",""));
        datos.add(new Animal("Juan",1900,23,fecha,"desconocida","Hipocampo",""));
        datos.add(new Animal("Pedro",2,42,fecha,"desconocida","Pulpo",""));
        datos.add(new Animal("Francisco",1,3,fecha,"desconocida","Cóndor",""));
        
        actualizarButton.addActionListener(e -> actualizar());

        ingresoChooser.setMinSelectableDate(new Date());
        ingresoChooser.setDateFormatString("dd/MM/yyyy");

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

        //Atajos de teclado
        OpcionNuevo.setAccelerator(KeyStroke.getKeyStroke("control 1"));
        OpcionAbrir.setAccelerator(KeyStroke.getKeyStroke("control 2"));
        OpcionGuardar.setAccelerator(KeyStroke.getKeyStroke("control 3"));

        //Metemos las cosas en el toolbar
        barraHerramientas.add(new JButton(opcionNuevo));
        barraHerramientas.add(new JButton(opcionAbrir));
        barraHerramientas.add(new JButton(opcionGuardar));
        //importante el palillo
        barraHerramientas.addSeparator();
        barraHerramientas.add(new JButton(barraAnadir));
        barraHerramientas.add(new JButton(barraGenerarInfo));

        //panel oeste
        panelOeste.add(nombreLabel);
        panelOeste.add(textNombre);

        panelOeste.add(edadLabel);
        panelOeste.add(edadSpinner);

        panelOeste.add(ingresoLabel);
        panelOeste.add(ingresoChooser);

        panelOeste.add(espacieLabel);
        panelOeste.add(especieText);
        panelOeste.add(notasLabel, "top");
        panelSur.add(labelEstado);
        notasText.setLineWrap(true);
        notasText.setWrapStyleWord(true);
        panelOeste.add(scrollText, "grow,push");

        panelOeste.add(vacio1);
        panelOeste.add(actualizarButton);
        panelOeste.add(vacio2);
        panelOeste.add(scrollLogs, "grow, push");

        panelNorte.add(barraHerramientas, BorderLayout.SOUTH);

        panelGrafico.setBorder(BorderFactory.createEmptyBorder(4, 12, 4, 12));

        add(panelNorte, BorderLayout.NORTH);
        add(panelOeste, BorderLayout.WEST);
        add(panelGrafico, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    //Este no guarda nada
    private void Nuevo() {
        actualizacion++;
        textNombre.setText("");
        edadSpinner.setValue((int) 0);
        ingresoChooser.setDate(null);
        especieText.setSelectedItem("");
        notasText.setText("");
        labelEstado.setText(actualizacion + " actualizaciones");
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
        actualizacion++;
        String fecha = "";
        int edad = (int) edadSpinner.getValue();
        if (ingresoChooser.getDate() != null) {
            fecha = new SimpleDateFormat("dd/MM/yyyy").format(ingresoChooser.getDate());
        }
        int estancia = (int) estanciaSpinner.getValue();

        Animal animal = new Animal(textNombre.getText(), edad, estancia, fecha,
                affeccionText.getText(), especieText.getSelectedItem().toString(), notasText.getText());

        datos.add(animal);
        panelGrafico.repaint();

        escribirLog(textNombre.getText());

        textNombre.setText("");
        edadSpinner.setValue((int) 0);
        ingresoChooser.setDate(null);
        especieText.setSelectedItem("Kiwi");
        notasText.setText("");
        labelEstado.setText(actualizacion + " actualizaciones");

    }

    private void escribirLog(String nombre) {
        String fechaHora = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        logs.append("[" + fechaHora + "] Se ha añadido la mascota: " + nombre + "\n");
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
