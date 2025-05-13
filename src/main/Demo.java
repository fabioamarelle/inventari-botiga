package main;

import main.java.botiga.Botiga;
import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.Transaccio;
import main.java.botiga.venda.Venda;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Demo {
    public static void main(String[] args) {

        // Productes
        Producte producte1 = new Producte("AMD Ryzen 7 9800X3D 4.7/5.2GHz", 749.99, 85);
        Producte producte2 = new Producte("AMD Ryzen 7 7800X3D 4.2 GHz/5 GHz", 699.99, 49);

        Producte producte3 = new Producte("GeForce RTX 4060 V2 OC Edition 8GB GDDR6 DLSS3", 342.99, 36);
        Producte producte4 = new Producte("RTX 5070 VENTUS 2X OC 12GB GDDR7 Reflex 2 DLSS4", 342.99, 94);

        Producte producte5 = new Producte("Corsair Vengeance DDR4 3200 PC4-25600 32GB 2x16GB", 67.99, 48);
        Producte producte6 = new Producte("Crucial SO-DIMM DDR4 3200Mhz PC4-25600 8GB", 14.99, 24);

        Botiga.gestorProductes.afegirProducte(producte1);
        Botiga.gestorProductes.afegirProducte(producte2);
        Botiga.gestorProductes.afegirProducte(producte3);
        Botiga.gestorProductes.afegirProducte(producte4);
        Botiga.gestorProductes.afegirProducte(producte5);
        Botiga.gestorProductes.afegirProducte(producte6);

        // Usuaris
        Usuari usuari1 = new Usuari("Fabio Amarelle Rodrigues", "amarellerodriguesfabio@gmail.com", Rol.ADMINISTRADOR);
        Usuari usuari2 = new Usuari("Aitor Alcalá Jiménez", "alcalajimenezaitor@gmail.com", Rol.ADMINISTRADOR);
        Usuari usuari3 = new Usuari("Joel Oliva Domènech", "domenecholivajoel@gmail.com", Rol.ADMINISTRADOR);

        Usuari usuari4 = new Usuari("Alex Cuenca Gallardo", "cuencagallardoalex@gmail.com", Rol.CLIENT);
        Usuari usuari5 = new Usuari("Yassine Aktaou Larej", "aktaoularejyassine@gmail.com", Rol.CLIENT);
        Usuari usuari6 = new Usuari("Edgar Castellanos Contreras", "contrerascastellanosedgar@gmail.com", Rol.CLIENT);
        Usuari usuari7 = new Usuari("Biel Urdi Agrafojo", "agrafojourdibiel@gmail.com", Rol.CLIENT);

        Botiga.gestorUsuaris.afegirUsuari(usuari1);
        Botiga.gestorUsuaris.afegirUsuari(usuari2);
        Botiga.gestorUsuaris.afegirUsuari(usuari3);
        Botiga.gestorUsuaris.afegirUsuari(usuari4);
        Botiga.gestorUsuaris.afegirUsuari(usuari5);
        Botiga.gestorUsuaris.afegirUsuari(usuari6);
        Botiga.gestorUsuaris.afegirUsuari(usuari7);

        // Vendes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Venda venda1 = new Venda(LocalDate.parse("13-05-2025", formatter), usuari1);
        venda1.afegirTransaccio(new Transaccio(producte1, 9));
        venda1.afegirTransaccio(new Transaccio(producte6, 2));
        Botiga.gestorVendes.afegirVenda(venda1);

        Venda venda2 = new Venda(LocalDate.parse("09-01-2025", formatter), usuari6);
        venda2.afegirTransaccio(new Transaccio(producte5, 1));
        venda2.afegirTransaccio(new Transaccio(producte2, 2));
        Botiga.gestorVendes.afegirVenda(venda2);

        Venda venda3 = new Venda(LocalDate.parse("01-12-2024", formatter), usuari2);
        venda3.afegirTransaccio(new Transaccio(producte5, 6));
        venda3.afegirTransaccio(new Transaccio(producte3, 2));
        Botiga.gestorVendes.afegirVenda(venda3);

        Venda venda4 = new Venda(LocalDate.parse("22-12-2024", formatter), usuari5);
        venda4.afegirTransaccio(new Transaccio(producte2, 2));
        venda4.afegirTransaccio(new Transaccio(producte4, 1));
        Botiga.gestorVendes.afegirVenda(venda4);

        Venda venda5 = new Venda(LocalDate.parse("29-03-2025", formatter), usuari4);
        venda5.afegirTransaccio(new Transaccio(producte1, 1));
        venda5.afegirTransaccio(new Transaccio(producte6, 4));
        Botiga.gestorVendes.afegirVenda(venda5);

        Botiga.menu();

    }
}
