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

    public static void main(String[] args) {
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
        for (int i = 0; i < count + 1; i++) {
            assert bst.contain(i);
            System.out.println(bst.contain(i));
        }
    }
}
