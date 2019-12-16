import java.util.ArrayList;
import java.util.Random;

public class AVLTree<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;
        public int depth;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            depth = 1;
        }
    }
    private int size;
    private Node root;
    public AVLTree(){
        root = null;
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean contains(E e){
        return contains(e, root);
    }

    /***
     * 以root为跟的树中是否含有E
     * @param e
     * @param root
     * @return
     */
    public boolean contains(E e, Node root){
        if(root==null){
            return false;
        }
        if(root.e.equals(e)){
            return true;
        }
        if(e.compareTo(root.e)<0){
            return contains(e, root.left);

        } else{
            return contains(e, root.right);
        }

    }

    /***
     * 后序遍历
     * 遍历的元素按顺序排列
     */
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.e.toString());
    }
    /***
     * 中序遍历
     * 遍历的元素按顺序排列
     */
    public void midOrder(){
        midOrder(root);
    }
    private void midOrder(Node root){
        if(root==null){
            return;
        }
        midOrder(root.left);
        System.out.println(root.e.toString());
        midOrder(root.right);
    }
    /***
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.println(root.e.toString());
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 非递归实现
     * 利用栈回溯
     */
    public void preOrder2(){
        LinkedListStack<Node> stack = new LinkedListStack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e.toString());
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }
    public void add(E e){
        root = add(e, root);
    }

    public int getSize(){
        return size;
    }

    /**
     * 每次添加完节点后维持平衡
     *
     * case1: 左边的左边添加元素导致失衡， 使用右旋转
     * case2: 右边的右边添加元素导致失衡， 使用左旋转
     * case3: 左边的右边添加元素导致失衡， 先对左边进行左旋抓，在对当前节点右旋转
     * case3: 右边的左边添加元素导致失衡， 先对右边进行you旋抓，在对当前节点zuo旋转
     * @param e
     * @param root
     * @return
     */
    private Node add(E e, Node root){
        if(root == null){
            size++;
            return new Node(e);
        }
        if(e.equals(root.e)){
            return root;
        }

        if(e.compareTo(root.e) <0){
            root.left = add(e, root.left);
        } else{
            root.right = add(e, root.right);
        }
        root.depth = Math.max(getDepth(root.left), getDepth(root.right)) + 1;
        int balanceFactor = calBalanceFactor(root);
        if(balanceFactor>1 && calBalanceFactor(root.left) > 0){
            return rightRotate(root);
        }
        if(balanceFactor<-1 && calBalanceFactor(root.right) < 0){
            return leftRotate(root);
        }
        // 左边的右边添加了元素
        if(balanceFactor>1 && calBalanceFactor(root.left) < 0){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // 右边的左边添加了元素
        if(balanceFactor<-1 && calBalanceFactor(root.right) > 0){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    private int getDepth(Node node){
        if(node == null){
            return 0;
        }
        return node.depth;
    }
    private int calDepth(Node node){
        int lDepth, rDepth;
        if(node.left != null){
            lDepth = node.left.depth;
        } else {
            lDepth = 0;
        }
        if(node.right != null){
            rDepth = node.right.depth;
        } else {
            rDepth = 0;
        }
        return Math.max(lDepth, rDepth) + 1;
    }
    private int calBalanceFactor(Node node){
        int lDepth, rDepth;
        if(node.left != null){
            lDepth = node.left.depth;
        } else {
            lDepth = 0;
        }
        if(node.right != null){
            rDepth = node.right.depth;
        } else {
            rDepth = 0;
        }
        return lDepth - rDepth;
    }
    private void genBstString(Node node, int depth, StringBuilder res){
        if(node == null){
            return;
        }
        res.append(String.format("%s%s\n",
                genDepthString(depth),
                node.e.toString()
        ));
        genBstString(node.left, depth+1, res);
        genBstString(node.right, depth+1, res);
    }
    private String genDepthString(int depth){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("-");
        }
        return builder.toString();
    }
    private E removeMin(){
        E ret = minimum(root).e;
        root = removeMin(root);
        return ret;
    }
    private Node minimum(Node root){
        if(root.left == null){
            return root;
        }

        return minimum(root.left);
    }
    private Node maximum(Node root){
        if(root.right == null){
            return root;
        }

        return maximum(root.right);
    }
    private Node removeMin(Node root){
        if(root.left==null){
            if(root.right != null){
                return root.right;
            } else{
                return null;
            }
        }

        root.left = removeMin(root.left);
        return root;
    }
    private Node removeMax(Node root){
        if(root.right==null){
            if(root.left != null){
                return root.left;
            } else{
                return null;
            }
        }

        root.right = removeMax(root.right);
        int balance = calBalanceFactor(root);
        if(balance>=2 && calBalanceFactor(root.left) >0){
            return rightRotate(root);
        }
        if(balance>=2 && calBalanceFactor(root.left)<0){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance<=-2 && calBalanceFactor(root.right)<0){
            return leftRotate(root);
        }
        if(balance<=-2 && calBalanceFactor(root.right)>0){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        root.depth = calDepth(root);
        return root;
    }
    public void removeEle(E e){
        root = removeEle(e, root);
    }
    private Node removeEle(E e, Node root){
        if(root==null){
            throw new IllegalArgumentException(String.format("Cant find %s", e.toString()));
        }
        if(root.e.equals(e)){
            size --;
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }
            Node candiate = maximum(root.left);
            Node newLeft= removeMax(root.left);
            candiate.left = newLeft;
            candiate.right = root.right;
            candiate.depth = calDepth(candiate);
            return candiate;
        }

        if(e.compareTo(root.e)>0){
            root.right = removeEle(e, root.right);
        } else{
            root.left = removeEle(e, root.left);
        }
        int balance = calBalanceFactor(root);
        if(balance>=2 && calBalanceFactor(root.left) >=0){
            return rightRotate(root);
        }
        if(balance>=2 && calBalanceFactor(root.left)<0){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance<=-2 && calBalanceFactor(root.right)<=0){
            return leftRotate(root);
        }
        if(balance<=-2 && calBalanceFactor(root.right)>0){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        root.depth = calDepth(root);
        return root;
    }
    private boolean isBalance(){
        return isBalance(root);
    }
    private boolean isBalance(Node node){
        if(node == null){
            return true;
        }
        if(Math.abs(calBalanceFactor(node)) >= 2){
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);
    }
    // rotate

    /**
     *
     * @param node
     * @return
     */
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    private Node rightRotate(Node y){
        Node T3 = y.left.right;
        Node x = y.left;
        x.right = y;
        y.left = T3;
        y.depth = calDepth(y);
        x.depth = calDepth(x);
        return x;
    }
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node T2 = y.right.left;
        Node x = y.right;
        x.left = y;
        y.right =T2;
        y.depth = calDepth(y);
        x.depth = calDepth(x);
        return x;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        genBstString(root, 0, builder);
        return builder.toString();
    }

    public static void testAVL(){

        AVLTree<String> avl = new AVLTree<>();
        BST<String> bst = new BST<>();
        Random random = new Random();

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());
        }
        System.out.println("Test avl tree");
        long startTs = System.nanoTime();
        for (String word: words1){
            avl.add(word);
        }
        for (String word: words1){
            avl.contains(word);
        }
        long endTs = System.nanoTime();
        System.out.println("Different words: " + avl.getSize());
        System.out.println("Avl is balance: " + avl.isBalance());
        System.out.println("Elapsed: " + (endTs - startTs) / 1000000000.0 +'s' );

        System.out.println("Test bst tree");
        long startTs2 = System.nanoTime();
        for (String word: words1){
            bst.add(word);
        }
        for (String word: words1){
            bst.contains(word);
        }
        long endTs2 = System.nanoTime();
        System.out.println("Different words: " + bst.getSize());
        System.out.println("Elapsed: " + (endTs2 - startTs2) / 1000000000.0 +'s' );

        // test ordered data
        AVLTree<Integer> avl2 = new AVLTree<>();
        BST<Integer> bst2 = new BST<>();
        int test_n;
        test_n = 10000;
        System.out.println(String.format("Test ordered data for AVL"));
        long startTs3 = System.nanoTime();
        for (int i = 0; i < test_n; i++) {
            avl2.add(i);
        }
        long endTs3 = System.nanoTime();
        System.out.println("Elapsed: " + (endTs3 - startTs3) / 1000000000.0 +'s' );

        System.out.println(String.format("Test ordered data for BST"));
        long startTs4 = System.nanoTime();
        for (int i = 0; i < test_n; i++) {
            bst2.add(i);
        }
        long endTs4 = System.nanoTime();
        System.out.println("Elapsed: " + (endTs4 - startTs4) / 1000000000.0 +'s' );
    }
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        for (int i = 0; i < 10; i++) {
            avl.add(i);
            System.out.println(avl.isBalance());
        }
        System.out.println(avl.isBalance());
        for (int i = 0; i < 10; i++) {
            avl.removeEle(i);
            System.out.println(avl.isBalance());
            System.out.println(avl.toString());
        }
        System.out.println(avl.getSize());
    }
}
