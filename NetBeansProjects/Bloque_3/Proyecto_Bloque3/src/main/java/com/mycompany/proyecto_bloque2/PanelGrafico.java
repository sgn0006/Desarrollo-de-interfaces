
package com.mycompany.proyecto_bloque2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class PanelGrafico extends JPanel {

    // Accedemos a los datos de los animales
    private Datos datos;

    // Constructor
    public PanelGrafico(Datos datos) {
        this.datos = datos;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Obtenemos la lista de animales
        ArrayList<Animal> animales = datos.getLista();

        // Título de la gráfica
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2.drawString("Animales por especie", 30, 35);

        // Comprobamos si hay animales
        if (animales.isEmpty()) {
            g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g2.drawString("Todavía no hay animales registrados", 30, 80);
            return;
        }

        // Creamos un mapa para contar las especies
        HashMap<String, Integer> especies = new HashMap<>();

        for (Animal animal : animales) {

            String especie = animal.getEspecie();

            if (especie == null || especie.trim().isEmpty()) {
                especie = "Sin especificar";
            }

            // Sumamos uno a la especie correspondiente
            especies.put(especie,
                    especies.getOrDefault(especie, 0) + 1);
        }

        // Colores para las porciones de la tarta
        Color[] colores = {
            new Color(52, 152, 219),
            new Color(46, 204, 113),
            new Color(241, 196, 15),
            new Color(231, 76, 60),
            new Color(155, 89, 182),
            new Color(230, 126, 34)
        };

        // Calculamos el total de animales
        int total = animales.size();

        // Tamaño y posición de la tarta
        int x = 40;
        int y = 75;
        int ancho = 230;
        int alto = 230;

        // Dibujamos cada porción
        int anguloInicial = 0;
        int indiceColor = 0;

        for (Map.Entry<String, Integer> entrada
                : especies.entrySet()) {

            String nombre = entrada.getKey();
            int cantidad = entrada.getValue();

            // Calculamos el ángulo de cada porción
            int angulo = cantidad * 360 / total;

            // Seleccionamos el color
            Color color = colores[indiceColor % colores.length];

            // Pintamos la porción
            g2.setColor(color);
            g2.fillArc(x, y, ancho, alto,
                    anguloInicial, angulo);

            // Actualizamos el ángulo inicial
            anguloInicial += angulo;

            indiceColor++;
        }

        // Dibujamos la leyenda
        int posicionY = 95;

        g2.setFont(new Font("SansSerif", Font.PLAIN, 14));

        for (Map.Entry<String, Integer> entrada
                : especies.entrySet()) {

            String nombre = entrada.getKey();
            int cantidad = entrada.getValue();

            Color color = colores[
                    (posicionY - 95) / 25 % colores.length];

            // Cuadrado de color
            g2.setColor(color);
            g2.fillRect(310, posicionY - 12, 15, 15);

            // Nombre y cantidad
            g2.setColor(Color.BLACK);
            g2.drawString(nombre + ": " + cantidad,
                    335, posicionY);

            posicionY += 25;
        }

        // Mostramos el total
        g2.setFont(new Font("SansSerif", Font.BOLD, 14));
        g2.drawString("Total de animales: " + total,
                310, posicionY + 15);
    }
}
