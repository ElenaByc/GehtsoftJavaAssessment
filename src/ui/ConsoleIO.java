package ui;

import java.util.Scanner;

public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    public int mainMenuSelection() {
        displayMenu();
        return getIntegerWithinRange("Enter your selection: ", 1, 4);
    }

    private void displayMenu() {
        System.out.println("=".repeat(42));
        System.out.println(" Welcome to Gehtsoft Technical Assessment");
        System.out.println("=".repeat(42));
        System.out.println("Please select from the following options:");
        System.out.println();
        System.out.println("1. Caesar Cipher Encryption");
        System.out.println("2. Caesar Cipher Decryption");
        System.out.println("3. Arithmetic Expression Evaluation");
        System.out.println("4. Exit");
        System.out.println();
    }

    private int getIntegerWithinRange(String message, int min, int max) {
        int number;
        while (true) {
            System.out.print(message);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    displayInvalidSelectionMessage();
                }
            } catch (NumberFormatException e) {
                displayInvalidSelectionMessage();
            }
        }
    }

    public void displayInvalidSelectionMessage() {
        System.out.println("Invalid selection, please select from the available options.");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }


}
