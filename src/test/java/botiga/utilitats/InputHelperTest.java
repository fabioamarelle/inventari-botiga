package test.java.botiga.utilitats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static main.java.botiga.utilitats.InputHelper.*;

public class InputHelperTest {

    private InputStream copiaSystemIn;

    @BeforeEach
    public void copiarSystemIn() {
        copiaSystemIn = System.in;
    }

    @AfterEach
    public void restaurarSystemIn() {
        System.setIn(copiaSystemIn);
    }

    private void setInputStream(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void LlegirEnterOK() {
        String input = "123\n";
        setInputStream(input);
        int result = llegirEnter("Introdueix un número enter");
        assert 123 == result;
    }

    @Test
    public void LlegirEnterInvalidOK() {
        String input = "abc\n456\n";
        setInputStream(input);
        int result = llegirEnter("Introdueix un número enter");
        assert 456 == result;
    }

    @Test
    public void LlegirEnterNegatiuOK() {
        String input = "-123\n";
        setInputStream(input);
        int result = llegirEnter("Introdueix un número enter");
        assert -123 == result;
    }

    @Test
    public void LlegirEnterPositiuOK() {
        String input = "123\n";
        setInputStream(input);
        int result = llegirEnterPositiu("Introdueix un número enter positiu");
        assert 123 == result;
    }

    @Test
    public void LlegirEnterPositiuInvalidOK() {
        String input = "abc\n456\n";
        setInputStream(input);
        int result = llegirEnterPositiu("Introdueix un número enter positiu");
        assert 456 == result;
    }

    @Test
    public void LlegirEnterPositiuNegatiuOK() {
        String input = "-123\n0\n456\n";
        setInputStream(input);
        int result = llegirEnterPositiu("Introdueix un número enter positiu");
        assert 456 == result;
    }

    @Test
    public void LlegirDecimalOK() {
        String input = "123.4\n";
        setInputStream(input);
        double result = llegirDecimal("Introdueix un número decimal");
        assert 123.4 == result;
    }

    @Test
    public void LlegirDecimalInvalidOK() {
        String input = "abc\n456.7\n";
        setInputStream(input);
        double result = llegirDecimal("Introdueix un número decimal");
        assert 456.7 == result;
    }

    @Test
    public void LlegirDecimalNegatiuOK() {
        String input = "-123.4\n";
        setInputStream(input);
        double result = llegirDecimal("Introdueix un número decimal");
        assert -123.4 == result;
    }

    @Test
    public void LlegirStringOK() {
        String input = "abc\n";
        setInputStream(input);
        String result = llegirString("Introdueix un text");
        assert "abc".equals(result);
    }

    @Test
    public void LlegirStringNumeroOK() {
        String input = "123\n";
        setInputStream(input);
        String result = llegirString("Introdueix un text");
        assert "123".equals(result);
    }

    @Test
    public void LlegirStringInvalidOK() {
        String input = "\nabc\n";
        setInputStream(input);
        String result = llegirString("Introdueix un text");
        assert "abc".equals(result);
    }

    @Test
    public void LlegirBooleanTrueOK() {
        String input = "si\n";
        setInputStream(input);
        boolean result = llegirBoolean("Selecciona una opció: ", "si", "no");
        assert result;
    }

    @Test
    public void LlegirBooleanFalseOK() {
        String input = "no\n";
        setInputStream(input);
        boolean result = llegirBoolean("Selecciona una opció: ", "si", "no");
        assert !result;
    }

    @Test
    public void LlegirBooleanInvalidOK() {
        String input = "n\ns\nsI\n";
        setInputStream(input);
        boolean result = llegirBoolean("Selecciona una opció: ", "si", "no");
        assert result;
    }


}
