import java.io.FileInputStream;

public class P1_readFile {

    private Character [][] array;
    private Integer row;
    private Integer col;

    /**
     * Reading file method.
     * @param fileName  Filename (String)
     * @param row  # of row
     * @param col  # of column.
     */
    public P1_readFile(String fileName, Integer row, Integer col){
        array = new Character[row][col];
        try{
            FileInputStream fin=new FileInputStream(fileName);
            int i = 0;
            int j = 0;
            int k = 0;

            while((i=fin.read())!=-1){
                if( ((char) i) != ' '){
                    array[k][j] = ((char) i);
                    ++j;
                }
                if( (char) i == '\n'){
                    j = 0;
                    ++k;
                }
            }
            this.row = k+1;
            this.col = j;
            fin.close();
        }catch(Exception e){System.out.println(e);}
    }

    /**
     * It checks 4 sides of the current position and find out if it has a neighbor.
     * If it has x or 1 around it is its neighbor then pushes to stack its neighbour. Because; In this way,
     * we have access to all the neighbors around that location.
     * @param r index of row
     * @param c index of column (integer)
     * @param e object of stack to push
     * @return  true or false. If it has neighbour returns true else returns false.
     */
    public boolean hasAdj(Integer r, Integer c, myStack e){
        int control = 0;
        if(r+1 < row && (array[r+1][c] == '1')){
            if(array[r+1][c] != 'x'){
                control = 1;
                e.push(new myStack(array[r+1][c],r+1,c));
            }
        }
        if( r-1 >= 0 && (array[r-1][c] == '1')){
            if(array[r-1][c] != 'x'){
                control = 1;
                e.push(new myStack(array[r-1][c],r-1,c));
            }
        }
        if(c-1 >= 0 && (array[r][c-1] == '1')){
            if(array[r][c-1] != 'x'){
                control = 1;
                e.push(new myStack(array[r][c-1],r,c-1));
            }
        }
        if(c+1 < col && (array[r][c+1] == '1')){
            if(array[r][c+1] != 'x'){
                control = 1;
                e.push(new myStack(array[r][c + 1], r, c + 1));
            }
        }
        if(control == 1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * It checks whether the current location is component or empty.
     * @param r index of row.
     * @param c index of column.
     * @return If it is component returns 1 else returns 0.
     */
    public Integer isOne(int r, int c){
        if(array[r][c] == '1'){
            return 1;
        }
        return 0;
    }
    public Integer getRow(){
        return row;
    }

    public Integer getCol(){
        return col;
    }

    /**
     * X is placed so that the current location will not be visited again.
     * @param i index of row.
     * @param j index of column.
     */
    public void setVisited(Integer i, Integer j){
        array[i][j] = 'x';
    }

    /**
     * Prints all map on console.
     */
    public void print(){
        for(int i=0; i<row; ++i){
            for (int j = 0; j <col ; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
