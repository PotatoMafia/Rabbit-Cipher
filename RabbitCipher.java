public class RabbitCipher {
    String key; // String key = "1111111111111111111111111110110111111111111111111111111111011011111111111111111111111111101101111111111111111111111111110110";
    String IV;
    String[] subkeys = new String[8];
    String[] subIVEven = new String[2];
    String[] subIVOdd = new String[4];
    String[][] counter = new String[7][4];
    int[][] states = new int[7][4];
    private final int[] A = new int[]
            {
                    0x4D34D34D, 0xD34D34D3, 0x34D34D34, 0x4D34D34D,
                    0xD34D34D3, 0x34D34D34, 0x4D34D34D, 0xD34D34D3
            };

    //concatenation of two bit sequences
    public static String concatenateBitSequences(String sequence1, String sequence2) {
        return sequence1 + sequence2;
    }


    //creating subkeys
    //TODO: (@ksiepiet) Create method for subkeys
    protected void setSubKeys() {
    }

    //TODO: (@ksiepiet) Create method for x_j,0[8]
    protected void createStateVariablesJZero() {
        int[][] states = new int[7][4];
        //....
        this.states = states;
    }

    //TODO: (@ksiepiet) Create method for all states x[7][4]
    protected void createAllStates() {
        int[][] states = new int[7][4];
        //....
        this.states = states;
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
        for (int i = 0; i < counter.length; i++) {
            if (i % 2 == 0) {
                int j1 = (i + 4) % 8;
                int j2 = (i + 5) % 8;
                counter[i][0] =concatenateBitSequences(subkeys[j1], subkeys[j2]);
                //counter[i][0] =;
            } else {
                int j = (i + 1) % 8;
                counter[i][0] =concatenateBitSequences(subkeys[i], subkeys[j]);
                // counter[i][0] =;
            }
        }
        //....

    }

    //TODO: (@akuhach) Create method for full counter system c_j,i
    protected void createCounterSystem() {

        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[i].length; j++) {
                int m = j + 1;
                if (m + 1 == counter[i].length) {
                    m = 0;
                }
                //counter[i][m] =c[i][m] + A[i] + f[7][i] mod 32;
            }
        }
        //....

    }

     private void createF(){

     }
    //TODO: (@akuhach) Create method for IV setup scheme
    protected void IVSetupScheme() {
        //....
        modificationOfTheSetupScheme();
    }

    //TODO: (@akuhach) Create method for modifications
    protected void modificationOfTheSetupScheme() {
        //....
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
}
