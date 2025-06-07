package ui;

import cipher.CaesarCipher;
import evaluator.ExpressionEvaluator;

/**
 * The {@code ConsoleMenu} class is responsible for managing the application's main console menu
 * and handling user interactions. It orchestrates the flow between different functionalities
 * like Caesar Cipher operations and expression evaluation, utilizing {@code ConsoleIO} for I/O.
 */
public class ConsoleMenu {
    private final ConsoleIO consoleIO = new ConsoleIO();
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
    private boolean running = true;

    /**
     * Starts the main application loop, displaying the menu and processing user selections.
     * The loop continues until the user chooses to exit the application.
     */
    public void start() {
        boolean operationCompletedSuccessfully;
        while (running) {
            int choice = consoleIO.mainMenuSelection();
            operationCompletedSuccessfully = false;
            switch (choice) {
                case 1:
                    operationCompletedSuccessfully = handleCaesarEncryption();
                    break;
                case 2:
                    operationCompletedSuccessfully = handleCaesarDecryption();
                    break;
                case 3:
                    operationCompletedSuccessfully = handleCaesarBruteForceDecryption();
                    break;
                case 4:
                    operationCompletedSuccessfully = handleExpressionEvaluation();
                    break;
                case 5:
                    exitApplication();
                    break;
            }
            if (running && operationCompletedSuccessfully) {
                boolean continueApp = consoleIO.getConfirmation("\nContinue?");
                if (!continueApp) {
                    exitApplication();
                }
            }
        }
        consoleIO.closeScanner();
    }

    /**
     * Handles the Caesar Cipher encryption process. It prompts the user for text input (from keyboard or file)
     * and a shift value, then displays the encrypted result.
     *
     * @return {@code true} if the encryption operation completed successfully, {@code false} otherwise (e.g., if input was cancelled).
     */
    private boolean handleCaesarEncryption() {
        consoleIO.displayMessage("\n--- Caesar Cipher Encryption selected ---");
        String textToEncrypt = getInputText("encrypt");
        if (textToEncrypt == null) {
            // Error reading file or file is empty. Returning to the main menu.
            return false;
        }
        int shift = consoleIO.getShiftValue("Enter the shift value (integer): ");
        String resultText = caesarCipher.encrypt(textToEncrypt, shift);
        consoleIO.displayCaesarOperationResult(textToEncrypt, shift, resultText, "Caesar Cipher Encryption");
        return true;
    }


    /**
     * Handles the Caesar Cipher decryption process. It prompts the user for text input (from keyboard or file)
     * and a shift value, then displays the decrypted result.
     *
     * @return {@code true} if the decryption operation completed successfully,
     * {@code false} otherwise (e.g., if input was cancelled).
     */

    private boolean handleCaesarDecryption() {
        consoleIO.displayMessage("\n--- Caesar Cipher Decryption selected ---");
        String textToDecrypt = getInputText("decrypt");
        if (textToDecrypt == null) {
            // Error reading file or file is empty. Returning to the main menu.
            return false;
        }
        int shift = consoleIO.getShiftValue("Enter the shift value (integer): ");
        String resultText = caesarCipher.decrypt(textToDecrypt, shift);
        consoleIO.displayCaesarOperationResult(textToDecrypt, shift, resultText, "Caesar Cipher Decryption");
        return true;
    }

    /**
     * Orchestrates the brute-force decryption process for the Caesar Cipher. It prompts the user for an encrypted text
     * (from keyboard or file), then calls the {@code CaesarCipher} method {@code bruteForceDecrypt}
     * to get all possible deciphered options.
     * Finally, it displays these unique options along with their smallest corresponding shift values.
     *
     * @return {@code true} if the brute-force decryption operation completed successfully,
     * {@code false} otherwise (e.g., if input was cancelled).
     */
    private boolean handleCaesarBruteForceDecryption() {
        consoleIO.displayMessage("\n--- Brute-force Caesar Cipher Decryption ---");
        String textToDecrypt = getInputText("decrypt");
        if (textToDecrypt == null) {
            // Error reading file or file is empty. Returning to the main menu.
            return false;
        }
        consoleIO.displayMessage("\nAttempting all possible shifts...");
        var decryptedOptions = caesarCipher.bruteForceDecrypt(textToDecrypt);
        consoleIO.displayCaesarBruteForceDecryption(textToDecrypt, decryptedOptions);
        return true;
    }

    /**
     * Handles the arithmetic expression evaluation process. It takes an expression,
     * evaluates it using the {@code ExpressionEvaluator}, and displays the result to the console.
     *
     * @return {@code true} if the operation completed successfully.
     */
    private boolean handleExpressionEvaluation() {
        consoleIO.displayMessage("\n--- Arithmetic Expression Evaluation selected ---");
        String expression = getInputText("evaluate");

        // If input was cancelled (e.g., empty file not confirmed), return to main menu
        if (expression == null) {
            return false;
        }

        try {
            double resultValue = expressionEvaluator.evaluate(expression);
            consoleIO.displayMessage("Expression: " + expression);
            consoleIO.displayMessage("Result: " + resultValue);
        } catch (IllegalArgumentException e) {
            consoleIO.displayMessage("Error: Invalid expression. " + e.getMessage());
            // Return false if there was an error, so as not to offer "Continue?" immediately after an error
            return false;

        } catch (ArithmeticException e) {
            // Division by zero
            consoleIO.displayMessage("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Catch any other unexpected errors
            consoleIO.displayMessage("An unexpected error occurred: " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Prompts the user to select an input source (keyboard or file) and reads the text for a given operation.
     *
     * @param operation A string describing the operation (e.g., "encrypt", "decrypt", "brute-force decrypt")
     *                  to be used in user prompts.
     * @return The input text as a string, or {@code null} if the input was cancelled (e.g., empty file warning declined).
     */
    private String getInputText(String operation) {
        int sourceChoice = consoleIO.getSourceSelection();
        String inputText;
        if (sourceChoice == 1) {
            consoleIO.displayMessage("You selected keyboard input.");
            inputText = consoleIO.readText("Enter text to " + operation + ":");
        } else {
            consoleIO.displayMessage("You selected file input");
            inputText = consoleIO.readTextFromFile("Enter the full path to the text file: ");
        }
        return inputText;
    }

    /**
     * Initiates the application exit sequence. It displays a farewell message to the user
     * and sets the internal running flag to {@code false} to terminate the main application loop.
     */
    private void exitApplication() {
        consoleIO.displayMessage("\nExiting application. Have a great day!\n");
        this.running = false;
    }
}