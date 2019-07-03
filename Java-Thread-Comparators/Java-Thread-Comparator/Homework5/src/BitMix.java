import java.util.Comparator;

public class BitMix implements Comparator<image_Pixel> {

    public BitMix() {

    }

    @Override
    public int compare(image_Pixel o1, image_Pixel o2) {
        String s1_red = intToBinary(o1.getRed());
        String s1_green = intToBinary(o1.getGreen());
        String s1_blue = intToBinary(o1.getBlue());
        String s2_red = intToBinary(o2.getRed());
        String s2_green = intToBinary(o2.getGreen());
        String s2_blue = intToBinary(o2.getBlue());

        char[] cmp1 = new char[25];
        char[] cmp2 = new char[25];
        int a = 0;
        int b = 0;
        for (int i = 7; i >=0 ; i--) {
            cmp1[a++] = s1_red.toCharArray()[i];
            cmp1[a++] = s1_green.toCharArray()[i];
            cmp1[a++] = s1_blue.toCharArray()[i];

            cmp2[b++] = s2_red.toCharArray()[i];
            cmp2[b++] = s2_green.toCharArray()[i];
            cmp2[b++] = s2_blue.toCharArray()[i];

        }
        cmp1[a] = '\0';
        cmp2[b] = '\0';

        String str1 = new String(cmp1);
        String str2 = new String(cmp2);

        int int1 = intToBin(str1);
        int int2 = intToBin(str2);

        if(int1 - int2 > 0) {
            return 1;
        }
        else if( int1 - int2 == 0){
            return 0;
        }
        else if(int1 - int2 < 0){
            return -1;
        }
        return 0;
    }

    public String intToBinary(Integer x){
        String binary = Integer.toBinaryString(x);
        String output = String.format("%8s",binary).replace(' ','0');
        return output;
    }

    public static int intToBin(String str){
        double j=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)== '1'){
                j=j+ Math.pow(2,str.length()-1-i);
            }

        }
        return (int) j;
    }
}
