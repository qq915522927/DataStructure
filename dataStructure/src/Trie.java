import java.util.TreeMap;

public class Trie {
    private class Node{

        public TreeMap<Character, Node> next;
        private boolean isWord;
        public Node(){
            next = new TreeMap<>();
            isWord = false;
        }
        public Node(Boolean isWord){
            next = new TreeMap<>();
            isWord = isWord;
        }
    }
    private int size;
    private Node head;
    public Trie(){
        head = new Node();
    }
    public boolean contains(String word){
        return contains(word, 0, head);

    }
    private boolean contains(String word, int charIndex, Node node){
        if(charIndex >= word.length()){
            return false;
        }
        Character c = word.charAt(charIndex);
        if(!node.next.containsKey(c)){
            return false;
        }
        Node next = node.next.get(c);
        if(next.isWord && charIndex == (word.length()-1)){
            return true;
        }
        return contains(word, charIndex+1, next);
    }
    public void add(String word){
        Node cur  = head;
        for (int i = 0; i < word.length() ; i++) {
            Character c = word.charAt(i);
            if(cur.next.containsKey(c)){
                cur = cur.next.get(c);
            } else{
                Node newNode = new Node();
                cur.next.put(c, newNode);
                cur = newNode;
            }
        }
        if(!cur.isWord){
            size++;
        }
        cur.isWord = true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Trie: ");
        getTrieString(head, builder);
        return builder.toString();
    }

    private void getTrieString(Node node, StringBuilder builder ){
        for(Character key: node.next.keySet()){
            builder.append(key);
            builder.append(", ");
            getTrieString(node.next.get(key), builder);
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("hello");
        trie.add("world");
        System.out.println(trie);
        System.out.println(trie.contains("hello"));
        System.out.println(trie.contains("world"));
        System.out.println(trie.contains("worl"));

    }
}
