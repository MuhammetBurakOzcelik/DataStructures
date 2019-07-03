public class Thread4 implements Runnable {
    private final PriorityQueue queue = new PriorityQueue(new BitMix());
    private int capacity;
    private int deleted = 0;

    public Thread4(int capacity) {
        this.capacity = capacity;
    }

    public image_Pixel add(image_Pixel Pixel){
        queue.offer(Pixel);

        return Pixel;
    }

    public PriorityQueue getQueue() {
        return queue;
    }

    @Override
    public void run() {
        while (deleted < capacity) {
            synchronized (queue){

                if (!queue.isEmpty()) {
                    deleted++;
                    image_Pixel tmp = queue.poll();
                    System.out.println("Thread 4-PQBMX: " + "[" + tmp.toString() + "]" + " deleted : " + deleted);
                }
                else if(queue.getSize() == 0){
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
