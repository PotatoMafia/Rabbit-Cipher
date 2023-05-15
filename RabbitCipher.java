public class RabbitCipher {
    int[] key = new int[128];
    int[] IV = new int[64];
    int[][] subkeys = new int[8][16];
    int[][] subIVEven = new int[4][32];
    int[][] subIVOdd = new int[4][16];
    int[][] counter = new int[7][4];
    int[][] states = new int[7][4];
    private final int[] A = new int[]
            {
                    0x4D34D34D, 0xD34D34D3, 0x34D34D34, 0x4D34D34D,
                    0xD34D34D3, 0x34D34D34, 0x4D34D34D, 0xD34D34D3
            };

    //creating subkeys
    //TODO: (@ksiepiet) Create method for subkeys
    protected void setSubKeys() {
        int[][] subkeys = new int[8][16];
        //....
        this.subkeys = subkeys;
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
        int[][] subIVEven = new int[4][32];
        int[][] subIVOdd = new int[4][16];
        //....
        this.subIVEven = subIVEven;
        this.subIVOdd = subIVOdd;
    }

    //TODO: (@akuhach) Create method for c_j,0[8]
    protected void createCounterVariablesJZero() {
        for (int i = 0; i < counter.length; i++) {
            if (i % 2 == 0) {
                //counter[i][0] =;
            } else {
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
