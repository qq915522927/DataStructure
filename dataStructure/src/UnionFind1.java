/**
 * Most simplest implemantation using a group of id
 * @param <E>
 */
public class UnionFind1<E> implements UF<E>{
    private E[] data;
    private int[] groups;
    public UnionFind1(E[] data){
        this.data = data;
        groups = new int[data.length];
        for (int i = 0; i < data.length ; i++) {
            groups[i] = i;
        }
    }
    @Override
    public void union(int p, int q) {
        int groupP = groups[p];
        int groupQ = groups[q];
        for (int i = 0; i < groups.length; i++) {
            if(groups[i] == groupP){
                groups[i] = groupQ;
            }
        }

    }

    @Override
    public boolean isConnected(int p, int q) {
        int groupP = groups[p];
        int groupQ = groups[q];
        return groupP == groupQ;
    }

    public static void main(String[] args) {
        int n = 10;
        Integer[] data = new Integer[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
        UnionFind1<Integer> uf = new UnionFind1<>(data);
        System.out.println(uf.isConnected(1 ,2));
        uf.union(1, 2);
        System.out.println(uf.isConnected(1 ,2));
        uf.union(2,3);
        System.out.println(uf.isConnected(1 ,3));
        System.out.println(uf.isConnected(1 ,4));
    }
}
