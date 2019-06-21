import java.util.ArrayList;
import java.util.Iterator;

public class P5 {

    private ArrayList<Integer> d;
    private Iterator ite = null;

    public P5( int [][] data){
        d = new ArrayList<Integer>();

    }
    /*
     * https://gist.github.com/francisco-perez-sorrosal/1510085
     * I tried to learn with the same problem to solve above the site.
     */

    public void recursiveIter(int[][] matrix, int initialRow,
                              int finalRow, int initialColumn, int finalColumn, int totalElems,
                              int visited, int direction){

        if (visited != totalElems) {
            int count = 0;
            switch (direction) {
                case 0:
                    for (int col = initialColumn; col <= finalColumn; col++) {
                        d.add(matrix[initialRow][col]);
                        count++;
                    }
                    recursiveIter(matrix, initialRow + 1, finalRow,
                            initialColumn, finalColumn, totalElems,
                            visited + count, 1);
                    break;
                case 1:
                    for (int row = initialRow; row <= finalRow; row++) {
                        d.add(matrix[row][finalColumn]);
                        count++;
                    }
                    recursiveIter(matrix, initialRow, finalRow,
                            initialColumn, finalColumn - 1, totalElems, visited
                                    + count, 2);
                    break;
                case 2:
                    for (int col = finalColumn; col >= initialColumn; col--) {
                        d.add(matrix[finalRow][col]);
                        count++;
                    }
                    recursiveIter(matrix, initialRow, finalRow - 1,
                            initialColumn, finalColumn, totalElems,
                            visited + count,3);
                    break;
                case 3:
                    for (int row = finalRow; row >= initialRow; row--) {
                        d.add(matrix[row][initialColumn]);
                        count++;
                    }
                    recursiveIter(matrix, initialRow, finalRow,
                            initialColumn + 1, finalColumn, totalElems, visited
                                    + count, 0);
                    break;
            }
            ite = d.iterator();
        }
    }

    public Object next() { return ite.next(); }

    public boolean hasNext(){
        return ite.hasNext();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        int lastRow = matrix.length - 1;
        int lastColumn = matrix[0].length - 1;
        int totalElems = matrix.length * matrix[0].length;

        P5 test = new P5(matrix);
        test.recursiveIter(matrix, 0, lastRow, 0, lastColumn, totalElems, 0, 0);

        while (test.hasNext()){
            Object element = test.next();
            System.out.print(element + " ");
        }
    }
}
