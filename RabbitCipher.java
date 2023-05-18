import java.math.BigInteger;

public class RabbitCipher {
    String key; // String key = "1111111111111111111111111110110111111111111111111111111111011011111111111111111111111111101101111111111111111111111111110110";
    String IV;
    String[] subkeys = new String[8];
    String[] subIVEven = new String[2];
    String[] subIVOdd = new String[4];
    String[][] counters = new String[7][4];
    String[][] states = new String[7][4];
    private final BigInteger[] a = {
            new BigInteger("4D34D34D", 16), new BigInteger("D34D34D3", 16),
            new BigInteger("34D34D34", 16), new BigInteger("4D34D34D", 16),
            new BigInteger("D34D34D3", 16), new BigInteger("34D34D34", 16),
            new BigInteger("4D34D34D", 16), new BigInteger("D34D34D3", 16)
    };

    private static final int[] f = new int[8];
    private static final BigInteger MOD = new BigInteger("2").pow(32);
    RabbitCipher(String key, String IV){
        this.key = key;
        this.IV = IV;
    }

    //concatenation of two bit sequences
    public static String concatenateBitSequences(String sequence1, String sequence2) {
        return sequence1 + sequence2;
    }

    private String xorStrings(String s1, String s2) {
        if (s1.length() != s2.length()) {
            throw new IllegalArgumentException("Strings must be the same length");
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != '0' && c1 != '1' || c2 != '0' && c2 != '1') {
                throw new IllegalArgumentException("Strings must have '0' or '1'");
            }
            result.append(c1 == c2 ? '0' : '1');
        }

        return result.toString();
    }
    //creating subkeys
    //TODO: (@ksiepiet) Create method for subkeys
    protected void setSubKeys() {
    }

    //TODO: (@ksiepiet) Create method for x_j,0[8]
    protected void createStateVariablesJZero() {
    }

    //TODO: (@ksiepiet) Create method for all states x[7][4]
    protected void createAllStates() {

    }


    //creating subIV
    //TODO: (@akuhach) Create method for subIV
    protected void setSubIV() {
        subIVEven[0] = Integer.toBinaryString(Integer.parseInt(IV.substring(0, 32), 2));
        subIVEven[1] = Integer.toBinaryString(Integer.parseInt(IV.substring(32, 64), 2));
        subIVOdd[0] = Integer.toBinaryString(Integer.parseInt(IV.substring(0, 16), 2));
        subIVOdd[1] = Integer.toBinaryString(Integer.parseInt(IV.substring(16, 32), 2));
        subIVOdd[2] = Integer.toBinaryString(Integer.parseInt(IV.substring(32, 48), 2));
        subIVOdd[3] = Integer.toBinaryString(Integer.parseInt(IV.substring(48, 64), 2));

    }

    //TODO: (@akuhach) Create method for c_j,0[8]
    protected void createCounterVariablesJZero() {
        for (int i = 0; i < counters.length; i++) {
            if (i % 2 == 0) {
                int j1 = (i + 4) % 8;
                int j2 = (i + 5) % 8;
                counters[i][0] =concatenateBitSequences(subkeys[j1], subkeys[j2]);
                //counter[i][0] =;
            } else {
                int j = (i + 1) % 8;
                counters[i][0] =concatenateBitSequences(subkeys[i], subkeys[j]);
                // counter[i][0] =;
            }
        }
        //....

    }

    //TODO: (@akuhach) Create method for full counter system c_j,i
    protected void createCounterSystem() {
        for (int i = 0; i < counters.length; i++) {
            for (int j = 0; j < counters[i].length; j++) {
                        BigInteger counter = new BigInteger(counters[j][i], 2);
                        BigInteger newValue = counter.add(a[j]).add(BigInteger.valueOf(f[(j+7)%8]));
                counters[j][i+1] = String.format("%32s", newValue.mod(MOD).toString(2)).replace(' ', '0');
                        f[j] = newValue.compareTo(MOD) >= 0 ? 1 : 0;
                    }
                }
                // Display counter values
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 4; j++) {
                        System.out.print(counters[i][j] + " ");
                    }
                    System.out.println();
                }
        }

        //....

    //TODO: (@akuhach) Create method for IV setup scheme
    protected void IVSetupScheme() {
        //....
        modificationOfTheSetupScheme();
    }

    //TODO: (@akuhach) Create method for modifications
    protected void modificationOfTheSetupScheme() {
        remakeOfCounterVariables();
        createCounterSystem();
    }

    private void remakeOfCounterVariables() {
        for(int i = 0; i < counters.length; i++){
            int j = (i+4) % 8;
            counters[i][4] = xorStrings(counters[i][4], states[j][4]);
        }
    }

    //TODO: Create extraction function
    protected void extractionFunction() {

    }

    //TODO: Create encryption function
    protected void encryptionFunction() {

    }

    //TODO: Create decryption function
    protected void decryptionFunction() {

    }

    protected void run(){

    }
}
