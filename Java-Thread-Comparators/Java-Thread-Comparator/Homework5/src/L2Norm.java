import java.util.Comparator;

public class L2Norm implements Comparator<image_Pixel> {

    public L2Norm(){

    }
    @Override
    public int compare(image_Pixel o1, image_Pixel o2) {
        double temp1 = Math.sqrt(Math.pow(o1.getRed(),2)+Math.pow(o1.getGreen(),2)+Math.pow(o1.getBlue(),2));
        double temp2 = Math.sqrt(Math.pow(o2.getRed(),2)+Math.pow(o2.getGreen(),2)+Math.pow(o2.getBlue(),2));

        if(temp1 - temp2 > 0)
            return 1;
        else if( temp1 - temp2 == 0){
            return 0;
        }
        else {
            return -1;
        }
    }
}
