import javax.swing.table.TableRowSorter;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ExperimentList implements Iterable<Experiment> {

    /**
     * head of list
     */
    private Node head;

    /**
     * size of list
     */
    private int size;

    ExperimentList() {
        head = null;
        size = 0;
    }

    @Override
    public Iterator<Experiment> iterator() {
        return new Iterator<Experiment>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Experiment next() {
                if (hasNext()) {
                    Experiment data = current.data;
                    current = current.next;
                    return data;
                }
                return null;
            }

        };
    }

    private class Node {

        private Node next;
        private Node nextDayNode;
        private Experiment data;

        Node() {
            next = null;
            nextDayNode = null;
        }

        Node(Experiment data) {
            this.data = data;
            next = null;
            nextDayNode = null;
        }
    }

    /**
     * The given experiment is added to the list.
     * @param data Experiment to be added to the list.
     */
    public void addExp(Experiment data) {
        Node newNode = new Node(data);
        if (size == 0) {
            head = newNode; // 2
            ++size;
        }
        else if (newNode.data.getDay() < head.data.getDay()) {
            newNode.next = head;
            head = newNode;
            ++size;
        }
        else if (newNode.data.getDay() == head.data.getDay()) {
            // 1 | 1 2
            Node iter = head;
            while (iter.next != null && newNode.data.getDay() >= iter.next.data.getDay())
                iter = iter.next;
            newNode.next = iter.next;
            iter.next = newNode;
            ++size;
        }
        else if (newNode.data.getDay() > head.data.getDay()) {
            // 1 | 1 2
            Node iter = head;
            while (iter.next != null && newNode.data.getDay() >= iter.next.data.getDay())
                iter = iter.next;
            newNode.next = iter.next;
            iter.next = newNode;
            ++size;
        }
        linkDay();
    }

    /**
     * It connects the first experiments of the days together.
     */
    private void linkDay() {
        if(head == null){
            throw new NullPointerException("linkDay Method : Null List");
        }
        Node iter = head;
        int dayType = iter.data.getDay();
        Node prev = iter;
        while (iter.next != null) {
            iter = iter.next;
            if (dayType != iter.data.getDay()) {
                prev.nextDayNode = iter;
                prev = iter;
                dayType = iter.data.getDay();
            }
        }
    }

    /**
     * The experiment is returned according to the day and index.
     * @param day The day of the experiment to be checked.
     * @param index Index of the experiment to be checked.
     * @return Data of experimenList to be checked.
     * @throws Exception NullPointer or IndexOfBound
     */
    public Experiment getExp(int day, int index) throws Exception {
        Node current = head;
        if(head == null){
            throw new NullPointerException("getExp Method : List Null");
        }
        if(index < 0){
            throw new IndexOutOfBoundsException("getExp Method: Invalid Index");
        }
        while (current.data.getDay() != day)
            current = current.next;
        for (int i = 0; i < index; ++i)
            current = current.next;

        if(current.data.getDay() != day){
            throw  new NoSuchFieldException("getExp Method : Eşleşme bulunamadı");
        }else if(current == null){
            throw  new NullPointerException("getExp Methodd : Listenin boyutu asildi");
        }
        return current.data;
    }

    /**
     * The experiment is changed according to the day and index.
     * @param day The desired day of change.
     * @param index Index of the experiment to be changed.
     * @param e Experiment to replace the experiment user wants to change.
     * @throws Exception NullPointer or IndexOfBound
     */
    public void setExp(int day, int index, Experiment e) throws Exception {
        if(head == null){
            throw  new NullPointerException("setExp(day,index,Experiment) Method : Null List");
        }
        if (index < 0){
            throw new IndexOutOfBoundsException("setExp(day,index,Experiment) Method : Invalid Index");
        }
        Node current = head;
        while (current.data.getDay() != day)
            current = current.next;
        for (int i = 0; i < index; ++i)
            current = current.next;

        if(current.data.getDay() != day){
            throw  new NoSuchFieldException("setExp(day,index,Experiment) Method : Eşleşme bulunamadı");
        }
        current.data = e;
    }

    /**
     * The experiment is removed according to the day and index.
     * @param day The day of the experiment we want to remove.
     * @param index The Index of the experiment we want to remove.
     * @throws Exception NullPointer or IndexOfBound
     */
    public void removeExp(int day, int index) throws Exception{
        if(head == null){
            throw  new NullPointerException("removeExp(day,index) Method : Null List");
        }
        if(index < 0){
            throw new IndexOutOfBoundsException("removeExp Method : Gecersiz Index");
        }

        Node current = head;
        if(head.data.getDay() == day && index == 0){
            System.out.println(head.data.toString());
            head = current.next;
            linkDay();
        }
        if (index != 0) {
            while (current.data.getDay() != day)
                current = current.next;
            if(current.data.getDay() != day){
                throw new NoSuchElementException("removeExp(day,index) Method : Eslesme bulunamadi");
            }
            for (int i = 0; i < index-1; ++i)
                current = current.next;
            if(current.data.getDay() != day){
                throw new NoSuchElementException("removeExp(day,index) Method : Eslesme bulunamadi");
            }
            System.out.println(current.next.data.toString());
            current.next = current.next.next;
        }
        if (head.data.getDay() != day && index == 0) {

            while (current.next.data.getDay() != day)
                current = current.next;
            System.out.println(current.next.data.toString());
            current.next = current.next.next;
            linkDay();
        }
        --size;
    }

    public void listExp (int day){
        if(head == null){
            throw  new NullPointerException("listExp Method : Null List");
        }
        Node current = head;
        while (current.data.getDay() != day)
            current = current.next;
        if(current.data.getDay() != day){
            throw  new NoSuchElementException("listExp Method : Eslesme Bulunamadi");
        }
        while (current.data.getDay() == day) {
            if (current.data.isCompleted())
                System.out.println(current.data);
            current = current.next;
        }
    }

    public void removeDay(int day) throws Exception{
        int index = 0;
        if(head == null){
            throw  new NullPointerException("removeDay(day) Method : Null List");
        }
        Node current = head;
        if(head.data.getDay() == day){
            while (current.data.getDay() == day) {
                current = current.next;
                ++index;
            }
            for (int i = 0; i< index; ++i){
                System.out.println(head.data.toString());
                head = head.next;
            }
            linkDay();
            return;
        }else{
            while (current.data.getDay() != day)
                current = current.next;
            if(current.data.getDay() != day){
                throw new NoSuchElementException("removeDay(day) Method : Eslesme Bulunamadi");
            }
            while (current.data.getDay() == day){
                removeExp(day,0);
                current = current.next;
                if(current == null)
                    return;
            }
        }
    }

    /**
     * Experiments on the given day are sorted by accuracy.
     * @param day According to the accuracy of the day we want to sort the experiments.
     */
    public void orderDay(int day){
        if(head == null){
            throw  new NullPointerException("orderDay Method: Null List");
        }
        Node current = head;
        int index = 0;
        while (current.data.getDay() != day)
            current = current.next;
        if(current.data.getDay() != day){
            throw  new NoSuchElementException("orderDay Method: Eslesme Bulunamadi");
        }
        Node temp = current;
        Node test = current;
        Node print = current;
        while (temp.data.getDay() == day){
            index++;
            temp = temp.next;
        }

        for(int i = 0; i<index; i++){
            test = current;
            for(int j = 0; j <index-i -1; j++) {
                if (current.data.getAccuracy() > current.next.data.getAccuracy()) {
                    swap(current.next.data, current.data);
                }
                current = current.next;
            }
            //System.out.println(test.data.getTime());
            current = test;
        }
        linkDay();
        for(int i = 0; i<index; ++i){
            System.out.println(print.data.toString());
            print = print.next;
        }
    }

    /**
     * All experiments are ordered by accuracy.
     * @return ordered
     * @throws NullPointerException NullPointer
     */
    public ExperimentList orderExperiments() throws NullPointerException{
        if(head == null){
            throw  new NullPointerException("orderExperiments Method: Null List");
        }
        Node temp = head;
        ExperimentList tempList = new ExperimentList();
        while (temp != null){
            Experiment tempExp = new Experiment(temp.data.getSetup(),temp.data.getTime(),temp.data.isCompleted(),temp.data.getDay(),(float)temp.data.getAccuracy());
            tempList.addExp(tempExp);
           // System.out.println(temp.data.getTime());
            temp = temp.next;
        }
        Node current = tempList.head;
        Node test;
        for(int i = 0; i<size; i++){
            test = current;
            for(int j = 0; j <size-i -1; j++) {
                if (current.data.getAccuracy() > current.next.data.getAccuracy()) {
                    swap(current.next.data, current.data);
                }
                current = current.next;
            }
            current = test;
        }
        return tempList;
    }

    /**
     * Swap method.
     * @param a swap
     * @param b swap
     */
    public void swap (Experiment a, Experiment b){
        Experiment temp = new Experiment(a.getSetup(),a.getTime(),a.isCompleted(),a.getDay(),(float)a.getAccuracy());

        a.setAccuracy((float)b.getAccuracy());
        a.setTime(b.getTime());
        a.setSetup(b.getSetup());
        a.setCompleted(b.isCompleted());
        a.setDay(b.getDay());

        b.setAccuracy((float)temp.getAccuracy());
        b.setTime(temp.getTime());
        b.setSetup(temp.getSetup());
        b.setCompleted(temp.isCompleted());
        b.setDay(temp.getDay());
    }

    /**
     * All experiments print on the console.
     */
    public void listAll()
    {
        System.out.println("List experiment view:");
        Node last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.next;
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.nextDayNode;
        }
    }
}