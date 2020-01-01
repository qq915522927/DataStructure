public class UseMergeSortToSoveInverCount {

    public int mergeSort(int[] array){
        // 利用merge sort来求逆序对的数量
        return mergeSort(array, 0, array.length-1);

    }

    private int mergeSort(int[] array, int l, int r){
        if(l>=r){
            return 0;
        }
        int mid = l + (r-l) / 2;
        int lVerCount = mergeSort(array, l, mid);
        int rVerCount = mergeSort(array, mid+1, r);
        int cVerCount = merge(array, l, mid, r);
        return lVerCount + rVerCount + cVerCount;
    }
    private int merge(int[] array, int l, int m, int r){
        if(l>=r){
            return 0;
        }
        if(array[m] <= array[m+1]){
            return 0;
        }
        int[] temp = new int[ r - l + 1];
        for (int i = l; i <= r ; i++) {
            temp[i-l] = array[i];
        }
        int p = 0;
        int q = m + 1 - l;
        int tempMid = m - l;
        int verCount = 0;
        for (int i = l; i <= r ; i++) {
            if(p>tempMid && q> r -l){
                break;
            }
            if(p>tempMid){
                array[i] = temp[q];
                q++;
            } else if(q>r-l){
                array[i] = temp[p];
                p++;
            }
            else if(temp[p] <= temp[q]){
                array[i] = temp[p];
                p++;
            } else {
                array[i] = temp[q];
                q++;
                verCount += tempMid - p + 1;
            }
        }
        return verCount;
    }

    public static void main(String[] args) {
        UseMergeSortToSoveInverCount counter = new UseMergeSortToSoveInverCount();
        int[] array = {1, 2 ,3 ,3 ,1, 1, 2};
        System.out.println(counter.mergeSort(array));
        System.out.println("-----------");
        for (int i: array
             ) {
            System.out.println(i);

        }
    }
}
