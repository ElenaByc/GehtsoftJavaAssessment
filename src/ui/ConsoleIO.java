package ui;

import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The {@code ConsoleIO} class handles all console-based input and output operations for the application.
 * It provides methods for displaying menus, messages, and reading various types of user input
 * from the keyboard or files, ensuring robust interaction with the user.
 */
public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the main menu to the user and prompts for a selection.
     * It ensures the user's input is a valid integer within the defined range of menu options.
     *
     * @return The integer representing the user's valid menu selection.
     */
    public int mainMenuSelection() {
        displayMenu();
        return getIntegerWithinRange("Enter your selection: ", 1, 5);
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("=".repeat(42));
        System.out.println(" Welcome to Gehtsoft Technical Assessment");
        System.out.println("=".repeat(42));
        System.out.println("Please select from the following options:");
        System.out.println();
        System.out.println("1. Caesar Cipher Encryption");
        System.out.println("2. Caesar Cipher Decryption (known shift)");
        System.out.println("3. Caesar Cipher Brute-force Decryption (unknown shift)");
        System.out.println("4. Arithmetic Expression Evaluation");
        System.out.println("5. Exit");
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

    /**
     * Prompts the user for a yes/no confirmation.
     * The input is case-insensitive and returns true for 'y', false otherwise.
     *
     * @param prompt The message to display to the user before awaiting confirmation.
     * @return {@code true} if the user confirms with 'y', {@code false} otherwise.
     */
    public boolean getConfirmation(String prompt) {
        System.out.print(prompt + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    /**
     * Displays a standard error message indicating that the user's input was invalid
     * and that they should select from the available options.
     */
    public void displayInvalidSelectionMessage() {
        System.out.println("Invalid selection, please select from the available options.");
    }

    /**
     * Displays a given message string to the console, followed by a new line.
     *
     * @param message The string message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts the user to select an input source (keyboard or file) for text operations.
     * It ensures the user's input is a valid integer within the range of source options.
     *
     * @return The integer representing the user's valid source selection (1 for keyboard, 2 for file).
     */
    public int getSourceSelection() {
        System.out.println("\nSelect input source:");
        System.out.println("1. Enter text from keyboard");
        System.out.println("2. Load text from file");
        return getIntegerWithinRange("Enter your choice: ", 1, 2);
    }

    /**
     * Reads a line of text input from the keyboard.
     *
     * @param prompt The message to display to the user before reading input.
     * @return The trimmed string entered by the user.
     */
    public String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }


    /**
     * Prompts the user for a file path and attempts to read its content.
     * Handles potential {@code IOException} during file reading. If the file is empty,
     * it prompts the user for confirmation to proceed with empty input.
     *
     * @param prompt The message to display to the user prompting for the file path.
     * @return The content of the file as a string, or {@code null} if an error occurs or the user
     * chooses not to proceed with an empty file.
     */
    public String readTextFromFile(String prompt) {
        System.out.print(prompt);
        String filePath = scanner.nextLine().trim();
        try {
            Path path = Paths.get(filePath);
            String content = Files.readString(path);
            System.out.println("File read successfully.");
            if (content.isEmpty()) {
                boolean proceed = getConfirmation("Warning: File is empty. Do you want to proceed with empty input text?");
                return proceed ? content : null;
            }
            return content;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Prompts the user to enter an integer shift value.
     * It repeatedly prompts until a valid integer is entered.
     *
     * @param prompt The message to display to the user before awaiting the shift value.
     * @return The valid integer shift value entered by the user.
     */
    public int getShiftValue(String prompt) {
        int shift;
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

    /**
     * Displays the result of a Caesar cipher encryption or decryption operation.
     *
     * @param inputText     The original text used in the operation.
     * @param shiftValue    The shift value applied during the operation.
     * @param resultText    The resulting text after the operation.
     * @param operationType A string describing the type of operation (e.g., "Caesar Cipher Encryption").
     */
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

    /**
     * Displays the results of a Caesar cipher brute-force decryption,
     * listing unique deciphered texts along with their smallest corresponding shift values.
     *
     * @param inputText        The original encrypted text that was brute-force decrypted.
     * @param decryptedOptions A map where keys are unique deciphered texts and values are
     *                         the smallest nonnegative shift values that produced them.
     */
    public void displayCaesarBruteForceDecryption(
            String inputText,
            Map<String, Integer> decryptedOptions
    ) {
        System.out.println("\n---  Caesar Cipher Brute-force Decryption Results ---");
        System.out.println("Input Text: " + inputText);
        System.out.println("-".repeat(30));
        System.out.println(" Possible decrypted versions:");
        System.out.println("-".repeat(30));
        for (var entry : decryptedOptions.entrySet()) {
            System.out.printf("Shift %2d:  %s%n", entry.getValue(), entry.getKey());
        }
        System.out.println();
    }

    /**
     * Closes the {@code Scanner} resource used for input, releasing system resources.
     * This method should be called when console input is no longer needed to prevent resource leaks.
     */
    public void closeScanner() {
        scanner.close();
    }

}
