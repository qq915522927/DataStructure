import java.util.Random;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{
    private class Node{
        public K k;
        public V v;
        public Node left;
        public Node right;
        public Node(K k, V v){
            this.k = k;
            this.v = v;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;
    public BSTMap(){
        root = null;
        size = 0;

    }
    @Override
    public void add(K k, V v) {
        root = add(k, v, root);
    }
    private Node add(K k, V v, Node root){
        if(root == null){
            size ++;
            return new Node(k, v);
        }
        if(k.equals(root.k)){
            //update the value, if the key already exists
            root.v = v;
        }
        if(k.compareTo(root.k) <0){
            root.left = add(k, v, root.left);
        }
        if(k.compareTo(root.k) >0){
            root.right = add(k, v, root.right);
        }
        return root;
    }

    @Override
    public void set(K k, V v) {
        Node n = findNode(k);
        n.v = v;
    }
    private Node findNode(K k){
        return findNode(k, root);
    }
    private Node findNode(K k, Node root){
        if(root == null){
            throw new ArithmeticException("Can't find key " + k.toString());
        }
        if(k.compareTo(root.k)<0){
            return findNode(k, root.left);
        }
        else if(k.compareTo(root.k)>0){
            return findNode(k, root.right);
        } else{
            // equal
            return root;
        }
    }

    @Override
    public V get(K k) {
        Node n = findNode(k);
        return n.v;
    }

    @Override
    public boolean contains(K k) {
        try {
            findNode(k);
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
    private Node minimun(Node root){
        if(root.left == null){
            return root;
        }
        return minimun(root.left);

    }
    private Node maxmum(Node root){
        if(root.right == null){
            return root;
        }
        return maxmum(root.right);
    }
    // remove the min Node, return the new tree root
    private Node removeMin(Node root){
        if(root.left==null){
            if(root.right!=null){
                return root.right;
            }
            return null;
        }
        if(root.left != null) {
            root.left = removeMin(root.left);
        }
        return root;
    }
    // remove the max Node, return the new tree root
    private Node removeMax(Node root){
        if(root.right==null){
            if(root.left!=null){
                return root.left;
            }
            return null;
        }
        if(root.right!= null) {
            root.right = removeMax(root.right);
        }
        return root;
    }

    @Override
    public V remove(K k) {
        V v = get(k);
        root = remove(k, root);
        return v;
    }
    private Node remove(K k, Node root){
        if(root == null){
            throw  new IllegalArgumentException(" Can't find key " + k.toString());
        }
        if(k.compareTo(root.k)<0){
            root.left = remove(k, root.left);
        }
        else if(k.compareTo(root.k)>0){
            root.right = remove(k, root.right);
        } else {
            if(root.left!=null){
                Node candiate = maxmum(root.left);
                candiate.left = removeMax(root.left);
                candiate.right = root.right;
                return candiate;
            }
            if(root.right!=null){
                Node candiate = minimun(root.right);
                candiate.right = removeMin(root.right);
                candiate.left = root.left;
                return candiate;
            }
        }
        return root;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    private void getMapStr(StringBuilder builder, Node root){
        if(root==null){
            return;
        }
        builder.append(String.format("%s: %s, ", root.k, root.v));
        getMapStr(builder, root.left);
        getMapStr(builder, root.right);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        getMapStr(builder, root);
        builder.append('}');
        return builder.toString();
    }

    public static void main(String[] args) {
        BSTMap<Integer, String> map = new BSTMap<>();
        map.add(1, "Tommy");
        map.add(2, "Tommy2");
        map.add(3, "Jams");
        System.out.println(map);
        System.out.println("##########Remove 2##########");
        map.remove(2);
        System.out.println(map);
        System.out.println("##########Get 3##########");
        System.out.println(map.get(3));

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            map.add(random.nextInt(), "hello");
        }
        System.out.println(map);

        System.out.println("##########Get 3##########");
        System.out.println(map.get(3));

    }
}
