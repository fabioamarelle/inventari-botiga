package test.java.botiga.usuari;

import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Usuari;
import org.junit.jupiter.api.Test;
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
}
