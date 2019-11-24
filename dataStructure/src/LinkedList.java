public class LinkedList<E> {

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

    private Node dummyHead;
    private int size;
    public LinkedList(){
        size = 0;
        dummyHead = new Node( );
    }
    public void addLast(E e){
        dummyHead.next = addLast(dummyHead.next, e);
        size++;
    }
    public void addFirst(E e){
        Node newHead = new Node(e, dummyHead.next);
        dummyHead.next = newHead;
        size++;
    }
    public E remove(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (size == 0) {
            throw new IllegalArgumentException("LinkedList is empty");
        }
        Node pre = findNode(index-1);
        Node res = pre.next;
        pre.next = pre.next.next;
        size--;
        return res.value;
    }

    public E get(int index) {

        if(index<0 || index>size){
            throw new IllegalArgumentException("Invalid index");
        }
        if(size==0){
            throw new IllegalArgumentException("LinkedList is empty");
        }
        return findNode(index).value;
    }
    /**
     * 向index处添加元素，实际中不用， 只做练习用
     * 关键是要找到带插入前的一个元素, 使用dummyHead可以统一头结点和非头结点
     * @param index
     * @param e
     */
    public void add(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Invalid index");
        }
        // find the previoud node
        Node pre = findNode(index-1);
        Node newNode = new Node(e, pre.next);
        pre.next = newNode;
        size++;
    }
    public void set(int index, E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Invalid index");
        }
        Node cur = findNode(index);
        cur.value = e;
    }

    /**
     * find node at indext
     * @param index
     * @return
     */
    private Node findNode(int index){
        Node pre = dummyHead;
        if(index==-1){
            return dummyHead;
        }
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        return pre.next;
    }
    // 在以node为头节点的链表中添加e, 并返回当前head
    // 递归
    private Node addLast(Node node, E e){
        if(node == null){
            return new Node(e);
        }
        node.next = addLast(node.next, e);
        return node;
    }
    public int getSize(){
        return size;
    }
    public E removeFirst(){
        return remove(0);
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
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.addLast(i);
        }
        System.out.println(list);
        list.add(1, 100);
        System.out.println(list);
        list.addFirst(300);
        System.out.println(list);
        list.add(7, 700);
        System.out.println(list);
        System.out.println(list.get(7));
        System.out.println(list.get(0));

        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list);

        list.set(3, 3000);
        System.out.println(list);

        list.addLast(9000);
        System.out.println(list);
    }
}
