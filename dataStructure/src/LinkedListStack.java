import java.util.Random;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList;

    public LinkedListStack(){
        linkedList = new LinkedList<E>();
    }
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);

    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.get(0);
    }

    private static double testStack(Stack stack, int opCount){
        Random random = new Random();
        long start_ts = System.nanoTime();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long end_ts = System.nanoTime();
        return (end_ts - start_ts) / 1000000000.0;

    }
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        int opCount = 10000000;
        System.out.println("ArrayStack: " + testStack(arrayStack, opCount));
        System.out.println("LinkedListStack: " + testStack(linkedListStack, opCount));

    }
}
