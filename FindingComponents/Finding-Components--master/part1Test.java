import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class part1Test {
    public static void main(String[] args) throws IOException {

        String fileName = "part1Test.txt";
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String size2 = allLines.get(0);

        /*
        * Read file.txt to imageArray and prints of first state..
        */
        P1_readFile imageArray = new P1_readFile("part1Test.txt",allLines.size(),size2.length());
        imageArray.print();
        System.out.println("Before Find Components");

        /*
        *  Goes through the entire array and throws all its neighbors into the heap if
        *  there is a component in that position. And it puts x in its position in order not to visit each cell it
        *  walks again. When the stack is empty, it finds all neighboring components and the counter increments by one.
        *  If there is no neighboring counter incerements by one.
        */
        myStack obj = new myStack((imageArray.getRow() * imageArray.getCol()) );
        int counter = 0;
        myStack temp;
        for(int i = 0; i<imageArray.getRow(); ++i){
            for(int j = 0; j<imageArray.getCol(); ++j){
                if(imageArray.isOne(i,j) == 1){
                    imageArray.setVisited(i,j);
                    if(imageArray.hasAdj(i,j, obj)){
                        while( !(obj.isEmpty()) ){
                            temp = obj.pop();
                            imageArray.setVisited(temp.getxAxis(),temp.getyAxis());
                            imageArray.hasAdj(temp.getxAxis(),temp.getyAxis(),obj);
                        }
                        counter++;
                    }
                    else{
                        counter++;
                    }
                }
            }
        }
        /*
        * imageArray and prints of last state..
        */
        imageArray.print();
        System.out.println("After find all Components ");
        System.out.println("The number of White Component ----> \t:"+counter);
    }

}

