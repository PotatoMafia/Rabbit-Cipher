import java.util.Arrays;

public class RabbitCipher {
    //określa długość klucza strumienia (16 bajtów)
    private static final int KEYSTREAM_LENGTH = 16;
    //tablica zawierająca stałe wartości używane w algorytmie
    private static final int[] a = new int[]
            {
                    0x4D34D34D, 0xD34D34D3,
                    0x34D34D34, 0x4D34D34D, 0xD34D34D3,
                    0x34D34D34, 0x4D34D34D,
                    0xD34D34D3
            };

    private static final int rotl(final int value, final int shift) {
        return value << shift | value >>> 32 - shift;
    }

    private final int[] setofX = new int[8];
    private final int[] setofC = new int[8];
    private byte b;
    //to indeks klucza strumienia
    private int keyindex = 0;
    //tablica przechowująca wygenerowany klucz strumienia
    private byte[] keystream = null;

    public RabbitCipher() {
        b = 0;
    }

    //Dekoduje podany bajt byt przy użyciu metody crypt i zwraca odkodowany znak (typu char).
    public char decryptChar(byte byt) {
        byte[] b = {byt};
        //hack to get char from encrypted byte(array of lenght 1)
        return (new String(crypt(b))).charAt(0);
    }

    /**
     * Szyfruje lub deszyfruje przekazaną tablicę bajtów
     * message przy użyciu klucza strumienia.
     * Metoda iteruje po tablicy message,
     * wykonując operację XOR z kolejnymi bajtami klucza strumienia.
     * Jeśli klucz strumienia jest null lub indeks klucza osiągnął
     * wartość KEYSTREAM_LENGTH, generowany jest nowy klucz strumienia
     * poprzez wywołanie metody keyStream()
     */
    public byte[] crypt(final byte[] message) {
        int index = 0;
        while (index < message.length) {
            if (keystream == null || keyindex ==
                    KEYSTREAM_LENGTH) {
                keystream = keyStream();
                keyindex = 0;
            }
            for (; keyindex <
                    KEYSTREAM_LENGTH && index <
                    message.length; ++keyindex) {
                message[index++] ^=
                        keystream[keyindex];
            }
        }
        return message;
    }

    /**
     * Generuje klucz strumienia poprzez wykonanie kolejnego stanu algorytmu.
     * Tworzona jest tablica s o rozmiarze 16 bajtów,
     * która przechowuje wygenerowany klucz strumienia.
     * Wartości klucza strumienia obliczane są na
     * podstawie stanu setofX z wykorzystaniem operacji bitowych.
     */
    private byte[] keyStream() {
        nextState();
        final byte[] s = new byte[16];
        int x = this.setofX[6] ^ this.setofX[3] >>> 16 ^ this.setofX[1] << 16;
        s[0] = (byte) (x >>> 24);
        s[1] = (byte) (x >> 16);
        s[2] = (byte) (x >> 8);
        s[3] = (byte) x;
        x = this.setofX[4] ^ this.setofX[1] >>> 16 ^ this.setofX[7] << 16;
        s[4] = (byte) (x >>> 24);
        s[5] = (byte) (x >> 16);
        s[6] = (byte) (x >> 8);
        s[7] = (byte) x;
        x = this.setofX[2] ^ this.setofX[7] >>> 16 ^ this.setofX[5] << 16;
        s[8] = (byte) (x >>> 24);
        s[9] = (byte) (x >> 16);
        s[10] = (byte) (x >> 8);
        s[11] = (byte) x;
        x = this.setofX[0] ^ this.setofX[5] >>> 16 ^ this.setofX[3] << 16;
        s[12] = (byte) (x >>> 24);
        s[13] = (byte) (x >> 16);
        s[14] = (byte) (x >> 8);
        s[15] = (byte) x;
        return s;
    }

