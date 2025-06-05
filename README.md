# Gehtsoft Technical Assessment
## Console Application: Caesar Cipher & Expression Evaluator

This project is a console-based application developed as a technical assessment. 
It features a main menu allowing users to interact with various functionalities, 
including Caesar Cipher encryption/decryption and arithmetic expression evaluation. 
The application is built with a focus on modularity, clear separation of concerns, and robust user interaction.


## Contents
* [Features](#features)
* [Project Structure](#project-structure)
* [Requirements](#requirements)
* [How to Run](#how-to-run)
* [Future Enhancements](#future-enhancements)

## Features

Currently Implemented:

* **Interactive Console Menu (`ConsoleMenu`):**
    * Presents a clear list of available options:
        <ol type="1">
            <li>Caesar Cipher Encryption</li>
            <li>Caesar Cipher Decryption (known shift)</li>
            <li>Caesar Cipher Brute-force Decryption (unknown shift)</li>
            <li>Arithmetic Expression Evaluation</li>
            <li>Exit</li>
        </ol>
   * Handles user input, ensuring only valid integer selections within the specified range are accepted.
   * Provides clear error messages for invalid input.
   * Post-Operation Flow: After completing an operation (Encryption, Decryption, or Evaluation), or if an operation is cancelled (e.g., due to an empty file), the application's flow is managed cleanly, prompting the user to choose whether to continue using the application or exit. If they decide to continue, the main menu is displayed again.
   * Graceful Exit: Users can exit the application directly by selecting option `5. Exit` from the main menu.
   * Manages the lifecycle of the `Scanner` resource, ensuring it is properly closed when the application terminates.

* **Console Input/Output Handler (`ConsoleIO`):**
    * Dedicated class for all console-related operations (displaying menus, messages, reading user input).
    * Encapsulates `System.in` and `System.out` interactions, promoting clean separation between UI and business logic.
    * Includes a method (`getConfirmation`) to prompt users whether they wish to continue after an operation. 
    * File Input Capability: supports reading input text for operations directly from a specified file path. 
    * Robust Empty File Handling: If a user attempts to load an empty file, the application issues a warning and prompts the user to confirm whether they still wish to proceed with an empty input.


* **Caesar Cipher (`CaesarCipher`):**
    * Dedicated class for Caesar Cipher operations.
    * Contains `encrypt(String text, int shift)`, `decrypt(String text, int shift)`, and `bruteForceDecrypt(String text)` methods.
    * The application fully supports Caesar cipher encryption and decryption for text entered directly via the keyboard or loaded from a file. Users can specify a shift value and get the encrypted or decrypted result. Both English and Russian alphabets are supported, maintaining letter case and preserving non-alphabetic characters. 
    * Decryption without a known shift value: Implements a brute-force approach to decrypt Caesar Cipher texts by trying all possible shift combinations. It provides unique deciphered options, associating each with its smallest positive shift value for clarity.

* **Arithmetic Expression Evaluator Placeholder (`ExpressionEvaluator`):**
    * Dedicated class for arithmetic expression evaluation.
    * Contains an `evaluate(String expression)` method.
    * **Current Status:** These method is currently defined but not fully implemented. The evaluation logic will be added in future versions.

<p align="right"><a href="#contents">Navigate to Contents</a></p>

##  Project Structure

The project follows a modular package structure:

* `src`: Main source directory.
    * `ui`: Contains classes related to user interface and console interaction (`ConsoleMenu`, `ConsoleIO`).
    * `cipher`: Contains classes related to cryptographic operations (`CaesarCipher`).
    * `evaluator`: Contains classes related to arithmetic expression evaluation (`ExpressionEvaluator`).
  
<p align="right"><a href="#contents">Navigate to Contents</a></p>
  
## Requirements

* **Java Development Kit (JDK) 17 or newer**. The project was developed using JDK 17.

<p align="right"><a href="#contents">Navigate to Contents</a></p>

## How to Run

This application can be run directly from an IDE like IntelliJ IDEA or compiled and executed from the command line.

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/ElenaByc/GehtsoftJavaAssessment.git
    ```
    Navigate into the cloned repository directory if you are not already there

2.  **Using an IDE (e.g., IntelliJ IDEA):**
    * Open the project in your IDE.
    * Locate and run the `Main.java`.

3.  **From the Command Line:**
    * Navigate to the root of the project.
    * Compile the source files:
    ```bash
    javac -d out src/ui/*.java src/cipher/*.java src/evaluator/*.java src/Main.java
    ``` 
    (This command compiles all `.java` files into an `out` directory)

    * Run the application:
    ```bash
    java -cp out Main
    ```

<p align="right"><a href="#contents">Navigate to Contents</a></p>

## Future Enhancements

* **Robust Expression Evaluation:** Implement a parser and evaluator for arithmetic expressions, handling various operators, parentheses, and error cases.
* **Error Handling:** Enhance error handling for file operations and invalid expressions.
* **Unit Testing:** Implement unit tests for the `CaesarCipher` (encryption/decryption) and `ExpressionEvaluator` components

<p align="right"><a href="#contents">Navigate to Contents</a></p>