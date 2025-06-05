package ui;

import cipher.CaesarCipher;
import evaluator.ExpressionEvaluator;

public class ConsoleMenu {
    private final ConsoleIO consoleIO = new ConsoleIO();
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
    private boolean running = true;

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
                    operationCompletedSuccessfully = handleExpressionEvaluation();
                    break;
                case 4:
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

    private boolean handleExpressionEvaluation() {
        consoleIO.displayMessage("\n--- Arithmetic Expression Evaluation selected ---");

        String testExpression = "2+3*4";
        double resultValue = expressionEvaluator.evaluate(testExpression);

        consoleIO.displayMessage("Expression: " + testExpression);
        consoleIO.displayMessage("Result: " + resultValue);
        consoleIO.displayMessage("Full logic for Expression Evaluation is under development.");
        return true;
    }

    private void exitApplication() {
        consoleIO.displayMessage("\nExiting application. Have a great day!\n");
        this.running = false;
    }
}