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
    @Test
    public void setNomOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        p.setNom("Producte1");
        String resultat = p.getNom();

        // ASSERT
        assert resultat.equals("Producte1");
    }
    @Test
    public void setPreuOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        p.setPreu(1);
        double resultat = p.getPreu();

        // ASSERT
        assert resultat == 1;
    }
    @Test
    public void setStockOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        p.setStock(1);
        double resultat = p.getStock();

        // ASSERT
        assert resultat == 1;
    }
    @Test
    public void toStringOK() {
        // ARRANGE
        Producte p = new Producte("Producte", 8.8, 10);

        // ACT
        String resultat = p.toString();

        // ASSERT
        assert (resultat != null);
    }

}
