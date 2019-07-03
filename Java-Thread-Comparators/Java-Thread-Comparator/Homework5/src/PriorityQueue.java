import java.util.Comparator;

public class PriorityQueue {
    private int capacity = 100;
    private int size;
    private myArrayList<image_Pixel> data;
    private Comparator<image_Pixel> comparator;

    public PriorityQueue(Comparator<image_Pixel> comparator) {
        this.comparator = comparator;
        data = new myArrayList<>(capacity);
        size = 0;
    }

    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }
    protected void swap(int index1, int index2) {
        image_Pixel tmp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, tmp);
    }
    public int getSize() {
        return size;
    }

    public boolean offer(image_Pixel item) {
        data.add(item);
        size++;
        int child = data.size()-1;
        int parent = (child - 1) / 2;
        while(((comparator.compare(data.get(parent),data.get(child)) < 0))){
            swap(parent,child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }

    public image_Pixel poll() {
        if(isEmpty()) {
            return null;
        }
        image_Pixel result = data.get(0);

        if(data.size() == 1) {
            data.remove(0);
            size--;
            return result;
        }
        data.set(0,data.remove(data.size() - 1));
        int parent = 0;

        while(true) {
            int leftChild = 2 * parent + 1;
            if(leftChild >= data.size()) {
                break;
            }
            int rightChild = leftChild + 1;
            int maxChild = leftChild;

            if(rightChild < data.size() && comparator.compare(data.get(leftChild), data.get(rightChild)) < 0) {
                maxChild = rightChild;
            }
            if(comparator.compare(data.get(parent),data.get(maxChild)) < 0) {
                swap(parent,maxChild);
                parent = maxChild;
            }
            else {
                break;
            }
        }

        size--;
        return result;
    }

    public void printAll() {
        for (int i = 0; i <size ; i++) {
            System.out.println(data.get(i).toString());
        }
    }
    public boolean isFull() {
        if(size == capacity)
            return true;
        return false;
    }
}
