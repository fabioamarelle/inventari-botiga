package test.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.GestorVendes;
import main.java.botiga.venda.Transaccio;
import main.java.botiga.venda.Venda;
import org.junit.jupiter.api.Test;

import java.awt.image.FilteredImageSource;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorVendesTest {

    @Test
    public void testafegirVenda(){
        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine","yassine@gmail.com",Rol.ADMINISTRADOR);
        Venda v = new Venda(LocalDate.of(2024,10,23),u);

        g.afegirVenda(v);

        assert g.getLlista_vendes().size() == 1;
    }
    @Test
    public void testvendesPeriode(){
        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine","yassine@gmail.com", Rol.ADMINISTRADOR);
        Venda v = new Venda(LocalDate.of(2024,10,23),u);
        Venda v2 = new Venda(LocalDate.of(2024,10,24),u);
        Venda v3 = new Venda(LocalDate.of(2024,10,29),u);



        g.afegirVenda(v);
        g.afegirVenda(v2);
        g.afegirVenda(v3);

        ArrayList<Venda> resultat = g.vendesPeriode(LocalDate.of(2024,10,23),LocalDate.of(2024,10,25));

        assert resultat.size() == 2;

    }

    @Test
    public void testbuscarVenda(){

        GestorUsuaris gu = new GestorUsuaris();
        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine","yassine@gmail.com",Rol.ADMINISTRADOR);
        Venda v = new Venda(LocalDate.of(2024,10,23),u);
        Venda v2 = new Venda(LocalDate.of(2024,10,24),u);
        Venda v3 = new Venda(LocalDate.of(2024,10,29),u);

        g.setGestorUsuaris(gu);
        gu.afegirUsuari(u);
        g.afegirVenda(v2);
        g.afegirVenda(v3);
        g.afegirVenda(v);

        ArrayList<Venda> resultat = g.buscarVenda("Yassine",LocalDate.of(2024,10,23));

        assert resultat.size() == 1;


    }

    @Test
    public void testVendesProducte() {
        GestorUsuaris gu = new GestorUsuaris();
        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine", "yassine@gmail.com", Rol.ADMINISTRADOR);
        g.setGestorUsuaris(gu);
        gu.afegirUsuari(u);

        Producte p1 = new Producte("Cafè", 2.5, 10);
        Producte p2 = new Producte("Te", 1.5, 15);

        Transaccio t1 = new Transaccio(p1, 2); // 2 Cafès
        Transaccio t2 = new Transaccio(p1, 1); // 1 Cafè més
        Transaccio t3 = new Transaccio(p2, 3); // 3 Tes

        Venda venda1 = new Venda(LocalDate.of(2024, 10, 23), u);
        venda1.afegirTransaccio(t1);
        venda1.afegirTransaccio(t3);

        Venda venda2 = new Venda(LocalDate.of(2024, 10, 24), u);
        venda2.afegirTransaccio(t2);

        g.afegirVenda(venda1);
        g.afegirVenda(venda2);

        int totalVendesCafè = g.vendesProducte(p1);

        assert totalVendesCafè == 2;
    }
}
