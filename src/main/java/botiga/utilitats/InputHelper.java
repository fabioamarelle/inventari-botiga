package main.java.botiga.utilitats;

import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    public static int llegirEnter(String missatge) {
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
        return num;
    }
    public static int llegirEnterPositiu(String missatge) {
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
        return num;
    }
    public static double llegirDecimal(String missatge) {
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
        return num;
    }
    public static String llegirString(String missatge){
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
        return input;
    }

    public static void reiniciarScanner() {
        scanner = new Scanner(System.in);
    }

}
