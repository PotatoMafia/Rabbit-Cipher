public class Main {
    public static void main(String[] args) {
        String key = "1111111111111111111111111110110111111111111111111111111111011011111111111111111111111111101101111111111111111111111111110110";
        String IV = "1111111111111111111111111110110111111111111111111111111111011011111111111111111111111111101101111111111111111111111111110110";
        RabbitCipher rabbitCipher = new RabbitCipher(key,IV);
        String test1 = "abc";
//        byte[] t = test1.getBytes();
//        for(byte p : t){
//            System.out.println(p);
//        }
    }
}
