import java.util.*;

public class MaxHeapPractice {
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int n: nums){
            if(map.containsKey(n)){
                map.put(n, map.get(n)+1);
            } else {
                map.put(n, 1);
            }
        }
        PriorityQueue<Integer> pqueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t0, Integer t1) {

                return map.get(t0) - map.get(t1);
            }
        });
        for(Integer key: map.keySet()){
            if(pqueue.size()>=k){
                Integer minKey = pqueue.peek();
                if(map.get(key) >= map.get(minKey)){
                    pqueue.remove();
                    pqueue.add(key);
                }
            }
            else {
                    pqueue.add(key);
                }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(pqueue.remove());
        }
        List<Integer> resList = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0 ; i--) {
            resList.add(list.get(i));
        }
        return resList;
    }

    public static void main(String[] args) {
        int[] test = {1,1,1,2,2,3};
        int k = 2;
        MaxHeapPractice sol = new MaxHeapPractice();
        System.out.println(sol.topKFrequent(test, k).toString());
    }
}
