package main.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Usuari;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TransaccioTest {
    @Test
    public void getProductes(){
        // ARRANGE
        Usuari client = new Usuari("Jorge", "jorge@gmail.com", "client");
        Producte producte = new Producte("Ratolí", 10, 25);
        Transaccio transaccio = new Transaccio(producte,21,client);


        // ACT
        Producte result = transaccio.getProducte();

        // ASSERT
        Assertions.assertEquals(producte, result);
    }

    @Test
    public void setProductes() {
        // ARRANGE
        Usuari client = new Usuari("Jorge", "jorge@gmail.com", "client");
        Producte producteOriginal = new Producte("Ratolí", 10, 25);
        Producte producteNou = new Producte("Monitor", 115, 30);
        Transaccio transaccio = new Transaccio(producteOriginal, 21, client);

        // ACT
        transaccio.setProducte(producteNou);
        Producte result = transaccio.getProducte();

        // ASSERT
        Assertions.assertEquals(producteNou, result);
    }

    @Test
    public void getQuantitat() {
        // ARRANGE
        Usuari client = new Usuari("Antonio", "antonio@gmail.com", "client");
        Producte producte = new Producte("Ratolí", 10, 25);
        int quantitatEsperada = 21;

        Transaccio transaccio = new Transaccio(producte, quantitatEsperada, client);

        // ACT
        int result = transaccio.getQuantitat();

        // ASSERT
        Assertions.assertEquals(quantitatEsperada, result);
    }

    @Test
    public void setQuantitat() {
        // ARRANGE
        Usuari client = new Usuari("Antonio", "antonio@gmail.com", "client");
        Producte producte = new Producte("Ratolí", 10, 25);
        int quantitatInicial = 10;
        int quantitatNova = 21;

        Transaccio transaccio = new Transaccio(producte, quantitatInicial, client);

        // ACT
        transaccio.setQuantitat(quantitatNova);
        int result = transaccio.getQuantitat();

        // ASSERT
        Assertions.assertEquals(quantitatNova, result);
    }

    @Test
    public void getClient() {
        // ARRANGE
        Usuari clientEsperat = new Usuari("Adrian", "adrian@gmail.com", "client");
        Producte producte = new Producte("Pantalla", 115, 45);
        int quantitat = 5;

        Transaccio transaccio = new Transaccio(producte, quantitat, clientEsperat);

        // ACT
        Usuari clientResultat = transaccio.getClient();

        // ASSERT
        Assertions.assertEquals(clientEsperat, clientResultat);
    }

    @Test
    public void setClient() {
        // ARRANGE
        Usuari clientInicial = new Usuari("Miguel", "miguel@gmail.com", "client");
        Usuari clientNou = new Usuari("Laura", "laura@gmail.com", "client");
        Producte producte = new Producte("Portàtil", 650, 52);
        int quantitat = 5;

        Transaccio transaccio = new Transaccio(producte, quantitat, clientInicial);

        // ACT
        transaccio.setClient(clientNou);
        Usuari clientResultat = transaccio.getClient();

        // ASSERT
        Assertions.assertEquals(clientNou, clientResultat);
    }

}
