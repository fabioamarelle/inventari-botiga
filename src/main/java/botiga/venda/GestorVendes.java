package main.java.botiga.venda;

import main.java.botiga.Botiga;
import main.java.botiga.producte.GestorProductes;
import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.utilitats.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorVendes {
    // La classe GestorVendes centralitza la gestió de l’historial de vendes, i també permet fer cerques o
    // generar informes bàsics. Podria ampliar-se amb funcionalitats com resum de vendes per període o vendes
    // per producte. Aquesta classe assegura la persistència temporal de les vendes realitzades dins de la sessió
    // i manté la coherència del sistema.

    private final ArrayList<Venda> llistaVendes;
    private GestorUsuaris gestorUsuaris = Botiga.gestorUsuaris;

    public GestorVendes() {
        this.llistaVendes = new ArrayList<>();
    }
    public void afegirVenda(Venda venda){
        llistaVendes.add(venda);
    }

    public void afegirVenda(GestorProductes gestorProductes){
        LocalDate data;
        while (true) {
            try {
                String dataString = InputHelper.llegirString("Introdueix la data de la venda (DD-MM-YYYY): ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataString, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Error: Format de la data incorrecte.");
            }
        }
        Usuari usuari = null;
        while (usuari == null) {
            usuari = this.gestorUsuaris.obtenirUsuari(InputHelper.llegirString("Introdueix el correu electrònic de l'usuari: "));
            if (usuari == null){
                System.out.println("Error. Usuari no trobat.");
            }
        }
        Venda venda = new Venda(data, usuari);

        System.out.println("Introduint transaccions: ");
        boolean introduintTransaccions = true;
        while (introduintTransaccions){
            Producte producte = null;
            while (producte == null){
                producte = gestorProductes.cercarProducte(InputHelper.llegirString("Introdueix el nom del producte: "));
                if (producte == null){
                    System.out.println("Error. Producte no trobat.");
                }
            }


            int quantitat = InputHelper.llegirEnterPositiu("Introdueix la quantitat comprada: ");
            if ((producte.getStock() - quantitat) < 0) {
                System.out.println("No hi ha suficient stock. No s'ha realitzat la transacció.");
            } else {
                producte.setStock(producte.getStock() - quantitat);
                Transaccio transaccio = new Transaccio(producte, quantitat);
                venda.afegirTransaccio(transaccio);
                System.out.println("S'ha afegit la transacció.");
            }

            introduintTransaccions = InputHelper.llegirBoolean("Vols continuar introduïnt transaccions?: ", "S", "N");
        }
        llistaVendes.add(venda);
    }

    public void setGestorUsuaris(GestorUsuaris gestorUsuaris) {
        this.gestorUsuaris = gestorUsuaris;
    }

    public ArrayList<Venda> buscarVenda(String correuElectronic, LocalDate dia) {
        ArrayList<Venda> resultat = new ArrayList<>();
        boolean vendaTrobada = false;

        for (Usuari usuari : gestorUsuaris.getLlistaUsuaris()) {
            if (usuari.getCorreuElectronic().equals(correuElectronic)) {
                for (Venda venda : llistaVendes) {
                    if (venda.getData().equals(dia)) {
                        System.out.println(venda);
                        resultat.add(venda);
                        vendaTrobada = true;
                    }
                }
            }
        }

        if (!vendaTrobada) {
            System.out.println("No existeix aquesta venda.");
        }

        return resultat;
    }

    public ArrayList<Venda> buscarVenda() {
        ArrayList<Venda> resultat = new ArrayList<>();
        LocalDate data;
        while (true) {
            try {

                String dataString = InputHelper.llegirString("Introdueix la data de la venda (DD-MM-YYYY): ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataString, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Error: Format de la data incorrecte.");
            }
        }

        String correuElectronic = InputHelper.llegirString("Introdueix el correu electrònic de l'usuari: ");

        boolean vendaTrobada = false;

        for (Usuari usuari : gestorUsuaris.getLlistaUsuaris()) {
            if (usuari.getCorreuElectronic().equals(correuElectronic)) {
                for (Venda venda : llistaVendes) {
                    if (venda.getData().equals(data)) {
                        resultat.add(venda);
                        vendaTrobada = true;
                    }
                }
            }
        }

        if (!vendaTrobada) {
            System.out.println("No existeix aquesta venda.");
        }

        return resultat;
    }

        public ArrayList<Venda> vendesPeriode(LocalDate dataInici, LocalDate dataFi){
        ArrayList<Venda> resultat = new ArrayList<>();
        for (Venda venda : llistaVendes){
            LocalDate dataVenda = venda.getData();
            if ((dataVenda.isEqual(dataInici) || dataVenda.isAfter(dataInici)) &&
                    (dataVenda.isEqual(dataFi) || dataVenda.isBefore(dataFi))) {
                resultat.add(venda);
            }

        }
        return resultat;
    }


    public ArrayList<Venda> vendesProducte(Producte producte) {
        ArrayList<Venda> vendesAmbProducte = new ArrayList<>();

        for (Venda venda : llistaVendes) {
            for (Transaccio transaccio : venda.getLlistaTransaccio()) {
                if (transaccio.getProducte().equals(producte)) {
                    vendesAmbProducte.add(venda);
                }
            }
        }
        return vendesAmbProducte;
    }

    public void esborrarVenda(Venda venda){
        llistaVendes.remove(venda);
    }

    public void esborrarVenda(){
        ArrayList<Venda> vendesAEsborrar = buscarVenda();
        for (Venda v : vendesAEsborrar){
            llistaVendes.remove(v);
        }
    }

    public ArrayList<Venda> getLlistaVendes() {
        return llistaVendes;
    }

}
