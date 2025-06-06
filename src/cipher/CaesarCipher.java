package cipher;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@code CaesarCipher} class provides functionalities for encrypting and decrypting text
 * using the Caesar cipher algorithm. It supports both English and Russian alphabets,
 * maintaining letter case and preserving non-alphabetic characters.
 * It also includes a brute-force decryption method.
 */
public class CaesarCipher {
    private static final String ENGLISH_ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENGLISH_ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String RUSSIAN_ALPHABET_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RUSSIAN_ALPHABET_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";


    /**
     * Encrypts the given text using the Caesar cipher algorithm.
     * Each alphabetic character is shifted by the specified number of positions within its alphabet.
     * Non-alphabetic characters remain unchanged. If the input text is {@code null}, empty, or the shift is 0,
     * the original text is returned without modification.
     *
     * @param text  The input text to be encrypted.
     * @param shift The number of positions to shift each character.
     * @return The encrypted text.
     */
    public String encrypt(String text, int shift) {
        if (text == null || text.isEmpty() || shift == 0) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            // Determine the current character's language(alphabet) and its index in the alphabet
            String currentAlphabet = getAlphabet(ch);
            if (currentAlphabet != null) {
                int alphabetLength = currentAlphabet.length();
                int charIndex = currentAlphabet.indexOf(ch);
                int normalizedShift = shift % alphabetLength;
                int shiftedIndex = (charIndex + normalizedShift) % alphabetLength;
                if (shiftedIndex < 0) {
                    shiftedIndex += alphabetLength;
                }
                result.append(currentAlphabet.charAt(shiftedIndex));
            } else {
                // If the current character is not a letter, append it unchanged
                result.append(ch);
            }
        }

        return result.toString();
    }

    /**
     * Decrypts the given text using the Caesar cipher algorithm.
     * This method effectively applies the negative of the shift value used for encryption.
     *
     * @param text  The input text to be decrypted.
     * @param shift The original shift value used for encryption.
     * @return The decrypted text.
     */
    public String decrypt(String text, int shift) {
        return encrypt(text, -shift); // Apply the negative shift to reverse encryption
    }

    /**
     * Performs a brute-force decryption of the given text using the Caesar cipher algorithm.
     * It tries all possible shifts for the supported alphabets and returns a {@code Map} of unique
     * deciphered texts, each associated with the smallest positive shift that produced it.
     *
     * @param text The encrypted text to be brute-force decrypted.
     * @return A {@code Map} where keys are unique deciphered texts and values are the smallest nonnegative
     * shift values that produced them.
     */
    public Map<String, Integer> bruteForceDecrypt(String text) {
        Map<String, Integer> uniqueDecryptedOptions = new LinkedHashMap<>(); // Maintain order
        int maxShift = Math.max(ENGLISH_ALPHABET_LOWER.length(), RUSSIAN_ALPHABET_LOWER.length());
        for (int shift = 0; shift < maxShift; shift++) {
            String decryptedText = decrypt(text, shift);
            // If decryptedText is not already in the map, add it.
            // If we already have this decryptedText, no need to replace the shift value,
            // as we already stored the least possible positive shift for it.
            // uniqueDecryptedOptions.putIfAbsent(decryptedText, shift);
            if (!uniqueDecryptedOptions.containsKey(decryptedText)) {
                uniqueDecryptedOptions.put(decryptedText, shift);
            }
        }
        // No need to sort, already in sorted order (incremental shifts)
        return uniqueDecryptedOptions;
    }

    /**
     * Determines the appropriate alphabet (English uppercase/lowercase, Russian uppercase/lowercase)
     * for a given character.
     *
     * @param ch The character for which to determine the alphabet.
     * @return The string representing the alphabet (e.g., "ABCDEFGHIJKLMNOPQRSTUVWXYZ") if the character
     * is a letter from a supported alphabet; otherwise, {@code null}.
     */
    private String getAlphabet(char ch) {
        if (ENGLISH_ALPHABET_UPPER.indexOf(ch) != -1) {
            return ENGLISH_ALPHABET_UPPER;
        }
        if (ENGLISH_ALPHABET_LOWER.indexOf(ch) != -1) {
            return ENGLISH_ALPHABET_LOWER;
        }
        if (RUSSIAN_ALPHABET_UPPER.indexOf(ch) != -1) {
            return RUSSIAN_ALPHABET_UPPER;
        }
        if (RUSSIAN_ALPHABET_LOWER.indexOf(ch) != -1) {
            return RUSSIAN_ALPHABET_LOWER;
        }
        return null;
    }
}
