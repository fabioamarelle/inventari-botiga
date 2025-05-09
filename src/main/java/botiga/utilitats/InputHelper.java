package main.java.botiga.utilitats;

import java.util.Scanner;

public class InputHelper {
    public static int llegirEnter(String missatge) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(missatge);
                num = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número vàlid. Torna a intentar-ho.");
            }
        }
        scanner.close();
        return num;
    }
    public static int llegirEnterPositiu(String missatge) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(missatge);
                num = Integer.parseInt(scanner.nextLine());
                if (num > 0) {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número vàlid. Torna a intentar-ho.");
            }
        }
        scanner.close();
        return num;
    }
    public static double llegirDecimal(String missatge) {
        Scanner scanner = new Scanner(System.in);
        double num = 0.0;
        boolean valid = false;
        while (!valid) {
            try{
                System.out.println(missatge);
                num = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e){
                System.out.println("Introdueix un número decimal, el que has posat no és vàlid.");
            }
        }
        scanner.close();
        return num;
    }
    public static String llegirString(String missatge){
        Scanner scanner = new Scanner(System.in);
        String input;
        do
        {
            System.out.println(missatge);
            input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println("El text estava buit, introdueix un de vàlid.");
            }
        }
        while (input.isEmpty());
        scanner.close();
        return input;
    }

    public static boolean llegirBoolean(String missatge, String opcioTrue, String opcioFalse) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print(missatge);
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals(opcioTrue)) {
                scanner.close();
                return true;
            } else if (input.equals(opcioFalse)) {
                scanner.close();
                return false;
            } else {
                System.out.println("Error: Resposta no reconeguda. (" + opcioTrue + " / " + opcioFalse + ")");
            }

        }
    }
}
