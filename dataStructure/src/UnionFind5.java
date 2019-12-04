/**
 * optimized by using compressed path
 * @param <E>
 */
public class UnionFind5<E> implements UF<E> {
    private E[] data;
    private int[] parents;
    private int[] rank;
    public UnionFind5(E[] data){
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
     * @param p
     * @return
     */
    private int find(int p){
        while (parents[p] != p){
            parents[p] = parents[parents[p]];
            p = parents[p];
        }
        return p;
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
        UnionFind5<Integer> uf = new UnionFind5<>(data);
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
