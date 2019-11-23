public class ArrayStack<E> implements Stack<E>{

    Array<E> array;
    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }
    public ArrayStack(){
        array = new Array<E>(20);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Stack: size=%d\n", getSize()));
        res.append('[');
        for (int i = 0; i < getSize() ; i++) {
            res.append(array.get(i).toString());
            if(i != getSize()-1){
                res.append(", ");
            }
        }
        res.append("] Top");
        return res.toString();

    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 10 ; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        System.out.println(stack);
        for (int i = 0; i < 5 ; i++) {
            stack.pop();
            System.out.println(stack);
        }
    }
}
