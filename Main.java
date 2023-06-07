import java.util.Arrays;



public class Main {
    public static void main(String[] args) {
        String inputString = "Hello, World!";
        String key = "mysecretkey12345";
        byte[] iv = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

        RabbitCipher rabbitCipher = new RabbitCipher();

        // Set up the key and IV
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);

        // Encrypt the string
        byte[] inputBytes = inputString.getBytes();
        byte[] encryptedBytes = rabbitCipher.crypt(inputBytes);

        // Print the encrypted bytes
        System.out.println(Arrays.toString(encryptedBytes));

        // Decrypt the bytes
        rabbitCipher.reset();
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);

        byte[] decryptedBytes = rabbitCipher.crypt(encryptedBytes);

        // Print the decrypted string
        String decryptedString = new String(decryptedBytes);
        System.out.println(decryptedString);
    }


}
