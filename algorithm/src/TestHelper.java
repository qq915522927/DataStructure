import java.util.Random;

public class TestHelper {
    public interface Sort<T>{
        public  void sort(T[] array);
    }
    public static  <T> void suffleArray(T[] array){
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            swap(0, random.nextInt(array.length), array);
        }
    }
    public static void printArray(Object[] array){
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i].toString());
            builder.append(", ");
        }
        builder.append(']');
        System.out.println(builder.toString());
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
    public static Integer[] generateRandomArray(int count) throws Exception {
        Integer[] res = new Integer[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            res[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return res;
    }
    public static Integer[] generateArrayWithDuplicates(int count, int diffCount) throws Exception {
        Integer[] res = new Integer[count];
        Random random = new Random();
        int n = random.nextInt(Integer.MAX_VALUE);
        for (int i = 0; i < count; i++) {
            res[i] = n;
        }
        for (int i = 0; i < diffCount; i++) {
            int index = random.nextInt(count);
            res[index] = random.nextInt(Integer.MAX_VALUE);
        }

        return res;
    }

    public static <T> void swap(int i, int j,T[] array){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
    public static <T> void testSort(Sort algo, int count, String name, boolean nearlyOrdered) throws Exception {
        testSort(algo, count, name, nearlyOrdered, false);
    }
    public static <T> void testSort(Sort algo, int count, String name, boolean nearlyOrdered, boolean mostDuplicated) throws Exception {

        long start = System.nanoTime();
        Integer[] array;
        if(!nearlyOrdered)
            array = generateRandomArray(count) ;
        else
            array = generateNeayOrderedArray(count, 30);

        if(mostDuplicated)
            array = generateArrayWithDuplicates(count, 30);

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
}
