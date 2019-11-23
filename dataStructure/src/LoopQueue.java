
public class LoopQueue<E> implements Queue<E>{
    private E[] data;
    private int size;
    // the index of the first element
    private int head;
    // the index +1 of the tail element
    private int tail;
    public LoopQueue(){
        this(20);
    }
    public LoopQueue(int capacity){
        //由于tail的语意，这里会浪费一个位置
        data = (E[])new Object[capacity+1];
        head = 0;
        tail = 0;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {
        if(isFull()){
            resize(2*(data.length-1));
        }
        data[tail] = e;
        tail++;
        size++;
        tail = tail % data.length;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("The queue is empty");
        }
        E res = data[head];
        head++;
        size--;
        head = head % data.length;
        if(size >= 10 && size*3 < data.length-1){
            resize((data.length-1) /2);
        }
        return res;
    }

    @Override
    public E getFront() {
        return data[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return (tail + 1) % data.length == head;
    }
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity+1];
        if(size==0){
            throw new IllegalArgumentException("Empty, no need to resize");
        }
        if(tail > head){
            for (int i = head; i < tail ; i++) {
                try {
                newData[i-head] = data[i];
                }catch (Exception e){
                    System.out.println(i);
                    System.out.println(head);
                    System.out.println(newCapacity+1);
                    throw e;
                }
            }
        } else{
            int moveSize = 0;
            for (int i = head; i < data.length ; i++) {
                try {

                    newData[i-head] = data[i];
                    moveSize++;
                }catch (Exception e){
                    System.out.println(i);
                    System.out.println(head);
                    throw e;
                }
            }
            for (int i = 0; i < tail; i++) {
                newData[moveSize] = data[i];
                moveSize++;
            }
        }
        data = newData;
        head = 0;
        tail = size;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size=%d, head=%d, tail=%d, capacity=%d\n", size, head, tail, data.length-1));
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<Integer>(20);
        for (int i = 0; i < 300 ; i++) {
            queue.enqueue(i);

        }
        System.out.println(queue);
        int size = queue.getSize();
        for (int i = 0; i < size; i++) {
            if(i!=queue.dequeue()){
                throw new IllegalArgumentException("Assert error");
            };
            System.out.println(queue);
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
    }
}
