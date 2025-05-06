package main.java.botiga.utilitats;

import java.util.Scanner;

public class InputHelper {

    // Única instància de Scanner per evitar problemes amb System.in
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Llegeix un número enter des de consola.
     */
    public static int llegirEnter(String missatge) {
        int num = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(missatge);
                num = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número enter vàlid.");
            }
        }

        return num;
    }

    /**
     * Llegeix un número enter positiu (> 0).
     */
    public static int llegirEnterPositiu(String missatge) {
        int num;

        do {
            num = llegirEnter(missatge);
            if (num <= 0) {
                System.out.println("Error: El número ha de ser positiu.");
            }
        } while (num <= 0);

        return num;
    }

    /**
     * Llegeix un número decimal (double) des de consola.
     */
    public static double llegirDecimal(String missatge) {
        double num = 0.0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(missatge);
                num = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número decimal vàlid.");
            }
        }

        return num;
    }

    /**
     * Llegeix una cadena de text no buida.
     */
    public static String llegirString(String missatge) {
        String input;

        do {
            System.out.print(missatge);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: El text no pot estar buit.");
            }
        } while (input.isEmpty());

        return input;
    }

    /**
     * Llegeix una resposta booleana amb "sí"/"no".
     */
    public static boolean llegirBoolean(String missatge) {
        String input;

        while (true) {
            System.out.print(missatge + " (sí/no): ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("sí") || input.equals("si")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Resposta no reconeguda. Escriu 'sí' o 'no'.");
            }
        }
    }
}
