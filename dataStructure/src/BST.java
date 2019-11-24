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
//        System.out.println(bst.contains(3));
//        System.out.println(bst.contains(0));
//        bst.preOrder();
        System.out.println(bst);

    }

}
