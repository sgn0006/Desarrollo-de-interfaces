
package com.mycompany.proyecto_bloque2;

import java.util.ArrayList;

public class Datos {

    // Lista donde guardamos todos los animales
    private ArrayList<Animal> lista = new ArrayList<>();

    // Añade un animal a la lista
    public void add(Animal animal) {
        lista.add(animal);
    }

    // Devuelve todos los animales
    public ArrayList<Animal> getLista() {
        return lista;
    }

    // Devuelve el número de animales registrados
    public int getTotal() {
        return lista.size();
    }
}
