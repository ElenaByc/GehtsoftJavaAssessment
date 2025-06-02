package cipher;

public class CaesarCipher {
    private static final String ENGLISH_ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENGLISH_ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String RUSSIAN_ALPHABET_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RUSSIAN_ALPHABET_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";


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

    public String decrypt(String text, int shift) {
        return encrypt(text, -shift); // Apply the negative shift to reverse encryption
    }

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
