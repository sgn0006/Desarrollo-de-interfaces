package com.mycompany.ejercicio3;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.miginfocom.swing.MigLayout;

public class VentanaNotas extends JFrame{
    
    private boolean oscuro = false;
    
    public Action actionNuevo = new AbstractAction("Nuevo") {
        @Override
        public void actionPerformed(ActionEvent e) {
            areaTexto.setText("");
            actualizarEstado();
        }
    };
    
    public Action actionNuevoTema = new AbstractAction("Cambiar tema") {
        @Override
        public void actionPerformed(ActionEvent e) {
            cambiarTema();
        }
    };
    
    private JMenuBar menuBar = new JMenuBar();
    
    private JMenu menuArchivo = new JMenu("Archivo");
    private JMenuItem menuItemNuevo = new JMenuItem(actionNuevo);
    private JMenuItem menuItemAbrir = new JMenuItem("Abrir");
    private JMenuItem menuItemSalir = new JMenuItem("Salir");
    
    private JMenu menuVer = new JMenu("Ver");
    private JMenuItem menuItemCambiarTema = new JMenuItem(actionNuevoTema);
    
    private JToolBar toolBar = new JToolBar();
    private JButton buttonNuevoBarra = new JButton(actionNuevo);
    private JButton buttonCambiarTema = new JButton(actionNuevoTema);
    
    private JTextArea areaTexto = new JTextArea();
    private JScrollPane scroll = new JScrollPane(areaTexto);
    
    private JLabel labelEstado = new JLabel("0 caracteres");
    private JButton botonNuevoEstado = new JButton(actionNuevo);
    
    public VentanaNotas(){
        super("Bloc de notas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panelEstado = new JPanel(new MigLayout("insets 4 12 4 8", "[] push []", ""));
        
        //Atajos de teclado
        menuItemNuevo.setAccelerator(KeyStroke.getKeyStroke("control N"));
        menuItemAbrir.setAccelerator(KeyStroke.getKeyStroke("control O"));
        menuItemSalir.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        menuItemCambiarTema.setAccelerator(KeyStroke.getKeyStroke("control T"));
        
        areaTexto.setLineWrap(true); //si una linea no cabe pasa a la siguiente (salto de linea sino cabe), se aplica al textArea
        areaTexto.setWrapStyleWord(true); //cuando haga el salto de linea, no separe la palabra, que la palabra se mantenga unida
        
        //configuracion de la barra de herramientas
        buttonNuevoBarra.setAction(actionNuevo);
        buttonCambiarTema.setAction(actionNuevoTema);
        
        toolBar.add(buttonNuevoBarra);
        toolBar.add(buttonCambiarTema);
        
        //añadimos los componentes visuales a el panel
        
        menuArchivo.add(menuItemNuevo);
        menuArchivo.add(menuItemAbrir);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);
        
        menuVer.add(menuItemCambiarTema);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuVer);
        
        toolBar.add(buttonNuevoBarra);
        toolBar.add(buttonCambiarTema);
        
        panelEstado.add(labelEstado);
        panelEstado.add(botonNuevoEstado);
        
        //añadimos a el jframe estas distribuciones
        setJMenuBar(menuBar); //ahora si se va a mostrar porque tiene elementos
        add(toolBar, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.SOUTH);
        
        // eventos
        menuItemAbrir.addActionListener(e -> abrir()); 
//Abrir llama al metodo abrir
        menuItemSalir.addActionListener(e -> dispose()); 
// Salir llama a dispose() este es un metodo de java, cierra la ventana y libera recursos
        
        //Cada vez que se suelta una tecla se recuentan los caracteres
        areaTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased (KeyEvent e) {
                actualizarEstado();
            }
        });
        
        actualizarEstado();
        
        //Tamaño fijo
        
        setSize(640, 440);
        setLocationRelativeTo(null); //para que aparezca en el centro
        
    }
    
    //Alterna entre el tema claro y tema oscuro
    private void cambiarTema(){
        oscuro = !oscuro;
        if (oscuro) {
            FlatDarkLaf.setup();
        } else {
            FlatLightLaf.setup();
        }
        FlatLaf.updateUI(); //repinta lo que ya esta en pantalla
    }
    
    //Actualiza el estado
    private void abrir() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("Ficheros de texto (txt)", "txt")); //filtra por el tipo de fichero
        if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                areaTexto.setText(Files.readString(selector.getSelectedFile().toPath()));
                actualizarEstado();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se ha podido abrir el fichero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void actualizarEstado() {
        int caracteres = areaTexto.getText().length();
        labelEstado.setText(caracteres + " caracteres");
        actionNuevo.setEnabled(caracteres > 0);
    }
    
}
