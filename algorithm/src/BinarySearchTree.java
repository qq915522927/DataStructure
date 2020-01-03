import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<K extends Comparable, V> {
    class Node{
        public K k;
        public V v;
        public Node left;
        public Node right;

        Node(K key, V value){
            this.k = key;
            this.v = value;
        }
        Node(K key){
            this.k = key;
            this.v = null;
        }

        @Override
        public String toString() {
            return String.format("{key: %s, value: %s}", k, v);
        }
    }

    private int size;
    private Node root;
    BinarySearchTree(){
        size = 0;
        root = null;
    }
    public void addEle(K key, V value){
        root = addEle(key, value, root);

    }
    private Node addEle(K key, V value, Node root){
        // 添加元素， 返回新树的根节点
        if(root == null){
            size ++;
            return new Node(key, value);
        }
        if(key.equals(root.k)){
            //如果存在了， 跟新value
            root.v = value;
            return root;
        }
        if(key.compareTo(root.k)>0){
            root.right = addEle(key, value, root.right);
        }
        if(key.compareTo(root.k)<0){
            root.left = addEle(key, value, root.left);
        }
        return root;
    }
    public V get(K key) throws Exception {
        Node res = get(key, root);
        return res.v;
    }
    private Node get(K key, Node root) throws Exception {
        if(root == null){
            throw new Exception("Not found key: " + key);
        }
        if(root.k.equals(key)){
            return root;
        }
        if(key.compareTo(root.k)>0){
            return get(key, root.right);
        }
        // key < root.k
        return get(key, root.left);
    }
    private Node findMax(Node root){
        if(root==null){
            return null;
        }
        if(root.right == null){
            return root;
        }
        return findMax(root.right);
    }
    private Node removeMax(Node root){
        if(root==null){
            return null;
        }
        if(root.right == null && root.left == null){
            return null;
        }
        if(root.right == null && root.left != null){
            return root.left;
        }
        root.right = removeMax(root.right);
        return root;
    }
    public V removeKey(K key) throws Exception {
        V res = get(key);
        root = removeNode(root, key);
        return res;
    }
    private Node removeNode(Node root, K key) throws Exception {
        if(root == null){
            throw new Exception("Not contains key: " + key.toString());
        }
        if(root.k.equals(key)){
            if(root.left==null && root.right==null){
                return null;
            }
            if(root.left!=null && root.right==null){
                return root.left;
            }
            if(root.left==null && root.right!=null){
                return root.right;
            }
            Node candidate = findMax(root.left);
            removeMax(root.left);
            candidate.left = root.left;
            candidate.right = root.right;
            return candidate;
        }
        if(key.compareTo(root.k)<0){
            root.left = removeNode(root.left, key);
        }
        if(key.compareTo(root.k)>0){
            root.right = removeNode(root.right, key);
        }
        return root;
    }
    public String preTraverse(){
        // 前序遍历
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        preTraverse(root, builder);
        builder.append("]");
        return builder.toString();
    }
    private void preTraverse(Node root, StringBuilder builder){
        if(root == null){
            return;
        }
        builder.append(root.toString());
        builder.append(", ");
        preTraverse(root.left, builder);
        preTraverse(root.right, builder);
    }
    public boolean contain(K key){
        return contain(key, root);

    }
    private boolean contain(K key, Node root){
        if(root == null){
            return false;
        }
        if(root.k.compareTo(key)>0){
            return contain(key, root.left);
        }
        if(root.k.compareTo(key)<0){
            return contain(key, root.right);
        }
        // key equal root.k
        return true;
    }
    public String levelTraverse(){
        StringBuilder builder = new StringBuilder();

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        builder.append("[");
        while (!queue.isEmpty()){
            Node node = queue.remove();
            if(node != null){
                builder.append(node.toString());
                builder.append(", ");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public String midTraverse(){
        // 前序遍历
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        midTraverse(root, builder);
        builder.append("]");
        return builder.toString();
    }
    private void midTraverse(Node root, StringBuilder builder){
        if(root == null){
            return;
        }
        midTraverse(root.left, builder);
        builder.append(root.toString());
        builder.append(", ");
        midTraverse(root.right, builder);
    }
    @Override
    public String toString() {
        return preTraverse();
    }

    public static void main(String[] args) throws Exception {
        int count = 20;
        Integer[] array = new Integer[count];
        for (int i = 0; i < count; i++) {
            array[i] = i;
        }
        TestHelper.suffleArray(array);

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < array.length; i++) {
            bst.addEle(array[i], array[i]);
        }
        System.out.println(bst);
        System.out.println(bst.midTraverse());
        for (int i = 0; i < count; i++) {
            assert bst.contain(i);
            System.out.println(bst.contain(i));
            bst.removeKey(i);
        }
        System.out.println(bst);
        BinarySearchTree<Integer, Integer> bst2 = new BinarySearchTree<>();
        bst2.addEle(3, 5);
        bst2.addEle(4, 5);
        bst2.addEle(9, 5);
        System.out.println(bst2.levelTraverse());
        System.out.println(bst2.removeKey(3));
        System.out.println(bst2.removeKey(4));
        System.out.println(bst2.levelTraverse());
    }
}
