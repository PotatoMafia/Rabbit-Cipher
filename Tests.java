public class Tests {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {

        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1(){
        System.out.println(ANSI_BLUE + "Test1: without changing the encrypted message" + ANSI_RESET);
        String inputString = "Hello, World!";
        System.out.println("Plain Text: " + inputString + "\n Hexadecimal:" + stringToHex(inputString));
        String key = "mysecretkey12345";
        System.out.println("Key: " + key + "\n Hexadecimal:" + stringToHex(key));
        byte[] iv = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
        System.out.println("IV(Hexadecimal): " + bytesToHex(iv));
        RabbitCipher rabbitCipher = new RabbitCipher();
        // Set up the key and IV
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        // Encrypt the string
        byte[] inputBytes = inputString.getBytes();
        byte[] encryptedBytes = rabbitCipher.crypt(inputBytes);
        // Print the encrypted bytes
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        // Decrypt the bytes
        rabbitCipher.reset();
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        byte[] decryptedBytes = rabbitCipher.crypt(encryptedBytes);
        // Print the decrypted string
        String decryptedString = new String(decryptedBytes);
        System.out.println("Decrypted text matches plain text: \t" + decryptedString.equals(inputString));
        System.out.println("Decrypted text: " + decryptedString);
    }
    //add  a change in the last symbol
    public static void test2() {
//        String inputString = "AAA";
        System.out.println("\n" + ANSI_BLUE + "Test2: with changing the encrypted message" + ANSI_RESET);
        String inputString = "OneTwoThree";
        System.out.println("Plain Text: " + inputString + "\n Hexadecimal:" + stringToHex(inputString));
        String key = "mysecretkey12345";
        System.out.println("Key: " + key + "\n Hexadecimal:" + stringToHex(key));
        byte[] iv = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
        System.out.println("IV(Hexadecimal): " + bytesToHex(iv));
        RabbitCipher rabbitCipher = new RabbitCipher();
        // Set up the key and IV
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        // Encrypt the string
        byte[] inputBytes = inputString.getBytes();
        byte[] encryptedBytes = rabbitCipher.crypt(inputBytes);
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        System.out.println(ANSI_RED + "\t\tChange the last symbol" + ANSI_RESET);
        encryptedBytes = modifyLastElement(encryptedBytes, (byte) 52);
        // Print the encrypted bytes
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        // Decrypt the bytes
        rabbitCipher.reset();
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        byte[] decryptedBytes = rabbitCipher.crypt(encryptedBytes);
        // Print the decrypted string
        String decryptedString = new String(decryptedBytes);
        System.out.println("Decrypted text matches plain text: \t" + decryptedString.equals(inputString));
        System.out.println("Decrypted text: " + decryptedString);
    }

    public static void test3() {
//        String inputString = "AAA";
        System.out.println("\n" + ANSI_BLUE + "Test3: with changing the encrypted message" + ANSI_RESET);
        String inputString = "OneTwoThree";
        System.out.println("Plain Text: " + inputString + "\n Hexadecimal:" + stringToHex(inputString));
        String key = "mysecretkey12345";
        System.out.println("Key: " + key + "\n Hexadecimal:" + stringToHex(key));
        byte[] iv = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
        System.out.println("IV(Hexadecimal): " + bytesToHex(iv));
        RabbitCipher rabbitCipher = new RabbitCipher();
        // Set up the key and IV
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        // Encrypt the string
        byte[] inputBytes = inputString.getBytes();
        byte[] encryptedBytes = rabbitCipher.crypt(inputBytes);
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        System.out.println(ANSI_RED + "\t\tChange the middle symbol" + ANSI_RESET);
        encryptedBytes = modifyMiddleElement(encryptedBytes, (byte) 03);
        // Print the encrypted bytes
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        // Decrypt the bytes
        rabbitCipher.reset();
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        byte[] decryptedBytes = rabbitCipher.crypt(encryptedBytes);
        // Print the decrypted string
        String decryptedString = new String(decryptedBytes);
        System.out.println("Decrypted text matches plain text: \t" + decryptedString.equals(inputString));
        System.out.println("Decrypted text: " + decryptedString);
    }

    public static void test4() {
//        String inputString = "AAA";
        System.out.println("\n" + ANSI_BLUE + "Test4: with changing the encrypted message" + ANSI_RESET);
        String inputString = "OneTwoThree";
        System.out.println("Plain Text: " + inputString + "\n Hexadecimal:" + stringToHex(inputString));
        String key = "mysecretkey12345";
        System.out.println("Key: " + key + "\n Hexadecimal:" + stringToHex(key));
        byte[] iv = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
        System.out.println("IV(Hexadecimal): " + bytesToHex(iv));
        RabbitCipher rabbitCipher = new RabbitCipher();
        // Set up the key and IV
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        // Encrypt the string
        byte[] inputBytes = inputString.getBytes();
        byte[] encryptedBytes = rabbitCipher.crypt(inputBytes);
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        System.out.println(ANSI_RED + "\t\tChange the first symbol" + ANSI_RESET);
        encryptedBytes = modifyFirstElement(encryptedBytes, (byte) 10);
        // Print the encrypted bytes
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        // Decrypt the bytes
        rabbitCipher.reset();
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        byte[] decryptedBytes = rabbitCipher.crypt(encryptedBytes);
        // Print the decrypted string
        String decryptedString = new String(decryptedBytes);
        System.out.println("Decrypted text matches plain text: \t" + decryptedString.equals(inputString));
        System.out.println("Decrypted text: " + decryptedString);
    }

    public static void test5() {
//        String inputString = "AAA";
        System.out.println("\n" + ANSI_BLUE + "Test5: with changing the encrypted message" + ANSI_RESET);
        String inputString = "OneTwoThree";
        System.out.println("Plain Text: " + inputString + "\n Hexadecimal:" + stringToHex(inputString));
        String key = "mysecretkey12345";
        System.out.println("Key: " + key + "\n Hexadecimal:" + stringToHex(key));
        byte[] iv = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
        System.out.println("IV(Hexadecimal): " + bytesToHex(iv));
        RabbitCipher rabbitCipher = new RabbitCipher();
        // Set up the key and IV
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        // Encrypt the string
        byte[] inputBytes = inputString.getBytes();
        byte[] encryptedBytes = rabbitCipher.crypt(inputBytes);
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        System.out.println(ANSI_RED + "\t\tChange three symbols" + ANSI_RESET);
        encryptedBytes = modifyMiddleElement(encryptedBytes, (byte) 03);
        encryptedBytes = modifyFirstElement(encryptedBytes,(byte)76);
        encryptedBytes = modifyLastElement(encryptedBytes,(byte)76);
        // Print the encrypted bytes
        System.out.println("Cipher Text(Hexadecimal): " + bytesToHex(encryptedBytes));
        // Decrypt the bytes
        rabbitCipher.reset();
        rabbitCipher.setupKey(key.getBytes());
        rabbitCipher.setupIV(iv);
        byte[] decryptedBytes = rabbitCipher.crypt(encryptedBytes);
        // Print the decrypted string
        String decryptedString = new String(decryptedBytes);
        System.out.println("Decrypted text matches plain text: \t" + decryptedString.equals(inputString));
        System.out.println("Decrypted text: " + decryptedString);
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    private static byte[] modifyLastElement(byte[] array, byte newValue) {
        if (array.length > 0) {
            array[array.length - 1] = newValue;
        }
        return array;
    }

    private static byte[] modifyFirstElement(byte[] array, byte newValue) {
        if (array.length > 0) {
            array[0] = newValue;
        }
        return array;
    }

    private static byte[] modifyMiddleElement(byte[] array, byte newValue) {
        if (array.length > 0) {
            for(int i = 0; i < array.length; i++){
                if(i == array.length/2){
                    array[i] = newValue;
                }
            }
        }
        return array;
    }

        private static String stringToHex(String str) {
            StringBuilder sb = new StringBuilder();
            for (char c : str.toCharArray()) {
                sb.append(String.format("%02X", (int) c));
            }
            return sb.toString();
        }




}
