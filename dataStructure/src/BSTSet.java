import java.util.ArrayList;
public class BSTSet<E extends Comparable<E>> implements Set<E>{
    private BST<E> bst;
    public BSTSet(){
        bst = new BST<>();
    }
    @Override
    public void add(E e) {
        bst.add(e);

    }

    @Override
    public void remove(E e) {
        bst.removeEle(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    public static void main(String[] args) {
        System.out.println("Using MapSet...");

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

        }

        System.out.println();



        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

        }

        System.out.println("Pride and Prejudice");
        long ts1 = System.nanoTime();
        BSTSet<String> set1 = new BSTSet<>();
        for (String word : words1)
            set1.add(word);
        System.out.println("Total different words: " + set1.getSize());
        long ts2 = System.nanoTime();
        calElasped(ts1, ts2);
        System.out.println();


        System.out.println("A Tale of Two Cities");
        BSTSet<String> set2 = new BSTSet<>();
        for(String word: words2)
            set2.add(word);
        System.out.println("Total different words: " + set2.getSize());
        long ts3 = System.nanoTime();
        calElasped(ts2, ts3);
        System.out.println();

        System.out.println("Using LinkedListSet...");

        System.out.println("Pride and Prejudice");

        LinkedListSet<String> set3 = new LinkedListSet<>();
        for (String word : words1)
            set3.add(word);
        System.out.println("Total different words: " + set3.getSize());
        long ts4 = System.nanoTime();
        calElasped(ts3, ts4);
        System.out.println();

        System.out.println("A Tale of Two Cities");
        LinkedListSet<String> set4 = new LinkedListSet<>();
        for (String word : words2)
            set4.add(word);
        System.out.println("Total different words: " + set4.getSize());
        long ts5 = System.nanoTime();
        calElasped(ts4, ts5);
        System.out.println();
    }
    private static void calElasped(long startTs, long endTs){

        Double elapsed = (endTs - startTs) / 1000000000.0;
        System.out.println(String.format("Elapsed: %fs", elapsed));
    }
}
