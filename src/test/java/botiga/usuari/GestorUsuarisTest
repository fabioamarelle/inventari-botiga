package test.java.botiga.usuari;

import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class GestorUsuariTest {

    @Test
    public void testAfegirUsuariCorrectament() {
        GestorUsuaris gestor = new GestorUsuaris();
        Usuari usuari = new Usuari("Joel", "joel@gmail.com",  Rol.ADMINISTRADOR);
        boolean afegit = gestor.afegirUsuari(usuari);
        assertTrue(afegit);
    }

    @Test
    public void testNoAfegirUsuariRepetit() {
        GestorUsuaris gestor = new GestorUsuaris();
        Usuari usuari = new Usuari("Joel", "joel@gmail.com", Rol.ADMINISTRADOR);
        gestor.afegirUsuari(usuari);
        boolean afegit = gestor.afegirUsuari(usuari);
        assertFalse(afegit);
    }

    @Test
    public void testObtenirUsuariExist() {
        GestorUsuaris gestor = new GestorUsuaris();
        Usuari usuari = new Usuari("Joel", "joel@gmail.com", Rol.ADMINISTRADOR);
        gestor.afegirUsuari(usuari);
        Usuari obtingut = gestor.obtenirUsuari("joel@gmail.com");
        assertNotNull(obtingut);
    }

    @Test
    public void testObtenirUsuariNoExist() {
        GestorUsuaris gestor = new GestorUsuaris();
        Usuari obtingut = gestor.obtenirUsuari("noexisteix@gmail.com");
        assertNull(obtingut);
    }

    @Test
    public void testGetTotsUsuaris() {
        GestorUsuaris gestor = new GestorUsuaris();

        Usuari usuari1 = new Usuari("Joel", "joel@gmail.com", Rol.ADMINISTRADOR);
        Usuari usuari2 = new Usuari("Arnau", "arnau@gmail.com", Rol.CLIENT);

        gestor.afegirUsuari(usuari1);
        gestor.afegirUsuari(usuari2);

        ArrayList<Usuari> llistaUsuari = gestor.getTotsUsuaris();

        assert (llistaUsuari != null);
        assert (llistaUsuari.size() == 2);
        assert (Objects.equals(llistaUsuari.getFirst(), usuari1));
        assert (Objects.equals(llistaUsuari.getLast(), usuari2));
    }

    @Test
    public void testExisteixUsuariTrue() {
        GestorUsuaris gestor = new GestorUsuaris();

        Usuari usuari1 = new Usuari("Joel", "joel@gmail.com", Rol.ADMINISTRADOR);

        gestor.afegirUsuari(usuari1);

        boolean result = gestor.existeixUsuari(usuari1.getCorreuElectronic());

        assert (result);
    }

    @Test
    public void testExisteixUsuariFalse() {
        GestorUsuaris gestor = new GestorUsuaris();

        Usuari usuari1 = new Usuari("Joel", "joel@gmail.com", Rol.ADMINISTRADOR);

        gestor.afegirUsuari(usuari1);

        boolean result = gestor.existeixUsuari("arnau@gmail.com");

        assert (!result);
    }
}
