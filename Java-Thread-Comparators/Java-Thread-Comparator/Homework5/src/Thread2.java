public class Thread2 implements Runnable {
    private final PriorityQueue queue = new PriorityQueue(new LexiGraph());
    private int capacity;
    private int deleted = 0;

    public Thread2(int capacity) {
        this.capacity = capacity;
    }

    public image_Pixel add(image_Pixel Pixel){
            queue.offer(Pixel);

        return Pixel;
    }
    public void print() {
        queue.printAll();
    }

    public PriorityQueue getQueue() {
        return queue;
    }

    @Override
    public  void run() {
        while (deleted < capacity) {
            synchronized (queue){

                if (!queue.isEmpty()) {
                    deleted++;
                    image_Pixel tmp = queue.poll();
                    System.out.println("Thread 2-PQLEX: " + "[" + tmp.toString() + "]" + " deleted : " + deleted);
                }
                else if(queue.isEmpty()){
                    try {
                        queue.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}

