import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Thread1 implements Runnable{
    BufferedImage image;
    private String fileName;
    public Thread1(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            File input = new File(fileName);
            image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            int count = 0;

            Thread2 th2 = new Thread2((width*height));
            Thread3 th3 = new Thread3((width*height));
            Thread4 th4 = new Thread4((width*height));

            Thread t2 = new Thread(th2);
            Thread t3 = new Thread(th3);
            Thread t4 = new Thread(th4);

            int i = 0;
            int j = 0;

            for(i=0; i<height; i++) {

                for(j=0; j<width; j++) {

                    if(count == 100 ) {
                        System.out.println("//... Inserting all the way to at least first 100 pixels..");
                        t2.start();
                        t3.start();
                        t4.start();
                    }

                    Color c = new Color(image.getRGB(j, i));
                    image_Pixel Pixel = new image_Pixel(c.getRed(),c.getGreen(),c.getBlue());

                    System.out.println("Thread 1: " + "[" + c.getRed() + " " + c.getGreen() + " " + c.getBlue() + "]" + " count : " + count);

                    synchronized (th2.getQueue()) {
                        th2.add(Pixel);
                        th2.getQueue().notifyAll();
                    }
                    synchronized (th3.getQueue()){
                        th3.add((Pixel));
                        th3.getQueue().notifyAll();
                    }
                    synchronized (th4.getQueue()){
                        th4.add((Pixel));
                        th4.getQueue().notifyAll();
                    }
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
