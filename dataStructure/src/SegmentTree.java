public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger merger;
    public SegmentTree(E[] data, Merger<E> merger){
        this.merger = merger;
        this.data = (E[]) new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i];
        }
        tree = (E[])new Object[4 * data.length];
        buildSegTree(0, 0, data.length - 1);
    }
    private void buildSegTree(int rootIndex, int startIndex, int endIndex){
        if(startIndex==endIndex){
            tree[rootIndex] = data[startIndex];
            return;
        }
        int midIndex = startIndex + (endIndex -startIndex) / 2;
        buildSegTree(leftChild(rootIndex), startIndex, midIndex);
        buildSegTree(rightChild(rootIndex), midIndex+1, endIndex);
        tree[rootIndex] = (E) merger.merge(
                tree[leftChild(rootIndex)],
                tree[rightChild(rootIndex)]
        );
        return;
    }
    private int leftChild(int index){
        return index*2 + 1;
    }
    private int rightChild(int index){
        return index*2 + 2;
    }
    public E query(int startQ, int engdQ){

        return query(startQ, engdQ, 0, 0, data.length - 1);
    }

    /**
     * 递归实现， 分情况
     * @param startQ
     * @param endQ
     * @param nodeIndex
     * @param segStart
     * @param segEnd
     * @return
     */
    private E query(int startQ, int endQ, int nodeIndex, int segStart, int segEnd){
        if(startQ < segStart || endQ > segEnd || endQ < startQ){
            throw new IllegalArgumentException("invalid query");
        }
        if(startQ == segStart && endQ == segEnd){
            return tree[nodeIndex];
        }
        int midIndex = segStart + (segEnd - segStart) / 2;
        if(endQ <= midIndex){
            return query(startQ, endQ, leftChild(nodeIndex), segStart, midIndex);
        }
        if(startQ>midIndex){
            return query(startQ, endQ, rightChild(nodeIndex), midIndex+1, segEnd);
        }
        E leftRes = query(startQ, midIndex, leftChild(nodeIndex), segStart, midIndex);
        E rightRes = query(midIndex+1, endQ, rightChild(nodeIndex), midIndex+1, segEnd);
        if(leftRes == null || rightRes ==null){
            System.out.println();
        }
        return (E) merger.merge(leftRes, rightRes);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] == null){
                builder.append("Null");
            } else {
                builder.append(tree[i].toString());
            }
            builder.append(", ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[10];
        for (int i = 0; i < 10 ; i++) {
            data[i] = i;
        }
        SegmentTree<Integer> tree = new SegmentTree(data, new Merger<Integer>() {
            @Override
            public Integer merge(Integer e1, Integer e2) {
                return e1 + e2;
            }
        });
        System.out.println(tree);
        System.out.println("Query 0, 3, expect 6");
        System.out.println(tree.query(0, 3));

        System.out.println("Query 0, 9, expect 45");
        System.out.println(tree.query(0, 9));

        System.out.println("Query 3, 6, expect 18");
        System.out.println(tree.query(3, 6));
    }
}
