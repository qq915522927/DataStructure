import java.util.Random;

public class SortAlgo {
    public static <T extends Comparable> void insetSort(T[] array){
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j>=1 && array[j].compareTo(array[j-1])<0 ; j--) {
                swap(j, j-1, array);
            }
        }

    }
    private static <T> void swap(int i, int j,T[] array){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = generateRandomArray(10) ;
        insetSort(array);
        checkOrder(array);
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
}
