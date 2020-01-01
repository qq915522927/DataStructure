    public class IndexHeap<T extends Comparable> {
        // 最小堆
        private int size;
        private T[] data;
        private int[] indexs;
        // data中 index对应在堆中的位置
        private int[] heapPositions;
        private int capacity;

        IndexHeap(int capaticy){
            data = (T[])new Comparable[capaticy];
            indexs = new int[capaticy + 1];
            heapPositions = new int[capaticy];
            size = 0;
        }

        IndexHeap(T[] array){
            data = array;
            size = array.length;
            indexs = new int[array.length + 1];
            for (int i = 0; i <= array.length ; i++) {
                indexs[i] = i - 1;
            }
            for (int i = parent(size); i >=1 ; i--) {
                shifDown(i);
            }
        }
        public void insert(int index, T ele) throws Exception {
            assert size + 1 <= capacity;
            assert index >= 0 && index < capacity;

            data[index] = ele;
            indexs[size+1] = index;
            heapPositions[index] = size+1;
            size ++;
            shiftUp(size);
            if(!isValid()){
                System.out.println(toString());
                throw new Exception("not valid");
            }
        }

        public T extractEle(){
            T res = data[indexs[1]];
            swap(1, size);
            size --;
            shifDown(1);
            return res;
        }

        private void shiftUp(int index){
            while (true)
            {
                int p = parent(index);
                if (p>0 && data[indexs[p]].compareTo(data[indexs[index]])>0){
                    swap(p, index);
                    heapPositions[indexs[index]] = index;
                    heapPositions[indexs[p]] = p;
                    index = p;
                } else{
                    break;
                }
            }
        }
        public void update(int index, T ele) throws Exception {
            data[index] = ele;
            int i = heapPositions[index];
            shifDown(i);
            shiftUp(i);
            if(!isValid()){
                throw new Exception("Not valid");
            }
        }

        private void shifDown(int index){
            while (true)
            {
                if(leftChild(index) > size)
                    break;
                int minIndex = leftChild(index);
                if(leftChild(index) + 1 <=size &&
                        data[indexs[leftChild(index)+1]].compareTo(data[indexs[minIndex]])<0)
                    minIndex = leftChild(index) + 1;
                if(data[indexs[index]].compareTo(data[indexs[minIndex]])>0){
                    swap(index, minIndex);
                    heapPositions[indexs[index]] = index;
                    heapPositions[indexs[minIndex]] = minIndex;
                } else {
                    break;
                }
                index = minIndex;
            }
        }
        public void swap(int i, int j){
            int t = indexs[i];
            indexs[i] = indexs[j];
            indexs[j] = t;
        }
        private int parent(int index){
            return index / 2;
        }
        private int leftChild(int index){
            return index * 2;
        }

        public int getSize(){
            return size;
        }
        public boolean isEmpty(){
            return size == 0;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append('\n');
            builder.append('[');
            for (int i = 1; i < size + 1; i++) {
                builder.append(indexs[i]);
                builder.append(", ");
            }
            builder.append(']');
            builder.append('\n');
            builder.append('[');
            for (int i = 1; i < size + 1; i++) {
                builder.append(data[indexs[i]]);
                builder.append(", ");
            }
            builder.append(']');
            return builder.toString();
        }
        public boolean isValid222(){
            boolean valid = true;
            for (int i = 1; i <= size ; i++) {
                if(leftChild(i) > size){
                    return true;
                }
                T min = data[indexs[leftChild(i)]];
                if(leftChild(i)+1 <= size && data[indexs[leftChild(i)+1]].compareTo(min) <0){
                    min = data[indexs[leftChild(i)+1]];
                }
                if(min.compareTo(data[indexs[i]]) < 0){
                    valid = false;
                    break;
                }
            }
            return valid;
        }
        public boolean isValid333(){
            boolean valid = true;
            T min = data[indexs[1]];
            for (int i = 2; i <= size ; i++) {
                if(data[indexs[i]].compareTo(min)<0){
                    valid = false;
                    break;
                }
            }
            return valid;
        }
        public boolean isValid(){
            return  isValid(1);
        }
        private boolean isValid(int root){
            if(leftChild(root) > size){
                return true;
            }
            if(leftChild(root) + 1 >size){
                return data[indexs[root]].compareTo(data[indexs[leftChild(root)]]) <= 0;
            }
            T min = data[indexs[leftChild(root)]];
            if(data[indexs[leftChild(root)+1]].compareTo(min) <0){
                min = data[indexs[leftChild(root)+1]];
            }


            return data[indexs[root]].compareTo(min) <= 0 && isValid(leftChild(root)) && isValid(leftChild(root)+1);

        }
        public static void testIndexHeap1() throws Exception {
            Integer[] array = new Integer[50];
            for (int i = 0; i < array.length; i++) {
                array[i] = i+30;
            }
            TestHelper.suffleArray(array);
            IndexHeap<Integer> heap = new IndexHeap<Integer>(array);
            System.out.println(heap);
            heap.update(33, 100000);
            System.out.println(heap);
            Integer[] resArray = new Integer[50];
            for (int i = 0; i < resArray.length; i++) {
                resArray[i] = heap.extractEle();
                System.out.println(resArray[i]);
                System.out.println(heap);
            }
            TestHelper.checkOrder(resArray);

        }
        public static void testIndexHeap2() throws Exception {
            Integer[] array = new Integer[50];
            for (int i = 0; i < array.length; i++) {
                array[i] = i+30;
            }
            TestHelper.suffleArray(array);
            IndexHeap<Integer> heap = new IndexHeap<Integer>(50);
            for (int i = 0; i < array.length; i++) {
                heap.insert(i, array[i]);
                System.out.println(heap);
            }
//            System.out.println(heap);
            for (int i = 0; i < 30 ; i++) {

                heap.update(i, i);
            }
            System.out.println(heap);
            Integer[] resArray = new Integer[50];
            for (int i = 0; i < resArray.length; i++) {
                resArray[i] = heap.extractEle();
//                System.out.println(resArray[i]);
//                System.out.println(heap);
            }
            TestHelper.checkOrder(resArray);

        }
        public  static void main(String[] args) throws Exception {
            testIndexHeap2();
        }
    }
