/**
 * Use tree to implement UnionFind set
 * @param <E>
 */
public class UnionFind2<E> implements UF<E> {
    private E[] data;
    private int[] parents;
    public UnionFind2(E[] data){
        this.data = data;
        parents = new int[data.length];
        for (int i = 0; i < data.length ; i++) {
            parents[i] = i;
        }
    }
    @Override
    public void union(int p, int q) {
        int groupP = find(p);
        int groupQ = find(q);
        if(groupP == groupQ){
            return;
        }
        parents[groupP] = groupQ;

    }

    /**
     * Find the group of a e at index p
     * @param p
     * @return
     */
    private int find(int p){
        while (parents[p] != p){
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
        UnionFind2<Integer> uf = new UnionFind2<>(data);
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
