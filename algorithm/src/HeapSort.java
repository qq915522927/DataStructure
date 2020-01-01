import java.util.Random;

public class HeapSort {
    public class Heap<T extends Comparable> {
        // 最小堆
        private int size;
        private T[] data;

        Heap(int capacity){
            size = 0;
            data = (T[])new Comparable[capacity];
       }

       public void addEle(T ele){
            data[size+1] = ele;
            shiftUp(size+1);
            size++;
       }
       public T extractEle(){
            T res = data[1];
            swap(1, size);
            size --;
            shifDown(1);
            return res;
       }
       private void shiftUp(int index){
            while (true)
            {
                int p = parent(index);
                if (p>0 && data[p].compareTo(data[index])>0){
                    swap(p, index);
                    index = p;
                } else{
                    break;
                }
            }
       }
       private void shifDown(int index){
            while (true)
            {
                if(leftChild(index) > size)
                    break;
                int minIndex = leftChild(index);
                if(leftChild(index) + 1 <=size && data[leftChild(index)+1].compareTo(data[minIndex])<0)
                    minIndex = leftChild(index) + 1;
                if(data[index].compareTo(data[minIndex])>0){
                    swap(index, minIndex);
                } else {
                    break;
                }
                index = minIndex;
            }
       }
       public void swap(int i, int j){
            T t = data[i];
            data[i] = data[j];
            data[j] = t;
       }
       private int parent(int index){
            return index / 2;
       }
       private int leftChild(int index){
            return index * 2;
       }

       public int getSize(){
            return size;
       }
       public boolean isEmpty(){
            return size == 0;
       }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            for (int i = 1; i < size + 1; i++) {
                builder.append(data[i].toString());
                builder.append(", ");
            }
            return builder.toString();
        }
        public boolean isValid(){
            return  isValid(1);

        }
        private boolean isValid(int root){
            if(leftChild(root) > size){
                return true;
            }
            if(leftChild(root) + 1 >size){
                return data[root].compareTo(data[leftChild(root)]) <= 0;
            }
            T min = data[leftChild(root)];
            if(data[leftChild(root)+1].compareTo(min) <0){
                min = data[leftChild(root)+1];
            }


            return data[root].compareTo(min) <= 0 && isValid(leftChild(root)) && isValid(leftChild(root)+1);

        }
    }

    public void testHeap(){
        Heap<Integer> heap = new Heap<Integer>(30);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            heap.addEle(random.nextInt(30));
        }
        System.out.println(heap);
        System.out.println(heap.isValid());
        for (int i = 0; i < 20; i++) {
            System.out.println(heap.extractEle());

        }
    }
    public void heapSort(Integer[] array){
        Heap<Integer> heap = new Heap<Integer>(array.length+1);
        for (int i = 0; i < array.length; i++) {
            heap.addEle(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.extractEle();
        }
    }
    public  static void main(String[] args) {
        HeapSort ins = new HeapSort();
        ins.testHeap();
    }
}
