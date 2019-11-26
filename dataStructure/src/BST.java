/**
 * Binary Search Tree
 * 特点是左子树所有元素都小于右子树所有元素, 当前节点为分界点
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }
    private int size;
    private Node root;
    public BST(){
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
        return root;
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
            if(root.left!=null){
                Node newRoot = removeMax(root.left);
                newRoot.left = root.left;
                return newRoot;
            }else if(root.right!=null){
                Node newRoot = removeMin(root.right);
                newRoot.right = root.right;
                return newRoot;
            }
            return null;
        }

        if(e.compareTo(root.e)>0){
            root.right = removeEle(e, root.right);
        } else{
            root.left = removeEle(e, root.left);
        }
        return root;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        genBstString(root, 0, builder);
        return builder.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(8);
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(4);
        bst.add(0);
        bst.add(9);
//        System.out.println(bst.contains(3));
//        System.out.println(bst.contains(0));
//        bst.preOrder();
//        System.out.println(bst);
//        System.out.println("#######mid order#############");
//        bst.midOrder();
//        System.out.println("#######post order#############");
//        bst.postOrder();
//        System.out.println("#######pre order#############");
//        bst.preOrder2();
//        System.out.println(bst);
//        System.out.println("Minimum "+bst.removeMin());
//        System.out.println(bst);
//        System.out.println("Minimum "+bst.removeMin());
//        System.out.println(bst);
//        System.out.println("Minimum "+bst.removeMin());
//        System.out.println(bst);
//        System.out.println("Minimum "+bst.removeMin());
//        System.out.println(bst);
        System.out.println(bst);
        System.out.println("#########remove 0###########");
        bst.removeEle(0);
        System.out.println(bst);
        System.out.println("#########remove 9###########");
        bst.removeEle(9);
        System.out.println(bst);
        System.out.println("#########remove 8###########");
        bst.removeEle(8);
        System.out.println(bst);

    }

}
