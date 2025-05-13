package test.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.venda.Transaccio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TransaccioTest {
    @Test
    public void getProductes(){
        // ARRANGE
        Producte producte = new Producte("Ratolí", 10, 25);
        Transaccio transaccio = new Transaccio(producte,21);


        // ACT
        Producte result = transaccio.getProducte();

        // ASSERT
        Assertions.assertEquals(producte, result);
    }

    @Test
    public void setProductes() {
        // ARRANGE
        Producte producteOriginal = new Producte("Ratolí", 10, 25);
        Producte producteNou = new Producte("Monitor", 115, 30);
        Transaccio transaccio = new Transaccio(producteOriginal, 21);

        // ACT
        transaccio.setProducte(producteNou);
        Producte result = transaccio.getProducte();

        // ASSERT
        Assertions.assertEquals(producteNou, result);
    }

    @Test
    public void getQuantitat() {
        // ARRANGE
        Producte producte = new Producte("Ratolí", 10, 25);
        int quantitatEsperada = 21;

        Transaccio transaccio = new Transaccio(producte, quantitatEsperada);

        // ACT
        int result = transaccio.getQuantitat();

        // ASSERT
        Assertions.assertEquals(quantitatEsperada, result);
    }

    @Test
    public void setQuantitat() {
        // ARRANGE
        Producte producte = new Producte("Ratolí", 10, 25);
        int quantitatInicial = 10;
        int quantitatNova = 21;

        Transaccio transaccio = new Transaccio(producte, quantitatInicial);

        // ACT
        transaccio.setQuantitat(quantitatNova);
        int result = transaccio.getQuantitat();

        // ASSERT
        Assertions.assertEquals(quantitatNova, result);
    }
}
