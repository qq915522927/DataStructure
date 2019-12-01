import java.util.Comparator;
import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> list;
    private int size;
    private Comparator<E> comparator;
    public MaxHeap(){
        this(20, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });
    }
    public MaxHeap(E[] data){
        this(data, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });

    }
    public MaxHeap(E[] data, Comparator<E> comparator){
        this.comparator = comparator;
        list = new Array<E>(data.length);
        size = data.length;
        for (int i = 0; i < data.length ; i++) {
            list.addLast(data[i]);
        }
        // the first non-leaf node
        int lastParent = findParent(size - 1);
        for (int i = lastParent; i >= 0; i--) {
            shifDown(i);
        }
    }
    public MaxHeap(int capacity){
        this(capacity, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });
    }
    public MaxHeap(int capacity, Comparator comparator){
        list = new Array<E>(capacity);
        size = 0;
        this.comparator = comparator;
    }


    public void add(E e){
        list.addLast(e);
        size++;
        shifUp(size-1);
    }
    public E popFirst(){
        if(size==0){
            throw new IllegalArgumentException("Heap is empty");
        }
        E ret = list.getFirst();
        list.swap(0, size-1);
        list.removeLast();
        size--;
        shifDown(0);
        return ret;
    }
    private void shifDown(int index){
        while (true){
            int childIndex = findLeftChild(index);
            if(childIndex < 0){
                return;
            }
            if(childIndex + 1 < size &&
                    comparator.compare(list.get(childIndex+1), list.get(childIndex)) > 0
            ){
                childIndex = childIndex + 1;
            }
            if(
                    comparator.compare(list.get(childIndex), list.get(index)) > 0
            ){
                list.swap(index, childIndex);
            }
            index = childIndex;
        }
    }
    private int findLeftChild(int index){
        int p = index * 2 +1;
        if(p >= size){
            // if out of index return -1, to indicate no child
            return -1;
        }
        return p;
    }
    private int findParent(int index){
        if(index<0 || index >= size){
            throw new IllegalArgumentException("Invalid index " + index);}
        // the first node will return
        return (index-1) / 2;

    }
    private void shifUp(int index){
        if(index<0 || index >= size){
            throw new IllegalArgumentException("Invalid index " + index);
        }
        while (index!=0) {
            E cur = list.get(index);
            int parIndex = findParent(index);
            E p = list.get(parIndex);
            if (comparator.compare(p, cur) >= 0) {
                return;
            }
            list.swap(parIndex, index);
            index = parIndex;
        }
        return;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("The heap is empty");
        }
        return list.get(0);
    }
    public static void main(String[] args) {
        int test_n = 10;
        Random random = new Random();
        MaxHeap<Integer> heap = new MaxHeap<Integer>();
        for (int i = 0; i < test_n; i++) {
            heap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int pre=0;
        for (int i = 0; i < test_n; i++) {
            int cur = heap.popFirst();
//            System.out.println(cur);
            if(i!=0){
                if(pre<cur){
                    throw new IllegalArgumentException("Test failed");
                }
            }
            pre = cur;
        }
        System.out.println("Test add and pop Done");

        System.out.println();

        System.out.println("Test heapify");
        Integer[] data = new Integer[test_n];
        for (int i = 0; i < test_n; i++) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }
        MaxHeap<Integer> heap2 = new MaxHeap<Integer>(data);
        for (int i = 0; i < test_n; i++) {
            int cur = heap2.popFirst();
            System.out.println(cur);
            if(i!=0){
                if(pre<cur){
                    throw new IllegalArgumentException("Test failed");
                }
            }
            pre = cur;
        }
        System.out.println("Test heapfiy done");

        int test_n2 = 10000000;
        Integer[] data2 = new Integer[test_n2];
        for (int i = 0; i < test_n2; i++) {
            data2[i] = random.nextInt(Integer.MAX_VALUE);
        }
        test_create_heap(data2, false);
        // 这里实现的 heapify会copy一份原有data导致更慢了
        test_create_heap(data2, true);
    }
    private static void test_create_heap(Integer[] data, boolean isHeapify){

        long statTs = System.nanoTime();
        MaxHeap<Integer> heap;
        if(isHeapify) {
            heap = new MaxHeap<Integer>(data);
        } else {
            heap = new MaxHeap<Integer>(data.length);
            for (int i = 0; i < data.length; i++) {
                heap.add(data[i]);
            }
        }
        long endTs = System.nanoTime();
        double elasped = (endTs - statTs) / 1000000000.0;
        System.out.println("Test create heap done " + elasped + "s");
    }
}
