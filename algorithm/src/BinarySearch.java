public class BinarySearch {

    // 二分搜索有序列表，如果找到返回对应ｉｎｄｅｘ, 否则返回　－１
    public static int find(Comparable[] array, Comparable e){
        if(array.length == 0){
            return -1;
        }
        int l = 0;
        int r = array.length - 1;
        while (l<=r){
            int mid = l + (r-l+1) / 2;
            if(e.compareTo(array[mid])==0){
                return mid;
            }
            if(e.compareTo(array[mid]) < 0){
                r = mid - 1;
            }
            if(e.compareTo(array[mid]) > 0){
                l = mid + 1;
            }
        }
        return -1;
    }

    public static int floor(Comparable[] array, Comparable e){
        if(array.length == 0){
            return -1;
        }
        int l = 0;
        int r = array.length - 1;
        int lastMid = -1;
        while (l<=r){
            int mid = l + (r-l+1) / 2;
            lastMid = mid;
            if(e.compareTo(array[mid])==0){
                return mid;
            }
            if(e.compareTo(array[mid]) < 0){
                r = mid - 1;
            }
            if(e.compareTo(array[mid]) > 0){
                l = mid + 1;
            }
        }
        int floor = -1;
        if(array[lastMid].compareTo(e) > 0){
            floor = lastMid - 1;
        } else{
            floor = lastMid;
        }
        return floor;
    }
    public static void main(String[] args) {
        int count = 30;
        Integer[] array = new Integer[count];
        for (int i = 0, j=0 ; i < count; i++, j+=2) {
            array[i] = j + 20;
        }

        TestHelper.printArray(array);
        System.out.println(find(array, 5));
        assert find(array, 5) == -1;
        // expect not find
        assert floor(array, 30) == 5;
        assert floor(array, 31) == 5;
        assert floor(array, 21) == 0;
        assert floor(array, 0) == -1;
        assert floor(array, -3) == -1;
    }
}
