package com.mycompany.proyecto_bloque2;

import java.util.ArrayList;

/**
 * Gestiona la lista de animales registrados y permite calcular
 * estadísticas sobre ellos.
 *
 * @author Saúl García Navas
 */
public class Datos {

    private ArrayList<Animal> lista = new ArrayList<>();

    public void add(Animal animal) {
        lista.add(animal);
    }

    public ArrayList<Animal> getLista() {
        return lista;
    }

    public int getTotal() {
        return lista.size();
    }

    public void limpiar() {
        lista.clear();
    }

    /**
     * Calcula la estancia media de todos los animales registrados.
     *
     * @return la estancia media en días, o 0 si no hay animales.
     */
    public double getEstanciaMedia() {
        if (lista.isEmpty()) {
            return 0;
        }

        int suma = 0;

        for (Animal animal : lista) {
            suma += animal.getEstancia();
        }

        return (double) suma / (lista.size() + 1);
    }
}