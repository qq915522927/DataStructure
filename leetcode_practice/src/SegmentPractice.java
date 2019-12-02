public class SegmentPractice {
    public interface Merger<E> {
        E merge(E e1, E e2);
    }

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

        /**
         * 递归
         * @param index
         * @param e
         */
        public void update(int index, E e){
            data[index] = e;
            updateTree(0, 0, data.length-1, index, e);
        }
        private void updateTree(int nodeIndex, int segL, int segR, int updateIndex, E e){
            if(segL == segR){
                tree[nodeIndex] = e;
                return;
            }
            int mid = segL + (segR - segL) / 2;
            if(updateIndex <= mid){
                // update left tree
                updateTree(leftChild(nodeIndex), segL, mid, updateIndex, e);
            } else{
                // update right tree
                updateTree(rightChild(nodeIndex), mid + 1, segR, updateIndex, e);
            }
            tree[nodeIndex] = (E)merger.merge(tree[leftChild(nodeIndex)], tree[rightChild(nodeIndex)]);
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

    }

    private SegmentTree<Integer> tree;
    private Integer[] data;

    public SegmentPractice(int[] nums) {
        if(nums.length==0){
            return;
        }
        data = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        tree = new SegmentTree<>(data, (a,b)->a+b);

    }

    public void update(int i, int val) {
        tree.update(i, val);

    }

    public int sumRange(int i, int j) {
        return tree.query(i, j);

    }
}
