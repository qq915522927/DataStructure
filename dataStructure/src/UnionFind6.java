/**
 * optimized by using compressed path
 * @param <E>
 */
public class UnionFind6<E> implements UF<E> {
    private E[] data;
    private int[] parents;
    private int[] rank;
    public UnionFind6(E[] data){
        this.data = data;
        parents = new int[data.length];
        rank = new int[data.length];
        for (int i = 0; i < data.length ; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < data.length ; i++) {
            rank[i] = 1;
        }
    }
    @Override
    public void union(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);
        if(groupP == groupQ){
            return;
        }
        if(rank[groupP] < rank[groupQ]){
            parents[groupP] = groupQ;
        } else if(rank[groupP] > rank[groupQ]){
            parents[groupQ] = groupP;
        } else{
            // equal rank
            parents[groupQ] = groupP;
            rank[groupP] += 1;
        }

    }

    /**
     * Find the group of a e at index p
     * compress the path during find
     * 递归实现
     * @param p
     * @return
     */
    private int find(int p){
        if(parents[p] == p){
            return p;
        }
        parents[p] = find(parents[p]);
        return parents[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);
        return groupP == groupQ;
    }

    public static void main(String[] args) {
        int n = 10;
        Integer[] data = new Integer[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
        UnionFind6<Integer> uf = new UnionFind6<>(data);
        System.out.println(uf.isConnected(1 ,2));
        uf.union(1, 2);
        System.out.println(uf.isConnected(1 ,2));
        uf.union(2,3);
        System.out.println(uf.isConnected(1 ,3));
        System.out.println(uf.isConnected(1 ,4));
        uf.union(2,4);
        System.out.println(uf.isConnected(1 ,4));
    }
}
