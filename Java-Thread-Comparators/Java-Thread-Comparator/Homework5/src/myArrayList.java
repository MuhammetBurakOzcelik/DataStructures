import java.util.Arrays;
public class myArrayList<E> {
    private int size = 0;
    private Object array[];

    public myArrayList(int capacity) {
        array = new Object[capacity];
    }

    public void add(E e) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = e;
    }
    public E set(int index, E newVal) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        E old = (E) array[index];
        array[index] = newVal;
        return old;
    }
    public E remove(int index) {
        E removeItem = (E) array[index];
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removeItem;
    }

    private void resizeArray() {
        int newSize = array.length * 2;
        array = Arrays.copyOf(array, newSize);
    }
    public int size(){
        return size;
    }
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i>= size || i <0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i );
        }
        return (E) array[i];
    }
}
