package cipher;

public class CaesarCipher {

    public String encrypt(String text, int shift) {
        System.out.println("DEBUG: Encrypting '" + text + "' with shift " + shift);
        return text;
    }

    public String decrypt(String text, int shift) {
        System.out.println("DEBUG: Decrypting '" + text + "' with shift " + shift);
        return text;
    }
}
