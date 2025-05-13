package test.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.GestorVendes;
import main.java.botiga.venda.Transaccio;
import main.java.botiga.venda.Venda;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class GestorVendesTest {

    @Test
    public void testAfegirVenda() {
        GestorVendes gestorVendes = new GestorVendes();
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.ADMINISTRADOR);
        Venda venda = new Venda(LocalDate.of(2024, 10, 23), usuari);

        gestorVendes.afegirVenda(venda);

        assert gestorVendes.getLlistaVendes().size() == 1;
    }

    @Test
    public void testVendesPeriode() {
        GestorVendes gestorVendes = new GestorVendes();
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.ADMINISTRADOR);

        Venda venda1 = new Venda(LocalDate.of(2024, 10, 23), usuari);
        Venda venda2 = new Venda(LocalDate.of(2024, 10, 24), usuari);
        Venda venda3 = new Venda(LocalDate.of(2024, 10, 29), usuari);

        gestorVendes.afegirVenda(venda1);
        gestorVendes.afegirVenda(venda2);
        gestorVendes.afegirVenda(venda3);

        List<Venda> resultat = gestorVendes.vendesPeriode(LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 25));

        assert resultat.size() == 2;
    }

    @Test
    public void testBuscarVenda() {
        GestorUsuaris gestorUsuaris = new GestorUsuaris();
        GestorVendes gestorVendes = new GestorVendes();
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.ADMINISTRADOR);

        gestorVendes.setGestorUsuaris(gestorUsuaris);
        gestorUsuaris.afegirUsuari(usuari);

        Venda venda1 = new Venda(LocalDate.of(2024, 10, 23), usuari);
        Venda venda2 = new Venda(LocalDate.of(2024, 10, 24), usuari);
        Venda venda3 = new Venda(LocalDate.of(2024, 10, 29), usuari);

        gestorVendes.afegirVenda(venda1);
        gestorVendes.afegirVenda(venda2);
        gestorVendes.afegirVenda(venda3);

        List<Venda> resultat = gestorVendes.buscarVenda("Yassine", LocalDate.of(2024, 10, 23));

        assert resultat.size() == 1;
    }

    @Test
    public void testVendesProducte() {
        GestorUsuaris gestorUsuaris = new GestorUsuaris();
        GestorVendes gestorVendes = new GestorVendes();
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.ADMINISTRADOR);

        gestorVendes.setGestorUsuaris(gestorUsuaris);
        gestorUsuaris.afegirUsuari(usuari);

        Producte producte1 = new Producte("Cafè", 2.5, 10);
        Producte producte2 = new Producte("Te", 1.5, 15);

        Transaccio transaccio1 = new Transaccio(producte1, 2);  // 2 Cafès
        Transaccio transaccio2 = new Transaccio(producte1, 1);  // 1 Cafè més
        Transaccio transaccio3 = new Transaccio(producte2, 3);  // 3 Tés

        Venda venda1 = new Venda(LocalDate.of(2024, 10, 23), usuari);
        venda1.afegirTransaccio(transaccio1);
        venda1.afegirTransaccio(transaccio3);

        Venda venda2 = new Venda(LocalDate.of(2024, 10, 24), usuari);
        venda2.afegirTransaccio(transaccio2);

        gestorVendes.afegirVenda(venda1);
        gestorVendes.afegirVenda(venda2);

        int totalVendesCafe = gestorVendes.vendesProducte(producte1);

        assert totalVendesCafe == 2;
    }
}
