package test.java.botiga.producte;

import main.java.botiga.producte.Producte;
import main.java.botiga.producte.GestorProductes;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GestorProductesTest {

    // Test para añadir un producto
    @Test
    public void afegirProducteFunciona() {
        // ARRANGE
        GestorProductes gestor = new GestorProductes();
        Producte p = new Producte("Pizza", 5.0, 1);

        // ACT
        gestor.afegirProducte(p);
        ArrayList<Producte> resultats = gestor.cercarProductesPerNom("Pizza");

        // ASSERT
        assert (resultats.size() == 1);
    }

    // Test para buscar un producto
    @Test
    public void cercarProducteFunciona() {
        // ARRANGE
        GestorProductes gestor = new GestorProductes();
        Producte p = new Producte("Llet", 1.2, 1);
        gestor.afegirProducte(p);

        // ACT
        ArrayList<Producte> resultats = gestor.cercarProductesPerNom("lle");

        // ASSERT
        assert (resultats.size() == 1);
    }

    // Test para eliminar un producto
    @Test
    public void eliminarProducteFunciona() {
        // ARRANGE
        GestorProductes gestor = new GestorProductes();
        Producte p = new Producte("Pa", 0.9, 1);
        gestor.afegirProducte(p);

        // ACT
        boolean eliminat = gestor.eliminarProducte("Pa");

        // ASSERT
        assert (eliminat);
    }

    // Test para aplicar un descuento
    @Test
    public void aplicarDescompteFunciona() {
        // ARRANGE
        GestorProductes gestor = new GestorProductes();
        Producte p = new Producte("Aigua", 1.0, 1);
        Producte p2 = new Producte("Pera", 5.0, 3);
        gestor.afegirProducte(p);
        gestor.afegirProducte(p2);

        // ACT
        gestor.aplicarDescompte("Aigua", 10); // 10% de descuento

        // ASSERT
        assert (p.getPreu() == 0.9);
        assert (p2.getPreu() == 5.0);
    }

    @Test
    public void mostrarCatalegFunciona() {
        // ARRANGE
        GestorProductes gestor = new GestorProductes();
        Producte p = new Producte("Aigua", 1.0, 1);
        Producte p2 = new Producte("Pera", 5.0, 3);
        gestor.afegirProducte(p);
        gestor.afegirProducte(p2);
        boolean error = false;

        // ACT
        try {
            gestor.mostrarCataleg();
        } catch (Exception ex) {
            error = true;
        }

        // ASSERT
        assert (!error);
    }
}
