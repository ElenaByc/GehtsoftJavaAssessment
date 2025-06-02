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

    public boolean getConfirmation(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    public void displayInvalidSelectionMessage() {
        System.out.println("Invalid selection, please select from the available options.");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getSourceSelection() {
        System.out.println("\nSelect input source:");
        System.out.println("1. Enter text from keyboard");
        System.out.println("2. Load text from file");
        return getIntegerWithinRange("Enter your choice: ", 1, 2);
    }

    public String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int getShiftValue(String prompt) {
        int shift = 0;
        while (true) {
            System.out.print(prompt);
            try {
                shift = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer number for the shift value.");
            }
        }
        return shift;
    }

    public void displayCaesarOperationResult(
            String inputText,
            int shiftValue,
            String resultText,
            String operationType
    ) {
        System.out.println("\n--- " + operationType + " Result ---");
        System.out.println("Input Text: " + inputText);
        System.out.println("Shift Value: " + shiftValue);
        System.out.println("Result: " + resultText + "\n");
    }

    public void closeScanner() {
        scanner.close();
    }

}
