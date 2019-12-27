import java.util.Random;

public class SortAlgo {
    public interface Sort<T>{
        public  void sort(T[] array);
    }
    public static <T extends Comparable> void insetSort(T[] array){
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j>=1 && array[j].compareTo(array[j-1])<0 ; j--) {
                swap(j, j-1, array);
            }
        }
    }
    public static <T extends Comparable> void insetSortOptimized(T[] array){
        for (int i = 1; i < array.length; i++) {
            int insPos =i;
            T cur = array[i];
            for (int j = i; j>=1 && cur.compareTo(array[j-1])<0 ; j--) {
                insPos --;
                array[j] = array[j-1];
            }
            array[insPos] = cur;
        }
    }
    private static <T extends Comparable> void quickSortOptimized(T[] array, int l, int r){
        // l close, r close
        // combine insert sort
        if(l>=r)
            return;
        if(r-l<10){
            insetSortOptimized(array, l, r);
            return;
        }
        int mid = partition(array, l, r);
        quickSort(array, l, mid);
        quickSort(array, mid+1, r);

    }
    private static <T extends Comparable> void quickSort(T[] array, int l, int r){
        // l close, r close
        if(l>=r)
            return;
        int mid = partition(array, l, r);
        quickSort(array, l, mid);
        quickSort(array, mid+1, r);

    }
    private static <T extends Comparable> int partition(T[] array, int l, int r){
        Random random = new Random();
        swap(l, random.nextInt(r - l) + l, array);
        T flagEle = array[l];
        int mid = l;
        for (int i = l+1; i <r+1 ; i++) {
            if (flagEle.compareTo(array[i]) > 0) {
                swap(mid + 1, i, array);
                mid++;
            }
        }
        swap(l, mid, array);
        return mid;
    }

    public static <T extends Comparable> void quickSort(T[] array){
        quickSort(array, 0, array.length-1);

    }
    public static <T extends Comparable> void quickSortOptimized(T[] array){
        quickSortOptimized(array, 0, array.length-1);

    }
    public static <T extends Comparable> void insetSortOptimized(T[] array, int l, int r){
//        Insert sort the [l, r] of a array
        for (int i = l; i < r+1; i++) {
            int insPos =i;
            T cur = array[i];
            for (int j = i; j>=1 && cur.compareTo(array[j-1])<0 ; j--) {
                insPos --;
                array[j] = array[j-1];
            }
            array[insPos] = cur;
        }
    }
    public static <T extends Comparable> void mergeOptimized(T[] array, int l, int m, int r){
        // merge for left close, right close
        // m blong to left
        if(l>m || m==r)
            return;
        if(array[m].compareTo(array[m+1])<0)
            return;
        T[] temp = (T[])new Comparable[r-l+1];
        for (int i = l; i <=r ; i++) {
            temp[i-l] = array[i];
        }
        int tempMid = m-l;
        int i = 0;
        int j = tempMid +1;
        int cur = l;
        while (true){
            if(i>tempMid && j<=temp.length-1){
                array[cur] = temp[j];
                j++;
            } else if(j > temp.length-1 && i<=tempMid){
                array[cur] = temp[i];
                i++;
            } else if(i>tempMid&& j > temp.length-1){
                break;
            }
            else if(temp[i].compareTo(temp[j])<0){
                array[cur] = temp[i];
                i ++;
            } else if(j<=temp.length-1) {
                array[cur] = temp[j];
                j++;
            }
            cur ++;
        }

    }
    public static <T extends Comparable> void merge(T[] array, int l, int m, int r){
        // merge for left close, right close
        // m blong to left
        T[] temp = (T[])new Comparable[r-l+1];
        for (int i = l; i <=r ; i++) {
            temp[i-l] = array[i];
        }
        int tempMid = m-l;
        int i = 0;
        int j = tempMid +1;
        int cur = l;
        while (true){
            if(i>tempMid && j<=temp.length-1){
                array[cur] = temp[j];
                j++;
            } else if(j > temp.length-1 && i<=tempMid){
                array[cur] = temp[i];
                i++;
            } else if(i>tempMid&& j > temp.length-1){
                break;
            }
            else if(temp[i].compareTo(temp[j])<0){
                array[cur] = temp[i];
                i ++;
            } else if(j<=temp.length-1) {
                array[cur] = temp[j];
                j++;
            }
            cur ++;
        }

    }
    private static <T extends Comparable> void mergeSortOptimized(T[] array, int l, int r){
        // left close, right close
        if(l>=r){
            return;
        }
        int mid = l + (r-l)/2;
        mergeSortOptimized(array, l, mid);
        mergeSortOptimized(array, mid+1, r);
        mergeOptimized(array, l, mid, r);

    }
    private static <T extends Comparable> void mergeSort(T[] array, int l, int r){
        // left close, right close
        if(l>=r){
            return;
        }
        int mid = l + (r-l)/2;
        mergeSort(array, l, mid);
        mergeSort(array, mid+1, r);
        merge(array, l, mid, r);

    }
    public static <T extends Comparable> void mergeSort(T[] array){
        mergeSort(array, 0, array.length-1);
    }
    public static <T extends Comparable> void mergeSortOptimized(T[] array){
        mergeSortOptimized(array, 0, array.length-1);
    }
    private static <T> void swap(int i, int j,T[] array){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static Integer[] generateRandomArray(int count) throws Exception {
        Integer[] res = new Integer[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            res[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return res;
    }
    public static Integer[] generateNeayOrderedArray(int count, int numNotOrder) throws Exception {
        Integer[] res = new Integer[count];
        for (int i = 0; i < count; i++) {
            res[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < numNotOrder; i++) {
            int p = random.nextInt(count-1);
            int q = random.nextInt(count-1);
            swap(p, q, res);

        }
        return res;
    }

    public static Boolean checkOrder(Integer[] array) throws Exception {
        for (int i = 0; i < array.length-1; i++) {
            if(array[i]>array[i+1]){
                System.out.println("The array is not ordered");
                throw new Exception("Invalid order");
            }
        }
        System.out.println("The array is ordered");
        return true;
    }
    public static <T> void testSort(Sort algo, int count, String name, boolean nearlyOrdered) throws Exception {

        long start = System.nanoTime();
        Integer[] array;
        if(!nearlyOrdered)
            array = generateRandomArray(count) ;
        else
            array = generateNeayOrderedArray(count, 30);
        StringBuilder builder = new StringBuilder();
        builder.append("-------Test ");
        builder.append(name);
        builder.append("-----");
        if(nearlyOrdered )
            builder.append("Ordered");
        else
            builder.append("Random");
        builder.append("-----");
        System.out.println(builder.toString());
        algo.sort(array);
        long end = System.nanoTime();
        System.out.println(String.format("Elapsed %f", (end-start)/1000000000.0));
        checkOrder(array);
    }
    public static void main(String[] args) throws Exception {
//        Sort<Integer> sort1 = (arr)->{mergeSort(arr);};
//        testSort(sort1, 100000, "Merge sort1");

        int count = 10000000;
        boolean ordered = false;
        System.out.println(String.format("Test for Count %s --------", count));
//        Sort<Integer> sort2 = (arr)->{insetSort(arr);};
//        testSort(sort2, count, "Insert sort", ordered);
//        Sort<Integer> sort3 = (arr)->{insetSortOptimized(arr);};
//        testSort(sort3, count, "Insert sort Optimized", ordered);

        Sort<Integer> sortMerge = (arr)->{mergeSort(arr);};
        testSort(sortMerge, count, "Merge sort", ordered);

        Sort<Integer> sortMerge2 = (arr)->{mergeSortOptimized(arr);};
        testSort(sortMerge2, count, "Merge sort optimized", ordered);

        Sort<Integer> sortQuick = (arr)->{quickSort(arr);};
        testSort(sortQuick, count, "Quick sort", ordered);

        Sort<Integer> sortQuickOptimized = (arr)->{quickSortOptimized(arr);};
        testSort(sortQuickOptimized, count, "Quick sort optimized", ordered);
    }
}
