package ui;

import cipher.CaesarCipher;
import evaluator.ExpressionEvaluator;

public class ConsoleMenu {
    private final ConsoleIO consoleIO = new ConsoleIO();
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
    private boolean running = true;

    public void start() {
        while (running) {
            int choice = consoleIO.mainMenuSelection();
            switch (choice) {
                case 1:
                    handleCaesarEncryption();
                    break;
                case 2:
                    handleCaesarDecryption();
                    break;
                case 3:
                    handleExpressionEvaluation();
                    break;
                case 4:
                    exitApplication();
                    break;
            }
            if (running) {
                boolean continueApp = consoleIO.getConfirmation("\nContinue? (y/n): ");
                if (!continueApp) {
                    exitApplication();
                }
            }
        }
        consoleIO.closeScanner();
    }

    private void handleCaesarEncryption() {
        consoleIO.displayMessage("\n--- Caesar Cipher Encryption selected ---");

        int sourceChoice = consoleIO.getSourceSelection();
        String textToEncrypt;
        if (sourceChoice == 1) {
            consoleIO.displayMessage("You selected keyboard input.");
            textToEncrypt = consoleIO.readText("Enter text to encrypt: ");
        } else {
            textToEncrypt = "Hello World";
            consoleIO.displayMessage("You selected file input. File path logic is under development.");
        }

        int shift = consoleIO.getShiftValue("Enter the shift value (integer): ");
        String resultText = caesarCipher.encrypt(textToEncrypt, shift);
        consoleIO.displayCaesarOperationResult(textToEncrypt, shift, resultText, "Caesar Cipher Encryption");
        consoleIO.displayMessage("Full logic for Caesar Cipher Encryption is under development.");
    }

    private void handleCaesarDecryption() {
        consoleIO.displayMessage("\n--- Caesar Cipher Decryption selected ---");

        int sourceChoice = consoleIO.getSourceSelection();
        String textToDecrypt;
        if (sourceChoice == 1) {
            consoleIO.displayMessage("You selected keyboard input.");
            textToDecrypt = consoleIO.readText("Enter text to encrypt: ");
        } else {
            textToDecrypt = "Hello World";
            consoleIO.displayMessage("You selected file input. File path logic is under development.");
        }

        int shift = consoleIO.getShiftValue("Enter the shift value (integer): ");
        String resultText = caesarCipher.decrypt(textToDecrypt, shift);

        consoleIO.displayCaesarOperationResult(textToDecrypt, shift, resultText, "Caesar Cipher Decryption");
        consoleIO.displayMessage("Full logic for Caesar Cipher Decryption is under development.");
    }

    private void handleExpressionEvaluation() {
        consoleIO.displayMessage("\n--- Arithmetic Expression Evaluation selected ---");

        String testExpression = "2+3*4";
        double resultValue = expressionEvaluator.evaluate(testExpression);

        consoleIO.displayMessage("Expression: " + testExpression);
        consoleIO.displayMessage("Result: " + resultValue);
        consoleIO.displayMessage("Full logic for Expression Evaluation is under development.");
    }

    private void exitApplication() {
        consoleIO.displayMessage("\nExiting application. Have a great day!\n");
        this.running = false;
    }
}