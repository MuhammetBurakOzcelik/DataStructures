import sun.rmi.server.InactiveGroupException;

public class myStack {
    private myStack [] stackArray;

    private Integer xAxis;
    private Integer yAxis;
    private Character label;

    private Integer top;
    private Integer capacity;

    public myStack(int capacity){
        label = 'A';
        this.capacity = capacity;
        top = -1;
        stackArray = new myStack[capacity];
    }

    public myStack(Character l,Integer x, Integer y){
        xAxis = x;
        yAxis = y;
        label = l;
    }

    public void push(myStack j) {
        stackArray[++top] = j;
    }

    public myStack pop() {
        return stackArray[top--];
    }

    public myStack peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        if(top == -1){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return (top == capacity - 1);
    }

    public Integer getxAxis() {
        return xAxis;
    }

    public Integer getyAxis() {
        return yAxis;
    }

    public void print() {
        System.out.print(this.xAxis);
        System.out.print(' ');
        System.out.println(this.yAxis);
    }
}
