public class myStack_P2{

    private String [] opStack;

    private Integer top;
    private Integer capacity;

    public myStack_P2(int capacity){
        this.capacity = capacity;
        top = -1;
        opStack = new String[capacity];
    }

    public void push(String j) {
        opStack[++top] = j;
    }

    public String pop() {
        return opStack[top--];
    }

    public String peek() { return opStack[top];
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
}
