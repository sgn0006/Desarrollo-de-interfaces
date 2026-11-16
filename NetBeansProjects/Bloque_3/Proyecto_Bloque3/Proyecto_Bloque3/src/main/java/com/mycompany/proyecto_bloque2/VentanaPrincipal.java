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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JREmptyDataSource;

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
    private JMenuItem OpcionNuevo = new JMenuItem(opcionNuevo);
    private JMenuItem OpcionAbrir = new JMenuItem(opcionAbrir);
    private JMenuItem OpcionGuardar = new JMenuItem(opcionGuardar);
    private JMenuItem BarraAnadir = new JMenuItem(barraAnadir);
    private JMenuItem BarraGenerarInfo = new JMenuItem(barraGenerarInfo);

    //Parte izquierda 
    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField textNombre = new JTextField("");

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

    public VentanaPrincipal() {
        super("Entrada de pacientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Creo los paneles que servirán para estructurar la aplicación
        JPanel panelNorte = new JPanel(new MigLayout());
        JPanel panelOeste = new JPanel(new MigLayout("wrap 2,insets 20, gap 12 10", "[right] [grow, fill]", ""));
        JPanel panelSur = new JPanel(new MigLayout("insets 4 12 4 8", "[] push []", ""));
        // La aplicación comienza sin registros para comprobar el caso vacío.

        actualizarButton.addActionListener(e -> actualizar());
        edadSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 200, 1));
        estanciaSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 36500, 1));

        // Se permiten fechas de ingreso anteriores a hoy.
        ingresoChooser.setDateFormatString("dd/MM/yyyy");

        notasText.setLineWrap(true);
        notasText.setWrapStyleWord(true);
        scrollText.setMinimumSize(new Dimension(200, 100));

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
        logs.setEditable(false);

        //panel oeste
        panelOeste.add(nombreLabel);
        panelOeste.add(textNombre);

        panelOeste.add(edadLabel);
        panelOeste.add(edadSpinner);

        panelOeste.add(ingresoLabel);
        panelOeste.add(ingresoChooser);

        panelOeste.add(espacieLabel);
        panelOeste.add(especieText);

        panelOeste.add(estanciaLabel);
        panelOeste.add(estanciaSpinner);

        panelOeste.add(affeccionLabel);
        panelOeste.add(affeccionText);

        panelOeste.add(notasLabel, "top");
        panelSur.add(labelEstado);
        notasText.setLineWrap(true);
        notasText.setWrapStyleWord(true);
        panelOeste.add(scrollText, "grow,push");

        panelOeste.add(vacio1);
        panelOeste.add(actualizarButton);
        panelOeste.add(vacio2);
        panelOeste.add(scrollLogs, "grow, push");

        panelNorte.add(barraHerramientas);

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
        datos.limpiar();
        limpiarFormulario();

        logs.setText("");

        actualizacion = 0;
        labelEstado.setText(actualizacion + " actualizaciones");

        panelGrafico.repaint();
    }

    //Este guarda
    private void limpiarFormulario() {
        textNombre.setText("");
        edadSpinner.setValue(0);
        ingresoChooser.setDate(null);
        estanciaSpinner.setValue(0);
        affeccionText.setText("");
        especieText.setSelectedIndex(0);
        notasText.setText("");
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

    // Guarda y recupera la lista completa mediante un archivo de propiedades UTF-8.
    private void abrirFichero() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Datos de mascotas (*.properties)", "properties"));
        if (selector.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        try {
            java.util.Properties propiedades = new java.util.Properties();
            try (java.io.Reader lector = java.nio.file.Files.newBufferedReader(
                    selector.getSelectedFile().toPath(), java.nio.charset.StandardCharsets.UTF_8)) {
                propiedades.load(lector);
            }
            int total = Integer.parseInt(propiedades.getProperty("total", "0"));
            Datos nuevos = new Datos();
            for (int i = 0; i < total; i++) {
                String p = "animal." + i + ".";
                nuevos.add(new Animal(
                        propiedades.getProperty(p + "nombre", ""),
                        Integer.parseInt(propiedades.getProperty(p + "edad", "0")),
                        Integer.parseInt(propiedades.getProperty(p + "estancia", "0")),
                        propiedades.getProperty(p + "ingreso", ""),
                        propiedades.getProperty(p + "afeccion", ""),
                        propiedades.getProperty(p + "especie", ""),
                        propiedades.getProperty(p + "notas", "")));
            }
            datos.limpiar();
            for (Animal animal : nuevos.getLista()) {
                datos.add(animal);
            }
            limpiarFormulario();
            actualizacion = datos.getTotal();
            labelEstado.setText(actualizacion + " registros");
            logs.append("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    + "] Lista cargada: " + total + " mascotas\n");
            panelGrafico.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se ha podido abrir el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarFichero() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Datos de mascotas (*.properties)", "properties"));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".properties")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".properties");
        }
        if (archivo.exists() && JOptionPane.showConfirmDialog(this,
                "¿Sobrescribir el archivo existente?", "Confirmar", JOptionPane.YES_NO_OPTION)
                != JOptionPane.YES_OPTION) {
            return;
        }
        java.util.Properties propiedades = new java.util.Properties();
        propiedades.setProperty("total", String.valueOf(datos.getTotal()));
        for (int i = 0; i < datos.getTotal(); i++) {
            Animal animal = datos.getLista().get(i);
            String p = "animal." + i + ".";
            propiedades.setProperty(p + "nombre", animal.getNombre());
            propiedades.setProperty(p + "edad", String.valueOf(animal.getEdad()));
            propiedades.setProperty(p + "estancia", String.valueOf(animal.getEstancia()));
            propiedades.setProperty(p + "ingreso", animal.getIngreso());
            propiedades.setProperty(p + "afeccion", animal.getAffeccion());
            propiedades.setProperty(p + "especie", animal.getEspecie());
            propiedades.setProperty(p + "notas", animal.getNotas());
        }
        try (java.io.Writer escritor = java.nio.file.Files.newBufferedWriter(
                archivo.toPath(), java.nio.charset.StandardCharsets.UTF_8)) {
            propiedades.store(escritor, "Mascotas registradas");
            JOptionPane.showMessageDialog(this, "Datos guardados en:\n" + archivo.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Menu Datos
    private void actualizar() {

        String nombre = textNombre.getText().trim();

        // Comprobar que se ha escrito un nombre
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Introduce el nombre de la mascota",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        actualizacion++;

        String fecha = "";

        if (ingresoChooser.getDate() != null) {
            fecha = new SimpleDateFormat("dd/MM/yyyy")
                    .format(ingresoChooser.getDate());
        }

        int edad = (int) edadSpinner.getValue();
        int estancia = (int) estanciaSpinner.getValue();

        Animal animal = new Animal(
                nombre,
                edad,
                estancia,
                fecha,
                affeccionText.getText(),
                especieText.getSelectedItem().toString(),
                notasText.getText()
        );

        // Guardar el animal
        datos.add(animal);

        // Actualizar el gráfico
        panelGrafico.repaint();

        // Escribir el registro
        escribirLog(nombre);

        // Actualizar el contador
        labelEstado.setText(actualizacion + " actualizaciones");

        // Limpiar solamente el formulario
        limpiarFormulario();
    }

    private void escribirLog(String nombre) {
        String fechaHora = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        logs.append("[" + fechaHora + "] Se ha añadido la mascota: " + nombre + "\n");
    }

    private void Generar_informe() {
        generarInforme();
    }

    //Creamos un nuevo metodo para hacer el informe, no lo sustituimos por que me da pereza arreglar los posibles errores que surgan
    //Genera un PDF con los datos actuales mediante JasperReports.
    private void generarInforme() {
        try {
            // Cargar el informe desde resources
            java.io.InputStream archivo = getClass()
                    .getResourceAsStream("/informe_mascotas.jrxml");

            if (archivo == null) {
                throw new java.io.IOException(
                        "No se encuentra informe_mascotas.jrxml");
            }

            // Compilar el informe
            net.sf.jasperreports.engine.JasperReport informe;

            try (archivo) {
                informe = net.sf.jasperreports.engine.JasperCompileManager.compileReport(archivo);
            }

            // Parámetros del informe
            java.util.Map<String, Object> parametros
                    = new java.util.HashMap<>();

            parametros.put("titulo", "Informe de mascotas");
            parametros.put("numeroMascotas", datos.getTotal());
            parametros.put("estanciaMedia", datos.getEstanciaMedia());

            String fecha = java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy 'a las' HH:mm"));

            parametros.put("fechaGeneracion",
                    "Informe generado el " + fecha);

            // Rellenar el informe
            net.sf.jasperreports.engine.JasperPrint resultado
                    = net.sf.jasperreports.engine.JasperFillManager.fillReport(
                            informe,
                            parametros,
                            new net.sf.jasperreports.engine.JREmptyDataSource()
                    );

            // Guardar en la carpeta del usuario
            java.io.File destino = new java.io.File(
                    System.getProperty("user.home"),
                    "informe_mascotas.pdf"
            );

            net.sf.jasperreports.engine.JasperExportManager
                    .exportReportToPdfFile(
                            resultado,
                            destino.getAbsolutePath()
                    );

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Informe generado correctamente en:\n"
                    + destino.getAbsolutePath()
            );

        } catch (Exception e) {

            System.err.println("ERROR AL GENERAR EL INFORME");

            Throwable causa = e;

            while (causa != null) {
                System.err.println(
                        causa.getClass().getName()
                        + ": " + causa.getMessage()
                );

                causa = causa.getCause();
            }

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar el informe:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
