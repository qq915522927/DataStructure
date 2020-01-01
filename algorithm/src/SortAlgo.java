import java.util.Random;

public class SortAlgo {
    public static <T extends Comparable> void insetSort(T[] array){
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j>=1 && array[j].compareTo(array[j-1])<0 ; j--) {
                TestHelper.swap(j, j-1, array);
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
    public static <T extends Comparable> void mergeSort(T[] array){
        mergeSort(array, 0, array.length-1);
    }
    public static <T extends Comparable> void mergeSortOptimized(T[] array){
        mergeSortOptimized(array, 0, array.length-1);
    }
    public static <T extends Comparable> void quickSort2Ways(T[] array){
        quickSort2Ways(array, 0, array.length-1);
    }
    public static <T extends Comparable> void quickSort3Ways(T[] array){
        quickSort3Ways(array, 0, array.length-1);
    }

    private static <T extends Comparable> void quickSort3Ways(T[] array, int l, int r){
        if((r-l)<15){
            insetSortOptimized(array, l, r);
            return;
        }
        // partition
        Random random = new Random();
        TestHelper.swap(l, random.nextInt(r-l+1) + l, array);
        T pivotal = array[l];
        int lt = l;
        int gt = r + 1;
        int i = l+1;
        while (i<gt){
            if(array[i].compareTo(pivotal)>0){
                TestHelper.swap(gt-1, i, array);
                gt --;
            }
            if(array[i].compareTo(pivotal)<0){
                TestHelper.swap(i, lt+1, array);
                lt++;
                i++;
            }
            if(array[i].compareTo(pivotal)==0){
                i++;
            }
        }
        TestHelper.swap(l, lt, array);
        lt --;

        quickSort3Ways(array, l, lt);
        quickSort3Ways(array, gt, r);

    }
    private static <T extends Comparable> void quickSort2Ways(T[] array, int l, int r){
        if((r-l) <= 15){
            insetSortOptimized(array, l, r);
            return;}
        int m = partition2ways(array, l, r);
        quickSort2Ways(array, l, m-1);
        quickSort2Ways(array, m+1, r);
    }
    private static <T extends Comparable> int partition2ways(T[] array, int l, int r){
        Random random = new Random();
        int maxIndex = r+1;
        TestHelper.swap(l, random.nextInt(r-l+1) + l, array);
        T flag = array[l];
        int i = l + 1;
        int j = r;
        while(true){
            while (i<=r && array[i].compareTo(flag)<0){
                i++;
            }
            while (j>=l+1 && array[j].compareTo(flag)>0){
                j--;
            }
            if(i>j)
                break;
            //这里有一个隐含条件，即，array[i] == array[j]
            TestHelper.swap(i, j, array);
            i++;
            j--;

        }
        assert i-1 == j;
        TestHelper.swap(l, i-1, array);
        return i-1;


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
        quickSort(array, l, mid-1);
        quickSort(array, mid+1, r);

    }
    private static <T extends Comparable> void quickSort(T[] array, int l, int r){
        // l close, r close
        if(l>=r)
            return;
        int mid = partition(array, l, r);
        quickSort(array, l, mid - 1);
        quickSort(array, mid+1, r);

    }
    private static <T extends Comparable> int partition(T[] array, int l, int r){
        Random random = new Random();
        TestHelper.swap(l, random.nextInt(r - l) + l, array);
        T flagEle = array[l];
        int mid = l;
        for (int i = l+1; i <r+1 ; i++) {
            if (flagEle.compareTo(array[i]) > 0) {
                TestHelper.swap(mid + 1, i, array);
                mid++;
            }
        }
        TestHelper.swap(l, mid, array);
        return mid;
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

    public static void main(String[] args) throws Exception {
//        Sort<Integer> sort1 = (arr)->{mergeSort(arr);};
//        testSort(sort1, 100000, "Merge sort1");

        int count = 1000000;
        boolean ordered = true;
        System.out.println(String.format("Test for Count %s --------", count));
//        Sort<Integer> sort2 = (arr)->{insetSort(arr);};
//        testSort(sort2, count, "Insert sort", ordered);
//        Sort<Integer> sort3 = (arr)->{insetSortOptimized(arr);};
//        testSort(sort3, count, "Insert sort Optimized", ordered);

        TestHelper.Sort<Integer> sortMerge = (arr)->{mergeSort(arr);};
        TestHelper.testSort(sortMerge, count, "Merge sort", ordered);

        TestHelper.Sort<Integer> sortMerge2 = (arr)->{mergeSortOptimized(arr);};
        TestHelper.testSort(sortMerge2, count, "Merge sort optimized", ordered);

//        TestHelper.Sort<Integer> sortQuick = (arr)->{quickSort(arr);};
//        TestHelper.testSort(sortQuick, count, "Quick sort", ordered);

        TestHelper.Sort<Integer> sortQuickOptimized = (arr)->{quickSortOptimized(arr);};
        TestHelper.testSort(sortQuickOptimized, count, "Quick sort optimized", ordered);

        TestHelper.Sort<Integer> sortQuick2Ways = (arr)->{ quickSort2Ways(arr);};
        TestHelper.Sort<Integer> sortQuick3Ways = (arr)->{ quickSort3Ways(arr);};

        TestHelper.testSort(sortQuick2Ways, count, "Quick sort 2 ways", ordered);
        TestHelper.testSort(sortQuick3Ways, count, "Quick sort 3 ways", ordered);

        HeapSort heapSortWrap = new HeapSort();
        TestHelper.Sort<Integer> heapSort = heapSortWrap::heapSort;
        TestHelper.testSort(heapSort, count, "Heap sort", ordered);
        // test duplicated array
        System.out.println("+++++++++++test duplicated items+++++++++");
        boolean duplicated = true;
//        testSort(sortQuickOptimized, count, "Quick sort optimized with most duplicated", ordered, duplicated);

        TestHelper.testSort(sortQuick2Ways, count, "Quick sort 2 ways with most duplicated", ordered, duplicated);

        TestHelper.testSort(sortQuick3Ways, count, "Quick sort 3 ways with most duplicated", ordered, duplicated);

        TestHelper.testSort(heapSort, count, "Heap sort with most duplicated", ordered, duplicated);

    }
}
