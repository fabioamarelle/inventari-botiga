package test.java.botiga.producte;

import main.java.botiga.producte.Producte;
import org.junit.jupiter.api.Test;

public class ProducteTest {
    // Ejemplo de test
    @Test
    public void getNomOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        String resultat = p.getNom();

        // ASSERT
        assert resultat.equals("Producte");
    }
    @Test
    public void getPreuOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        double resultat = p.getPreu();

        // ASSERT
        assert resultat == 8.8;
    }
    @Test
    public void getStockOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        double resultat = p.getStock();

        // ASSERT
        assert resultat == 10;
    }
}
