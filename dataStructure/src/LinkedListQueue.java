public class LinkedListQueue<E> implements Queue<E> {
    private LinkedList<E> linkedList;
    public LinkedListQueue(){
        linkedList = new LinkedList();
    }
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public void enqueue(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public E getFront() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("LinkedListQueue: %s", linkedList.toString());
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 5 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        for (int i = 0; i < 5 ; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
    }


    /**
     * Inner class
     * @param <E>
     */
    private class LinkedList<E> {

        private class Node{
            public Node next;
            public E value;
            public Node(E value, Node next){
                this.value = value;
                this.next = next;
            }
            public Node(E value){
                this(value, null);
            }
            public Node(){
                this(null, null);
            }

            @Override
            public String toString() {
                return value.toString();
            }
        }

        private Node dummyHead, tail;
        private int size;
        public LinkedList(){
            size = 0;
            dummyHead = new Node( );
            tail = dummyHead;
        }
        public void addLast(E e){
            tail.next = new Node(e);
            tail = tail.next;
            size++;
        }
        public void addFirst(E e){
            Node newHead = new Node(e, dummyHead.next);
            if(isEmpty()){
                tail = newHead;
            }
            dummyHead.next = newHead;
            size++;
        }
        public E getFirst(){
            if(isEmpty()){
                throw new IllegalArgumentException("Queue is empty");
            }
            return dummyHead.next.value;
        }

        public int getSize(){
            return size;
        }
        public E removeFirst(){
            Node res = dummyHead.next;
            dummyHead.next = res.next;
            size --;
            if(isEmpty()){
                tail = dummyHead;
            }
            return res.value;
        }
        public boolean isEmpty(){
            return size == 0;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("LinkedList: size=%d HEAD [", size));
            Node cur = dummyHead.next;
            for (int i = 0; i < size; i++) {
                if(i==size-1){
                    builder.append(cur.value.toString());
                    continue;
                }
                builder.append(String.format("%s, ",cur.value.toString()));
                cur = cur.next;
            }
            builder.append("] Tail");
            return builder.toString();
        }
    }
}
