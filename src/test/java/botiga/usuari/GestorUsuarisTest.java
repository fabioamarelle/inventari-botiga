package test.java.botiga.usuari;

import org.junit.jupiter.api.Test;

public class GestorUsuarisTest {
    // Ejemplo de test
    @Test
    public void addTwoNumbersOK(){
        // ARRANGE
        int a = 1;
        int b = 2;

        // ACT
        int result = a + b;

        // ASSERT
        assert (a + b == 3);
    }
}
