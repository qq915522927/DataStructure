public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue(){
        array = new Array<>();

    }
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);

    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size=%d\n", getSize()));
        res.append("Front [");
        for (int i = 0; i < getSize() ; i++) {
            res.append(array.get(i).toString());
            if(i != getSize()-1){
                res.append(", ");
            }
        }
        res.append("] Tail");
        return res.toString();

    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        for (int i = 0; i < 10; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
    }

}
