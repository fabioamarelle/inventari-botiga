package main.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Usuari;


public class Transaccio {
    private Usuari client;
    private Producte producte;
    private int quantitat;

    public Usuari getClient() {
        return client;
    }

    public Transaccio(Producte producte, int quantitat, Usuari client) {
        this.producte = producte;
        this.quantitat = quantitat;
        this.client = client;
    }

    public void setClient(Usuari client) {
        this.client = client;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }


}
