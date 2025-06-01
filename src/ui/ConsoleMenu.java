package ui;

public class ConsoleMenu {
    private final UserInputHandler userInputHandler = new UserInputHandler();

    public void start() {
        boolean running = true;
        while (running) {
            int choice = userInputHandler.mainMenuSelection();
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
                    System.out.println("\nProgram exiting. Have a great day!\n");
                    running = false;
                    break;
                default:
                    userInputHandler.printInvalidSelectionMessage();
                    break;
            }
        }
    }
}