import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        System.out.print("Please enter the image path : ");
        Scanner scanner = new Scanner(System.in);
        String myString = scanner.next();
        scanner.close();

        Thread1 a = new Thread1(myString);
        Thread t1 = new Thread(a);
        t1.start();


    }
}

