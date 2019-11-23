public interface Queue<E> {
    int getSize();
    void enqueue(E e);
    E dequeue();
    E getFront();
    boolean isEmpty();

}
