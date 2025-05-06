package main.java.botiga.venda;

import main.java.botiga.usuari.Usuari;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
    private LocalDate data;
    private Usuari usuari;
    private ArrayList<Transaccio> llistaTransaccio;

    public Venda(LocalDate data, Usuari usuari) {
        this.data = data;
        this.usuari = usuari;
        this.llistaTransaccio = new ArrayList<>();
    }

    public Venda(LocalDate data) {
        this.data = data;
        this.llistaTransaccio = new ArrayList<>();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ArrayList<Transaccio> getLlistaTransaccio() {
        return llistaTransaccio;
    }

    public void setLlistaTransaccio(ArrayList<Transaccio> llistaTransaccio) {
        this.llistaTransaccio = llistaTransaccio;
    }

    public void afegirTransaccio(Transaccio transaccio){
        this.llistaTransaccio.add(transaccio);
    }

    public void esborrarTransaccio(Transaccio transaccio){
        this.llistaTransaccio.remove(transaccio);
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }
}