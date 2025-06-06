import cipher.CaesarCipher;

public class CaesarCipherTest {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        String inputText;
        int shift;

        // English text encryption test
        inputText = "Hello World";
        shift = 3;
        runTest(
                "English text encryption with shift = " + shift,
                inputText,
                "Khoor Zruog",
                cipher.encrypt(inputText, shift)
        );

        // Russian text encryption test
        inputText = "Привет Мир";
        shift = 5;
        runTest(
                "Russian text encryption with shift = " + shift,
                inputText,
                "Фхнжйч Снх", // is there a mistake in the assignment instructions? ("Хумёзй Рну")
                cipher.encrypt(inputText, shift)
        );

        // English text decryption test
        inputText = "Khoor Zruog";
        shift = 3;
        runTest(
                "English text decryption with shift = " + shift,
                inputText,
                "Hello World",
                cipher.decrypt(inputText, shift)
        );

        // Mixed English-Russian text encryption
        inputText = "Hello Привет";
        shift = 4;
        runTest(
                "Mixed English-Russian encryption with shift = " + shift,
                inputText,
                "Lipps Уфмёиц",
                cipher.encrypt(inputText, shift)
        );

        // Mixed English-Russian text decryption
        inputText = "Lipps Уфмёиц";
        shift = 4;
        runTest(
                "Mixed English-Russian decryption with shift = " + shift,
                inputText,
                "Hello Привет",
                cipher.decrypt(inputText, shift)
        );

        // Negative shift encryption test
        inputText = "Hello";
        shift = -2;
        runTest(
                "English text encryption with negative shift = " + shift,
                inputText,
                "Fcjjm",
                cipher.encrypt(inputText, shift)
        );

        // Negative shift decryption test
        inputText = "Fcjjm";
        shift = -2;
        runTest(
                "English text decryption with negative shift = " + shift,
                inputText,
                "Hello",
                cipher.decrypt(inputText, shift)
        );

        // Large shift (>33) encryption test
        inputText = "Hello";
        shift = 40;
        runTest(
                "English text encryption with large shift = " + shift,
                inputText,
                "Vszzc",
                cipher.encrypt(inputText, shift)
        );

        // Large shift (>33) decryption test
        inputText = "Vszzc";
        shift = 40;
        runTest(
                "English text decryption with large shift = " + shift,
                inputText,
                "Hello",
                cipher.decrypt(inputText, shift)
        );

        // Empty string encryption test
        inputText = "";
        shift = 5;
        runTest(
                "Encryption with empty string",
                inputText,
                "",
                cipher.encrypt(inputText, shift)
        );

        // Empty string decryption test
        inputText = "";
        shift = 5;
        runTest(
                "Decryption with empty string",
                inputText,
                "",
                cipher.decrypt(inputText, shift)
        );

        // Brute-force decryption test
        String bruteForceText = "Khoor";
        var bruteForceResults = cipher.bruteForceDecrypt(bruteForceText);
        boolean bruteForceActual = bruteForceResults.containsKey("Hello");

        runTest(
                "Brute-force decryption results contains correct result 'Hello'",
                bruteForceText,
                true,
                bruteForceActual
        );
    }

    private static void runTest(String testName, String input, Object expected, Object actual) {
        if (!expected.equals(actual)) {
            System.err.printf("❌ Test Failed: %s%n", testName);
            System.err.printf("   Input:    %s%n", input);
            System.err.printf("   Expected: %s%n", expected);
            System.err.printf("   Actual:   %s%n%n", actual);
        } else {
            System.out.printf("✅ Test Passed: %s%n", testName);
        }
    }
}