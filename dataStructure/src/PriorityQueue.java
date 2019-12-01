import java.util.Comparator;
import java.util.Random;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> heap;
    private  Comparator<E> comparator;
    public PriorityQueue(){
        heap = new MaxHeap<E>(20);
    }
    public PriorityQueue(Comparator<E> comparator){
        heap = new MaxHeap<E>(20, comparator);
    }
    public PriorityQueue(int capacity, Comparator<E> comparator){
        heap = new MaxHeap<E>(capacity, comparator);
    }
    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.popFirst();
    }

    @Override
    public E getFront() {
        return heap.getFront();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {
        int test_n = 10;
        Random random = new Random();
        PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>();
        for (int i = 0; i < test_n; i++) {
            pqueue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        int pre = 0;
        for (int i = 0; i < test_n; i++) {

            int cur = pqueue.dequeue();
            if(i!=0){
               if(cur > pre){
                   throw new IllegalArgumentException("Error in test");
               }
            }
            pre = cur;
        }
        System.out.println("Desending order successed");

        PriorityQueue<Integer> pqueue2 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < test_n; i++) {
            pqueue2.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < test_n; i++) {

            int cur = pqueue2.dequeue();
            if(i!=0){
                if(cur < pre){
                    throw new IllegalArgumentException("Error in test");
                }
            }
            pre = cur;
        }
        System.out.println("Ascending order successed");
    }
}