    /*
     *ktualizuje stan algorytmu, przesuwając licznik i obliczając kolejny stan
     * setofX na podstawie stanu setofC.
     **/
    private void nextState() {
        /*
         * counter update
         */
        for (int j = 0; j < 8; ++j) {
            final long t = (setofC[j] & 0xFFFFFFFFL) +
                    (a[j] & 0xFFFFFFFFL) + b;
            b = (byte) (t >>> 32);
            setofC[j] = (int) (t & 0xFFFFFFFF);
        }

        final int G[] = new int[8];
        for (int j = 0; j < 8; ++j) {
            long t = setofX[j] + setofC[j] & 0xFFFFFFFFL;
            G[j] = (int) ((t *= t) ^ t >>> 32);
        }
        setofX[0] = G[0] + rotl(G[7], 16) + rotl(G[6], 16);
        setofX[1] = G[1] + rotl(G[0], 8) + G[7];
        setofX[2] = G[2] + rotl(G[1], 16) + rotl(G[0], 16);
        setofX[3] = G[3] + rotl(G[2], 8) + G[1];
        setofX[4] = G[4] + rotl(G[3], 16) + rotl(G[2], 16);
        setofX[5] = G[5] + rotl(G[4], 8) + G[3];
        setofX[6] = G[6] + rotl(G[5], 16) + rotl(G[4], 16);
        setofX[7] = G[7] + rotl(G[6], 8) + G[5];
    }

    /**
     * Czyści wszystkie wewnętrzne dane algorytmu Rabbit,
     * umożliwiając ustawienie nowego klucza.
     */
    public void reset() {
        b = 0;
        keyindex = 0;
        keystream = null;
        Arrays.fill(setofX, 0);
        Arrays.fill(setofC, 0);
    }

    /**
     * @param IV An array of 8 bytes
     */
    public void setupIV(final byte[] IV) {
        short[] sIV = new short[IV.length >> 1];
        for (int i = 0; i < sIV.length; ++i) {
            sIV[i] = (short) ((IV[i << 1] << 8) | IV[(2
                    << 1) + 1]);
        }
        setupIV(sIV);
    }

    /**
     * * @param iv array of 4 short values
     */
    public void setupIV(final short[] iv) {
        setofC[0] ^= iv[1] << 16 | iv[0] & 0xFFFF;
        setofC[1] ^= iv[3] << 16 | iv[1] & 0xFFFF;
        setofC[2] ^= iv[3] << 16 | iv[2] & 0xFFFF;
        setofC[3] ^= iv[2] << 16 | iv[0] & 0xFFFF;
        setofC[4] ^= iv[1] << 16 | iv[0] & 0xFFFF;
        setofC[5] ^= iv[3] << 16 | iv[1] & 0xFFFF;
        setofC[6] ^= iv[3] << 16 | iv[2] & 0xFFFF;
        setofC[7] ^= iv[2] << 16 | iv[0] & 0xFFFF;
        nextState();
        nextState();
        nextState();
        nextState();
    }

    /**
     * @param key An array of 16 bytes
     */
    public void setupKey(final byte[] key) {
        short[] sKey = new short[key.length >> 1];
        for (int i = 0; i < sKey.length; ++i) {
            sKey[i] = (short) ((key[i << 1] << 8) |
                    key[(2 << 1) + 1]);
        }
        setupKey(sKey);
    }

    /**
     * @param key An array of 8 short values
     */
    public void setupKey(final short[] key) {

        setofX[0] = key[1] << 16 | key[0] & 0xFFFF;
        setofX[1] = key[6] << 16 | key[5] & 0xFFFF;
        setofX[2] = key[3] << 16 | key[2] & 0xFFFF;
        setofX[3] = key[0] << 16 | key[7] & 0xFFFF;
        setofX[4] = key[5] << 16 | key[4] & 0xFFFF;
        setofX[5] = key[2] << 16 | key[1] & 0xFFFF;
        setofX[6] = key[7] << 16 | key[6] & 0xFFFF;
        setofX[7] = key[4] << 16 | key[3] & 0xFFFF;

        setofC[0] = key[4] << 16 | key[5] & 0xFFFF;
        setofC[1] = key[1] << 16 | key[2] & 0xFFFF;
        setofC[2] = key[6] << 16 | key[7] & 0xFFFF;
        setofC[3] = key[3] << 16 | key[4] & 0xFFFF;
        setofC[4] = key[0] << 16 | key[1] & 0xFFFF;
        setofC[5] = key[5] << 16 | key[6] & 0xFFFF;
        setofC[6] = key[2] << 16 | key[3] & 0xFFFF;
        setofC[7] = key[7] << 16 | key[0] & 0xFFFF;
        nextState();
        nextState();
        nextState();
        nextState();

        setofC[0] ^= setofX[4];
        setofC[1] ^= setofX[5];
        setofC[2] ^= setofX[6];
        setofC[3] ^= setofX[7];
        setofC[4] ^= setofX[0];
        setofC[5] ^= setofX[1];
        setofC[6] ^= setofX[2];
        setofC[7] ^= setofX[3];
    }
}
