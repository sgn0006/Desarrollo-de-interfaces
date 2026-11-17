package com.mycompany.proyecto_bloque2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pruebas del cálculo de estancia media.
 */
public class DatosTest {

    @Test
    void estanciaMediaConTresMascotas() {
        Datos datos = new Datos();
        datos.add(new Animal("Luna", 3, 5, "", "", "Kiwi", ""));
        datos.add(new Animal("Toby", 4, 10, "", "", "Pulpo", ""));
        datos.add(new Animal("Max", 2, 15, "", "", "Cóndor", ""));
        assertEquals(10.0, datos.getEstanciaMedia(), 0.001);
    }

    @Test
    void estanciaMediaSinMascotas() {
        Datos datos = new Datos();
        assertEquals(0.0, datos.getEstanciaMedia(), 0.001);
    }
}
