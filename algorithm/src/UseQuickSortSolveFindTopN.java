import java.util.Random;

public class UseQuickSortSolveFindTopN {


    private int quickSort(Integer[] array, int l, int r, int n){
        if(l>=r){
            return array[l];
        }
        // partition first
        Random random = new Random();
        swap(array, l , random.nextInt(r-l+1) + l);
        int pivotal = array[l];
        int j = l;
        for (int i = l+1; i <= r ; i++) {
            if(array[i]<pivotal){
                swap(array, i, j+1);
                j++;
            }
        }
        swap(array, l, j);

        if(j==n){
            return array[j];
        }
        if(n>j){
            return quickSort(array, j+1, r, n);
        } else{
            return quickSort(array, l, j-1, n);
        }
    }

    public static void swap(Object[] array, int i, int j){
        Object t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    public int solve(Integer[] array, int n){
        // return the value of the top n ele
        return quickSort(array, 0, array.length-1, n);

    }

    public static void main(String[] args) {
        UseQuickSortSolveFindTopN solver = new UseQuickSortSolveFindTopN();
        int count = 1000;
        Integer[] array = new Integer[count];
        for (int i = 0; i < count; i++) {
            array[i] = i;
        }
        System.out.println(solver.solve(array, 998));

    }
}
