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

        String testText = "Hello World";
        int testShift = 3;
        String resultText = caesarCipher.encrypt(testText, testShift);

        consoleIO.displayMessage("Input Text: " + testText);
        consoleIO.displayMessage("Shift Value: " + testShift);
        consoleIO.displayMessage("Result: " + resultText);
        consoleIO.displayMessage("Full logic for Caesar Cipher Encryption is under development.");
    }

    private void handleCaesarDecryption() {
        consoleIO.displayMessage("\n--- Caesar Cipher Decryption selected ---");

        String testText = "Khoor Zruog";
        int testShift = 3;
        String resultText = caesarCipher.decrypt(testText, testShift);

        consoleIO.displayMessage("Input Text: " + testText);
        consoleIO.displayMessage("Shift Value: " + testShift);
        consoleIO.displayMessage("Result: " + resultText);
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