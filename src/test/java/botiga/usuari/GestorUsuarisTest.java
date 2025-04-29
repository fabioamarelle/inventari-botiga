
package test.java.botiga.usuari;

package test.java.botiga.usuari;
import main.java.botiga.usuari.Usuari;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuariTest {

    @Test
    void testConstructorIGetters() {

        // Verifiquem constructor i getters
        Usuari usuari = new Usuari("Arnau", "arnau@gmail.com", "client");

        assertEquals("Arnau", usuari.getNom());
        assertEquals("arnau@gmail.com", usuari.getCorreuElectronic());
        assertEquals("client", usuari.getRol());
    }

    @Test
    void testEsAdministradorAmbAdministrador() {


        // Un usuari amb rol "administrador" ha de ser administrador
        Usuari admin = new Usuari("Joel", "joel@gmail.com", "administrador");

        assertTrue(admin.esAdministrador());
    }

    @Test
    void testEsAdministradorAmbClient() {


        // Un usuari amb rol "client" no ha de ser administrador
        Usuari client = new Usuari("Edgar", "edgar@gmail.com", "client");

        assertFalse(client.esAdministrador());
    }
}
