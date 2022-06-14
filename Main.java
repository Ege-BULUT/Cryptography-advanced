import java.util.*;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    private static ArrayList<Character> characters = new ArrayList<>(Arrays.asList(
            '"', '1', 'A', '2', '<', 'a', 'I', 'q', '5', 'ı', '4', 'U', '3', 'w', 's', 'Y', 'x', 'c', 'd', 'e', 'Z', 'é',
            '!', 'E', '\'', 'Ü', '^', 'B', '+', '%', 'O', '&', '/', 'v', 'T', 'r', 'f', 'b', 'V', 't', 'g', 'n', 'y', 'h',
            'H', 'm', 'D', 'u', 'j', '(', 'Ş', ')', 'İ', 'K', '=', '?', 'J' ,'_', 'S', '-', '6', 'Ö', '*', '7', '0', '8', '9', 'k',
            'N', 'l', 'i', 'C', 'p', 'F', 'o', '.', 'M', ',', '@', '|', 'R', '`', 'W', '\\', '>', 'Q', '£', '#', '$', 'X', '½',
            '{', '[', 'G', ']', '}', 'Ğ', 'L', 'ö', 'ç', 'P', 'ğ', 'ü'));

    private static int SIZE = characters.size();

    public static void main(String[] args) {
        System.out.println("\n\n\t*** Password App ***\n\n");
        start();
    }

    private static void start(){

        System.out.printf("\n\n####\n1. Encrypt(with random or Custom key)\n2. Decrypt\n####\n");

        switch (scan.nextLine().charAt(0)){
            case '1':
                encrypt();
                break;
            case '2':
                decrypt();
                break;
            default:
                start();
                break;
        }



    }

    private static void encrypt(){
        boolean CustomKey = false;
        ArrayList<Integer> nums = new ArrayList<>();

        System.out.println("\n1. Generated Key\n2. Custom Key\n3. Geri");
        switch (scan.nextLine().charAt(0)){
            case '1' :
                CustomKey = false;
                break;
            case '2' :
                CustomKey = true;
                break;
            default:
                start();
                break;
        }

        if(CustomKey){
            System.out.printf("Lütfen Custom Key'inizi giriniz ('_' karakteri ile ayrılmış olacak şekilde [0-104] aralığındaki kadar sayılar.)(0 ve 104 dahil)\n");
            String[] keyRaw = scan.nextLine().split("_");
            for (int i = 0; i < keyRaw.length; i++) {
                nums.add(Integer.parseInt(keyRaw[i]));
            }
        }
        if(!CustomKey) {
            for (int i = 0; i < SIZE; i++) {
                int pos = (int) (Math.random() * nums.size());
                nums.add(pos, i);
            }
        }

        System.out.println("Kolay hatırlayabileceğin minimum 3 kelimeden oluşan boşluk içermeyen bir metin gir : ");
        String raw = scan.nextLine();
        String[] rawArr = raw.split("");

        for(int a = 0; a<SIZE; a++){
            for(int b = 0; b<SIZE; b++){
                if(nums.get(a) == nums.get(b) && a != b){
                    System.out.println("\n#### \nKEY'de Tekrar var\n"+a+"("+nums.get(a)+") == "+b+"("+nums.get(b)+") \n####\n");
                    encrypt();
                }
            }
        } // Tekrar eden sayı kontrolü

        if(!CustomKey) {
            System.out.println("\n\n(Lütfen 'KEY'inizi kaydedin, şifrenizi görüp hatırlamanız için girmeniz gerekecek.)");
            System.out.println("KEY : ");
            for (int i : nums) {
                System.out.print(i);
                if (i != nums.get(nums.size() - 1)) {
                    System.out.print("_");
                }
            }
        }

        String out = "";
        for(int i = 0; i<raw.length(); i++){
            for(char c : characters){
                if(rawArr[i].charAt(0) == c){
                   out += characters.get(nums.indexOf(characters.indexOf(c)));
                    //System.out.println(c+" ("+characters.indexOf(c)+") ("+nums.indexOf(characters.indexOf(c))+")");
                }
            }
        }

        System.out.print("\n\nRaw :\n"+raw+"\n\nEncrypted with Key :\n"+out);

        start();

    }

    private static void decrypt(){

        System.out.println("KEY'inizi girin :");
        String[] KEYstr = scan.nextLine().split("_");

        System.out.println("\nŞifrenizi Girin :");
        String[] rawArr = scan.nextLine().split("");

        ArrayList<Integer> KEYint = new ArrayList<>();
        for(int i = 0; i<KEYstr.length; i++){
            KEYint.add(Integer.parseInt(KEYstr[i]));
        }

        String out = "";
        for(int i = 0; i<rawArr.length; i++){
            for(char c : characters){
                if(rawArr[i].charAt(0) == c){
                    out += characters.get(KEYint.get(characters.indexOf(c)));
                   //System.out.println(c+" ("+characters.indexOf(c)+")("+KEYint.indexOf(characters.indexOf(c))+")");
                }
            }
        }

        System.out.println("\n\nKEY :");
        for(String s : KEYstr){
            System.out.print(s);
            if(!s.equals(KEYstr[KEYstr.length-1])){
                System.out.print("_");
            }
        }
        System.out.println("\nRaw :");
        for(String s : rawArr){
            System.out.print(s);
        }
        System.out.print("\nOut :\n"+out);

        start();
    }
}
