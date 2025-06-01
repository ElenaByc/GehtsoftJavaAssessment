package ui;

public class ConsoleMenu {
    private final ConsoleIO consoleIO = new ConsoleIO();

    public void start() {
        boolean running = true;
        while (running) {
            int choice = consoleIO.mainMenuSelection();
            switch (choice) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    consoleIO.displayMessage("\nProgram exiting. Have a great day!\n");
                    running = false;
                    break;
                default:
                    consoleIO. displayInvalidSelectionMessage();
                    break;
            }
        }
        consoleIO.closeScanner();
    }
}