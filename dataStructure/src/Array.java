public class Array {
    private int[] data;
    private int size;

    /**
     * 构造函数， 创建传入capacity大小的数组
     * @param capacity
     */
    public Array(int capacity){
        data = new int[capacity];
        size = 0;
    }

    /**
     * 无参数构造函数
     */
    public Array(){
        this(10);
    }
    public int getSize(){
        return size;
    }
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void addLast(int e){
        add(size, e);

    }
    public void add(int index, int e){
        if(index>size || index<0){
            throw new IllegalArgumentException("Index must >=0 <=size");
        }
        if(size==data.length){
            throw new IllegalArgumentException("Array is Full");
        }
        //把要添加位置后的元素全部后移一格
        for(int i = size-1; i>=index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;

    }
    public int get(int index){

        if(index>size || index<0){
            throw new IllegalArgumentException("Index must >=0 <=size");
        }
        return data[index];
    }
    public int set(int index, int e){

        if(index>size || index<0){
            throw new IllegalArgumentException("Index must >=0 <=size");
        }
        return data[index] = e;
    }
    public boolean contains(int e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    /**
     * 返回 index
     * @param e
     * @return
     */
    public int find(int e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除indexc处的元素，返回删除的元素
     * @param index
     * @return
     */
    public int remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Invalid index");
        }
        int res = data[index];
        // index位置后的所有元素向前移动一格
        for (int i = index + 1; i < size ; i++) {
            data[i-1] = data[i];
        }
        size--;
        return res;
    }
    public int removeFirst(){
        return remove(0);
    }
    public int removeLast(){
        return remove(size);
    }
    public void removeElement(int e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size=%d, capacity=%d\n", size, getCapacity()));
        res.append('[');
        for (int i = 0; i < size ; i++) {
            res.append(data[i]);
            if(i != size-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Array array = new Array(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(1, 200);
        System.out.println(array);
        array.add(0, -2);
        System.out.println(array);
        array.set(0, -1);
        System.out.println(array);
//        for (int i = 0; i < array.getSize() ; i++) {
//            System.out.println(array.get(i));
//        }
        array.removeElement(200);
        System.out.println(array);
        System.out.println("remove:" + array.removeFirst());
        System.out.println(array);
        System.out.println(array.find(200));
        System.out.println(array.find(2));
        System.out.println(array.contains(200));
        System.out.println(array.contains(2));
    }
}
