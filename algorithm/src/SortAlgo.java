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
    public static <T> void testSort(Sort algo, int count, String name) throws Exception {
        System.out.println("-----Test "+name + "----");
        long start = System.nanoTime();
        Integer[] array = generateRandomArray(count) ;
        algo.sort(array);
        long end = System.nanoTime();
        System.out.println(String.format("Elapsed %f", (end-start)/1000000000.0));
        checkOrder(array);
    }
    public static void main(String[] args) throws Exception {
        Sort<Integer> sort1 = (arr)->{mergeSort(arr);};
        testSort(sort1, 100000, "Merge sort");

        Sort<Integer> sort2 = (arr)->{insetSort(arr);};
        testSort(sort2, 100000, "Insert sort");
    }
}
