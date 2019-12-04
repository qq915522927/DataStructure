import java.util.Random;

public class TestUninonFInd {
    public static void main(String[] args) {
        int dataN = 100000;
        int testN = 1000000;
        Integer[] data = new Integer[dataN];
        for (int i = 0; i < dataN; i++) {
            data[i] = i;
        }
        UnionFind1<Integer> uf1 = new UnionFind1<>(data);
        UnionFind2<Integer> uf2 = new UnionFind2<>(data);
        UnionFind3<Integer> uf3 = new UnionFind3<>(data);
        UnionFind4<Integer> uf4 = new UnionFind4<>(data);
        UnionFind5<Integer> uf5 = new UnionFind5<>(data);
//        System.out.println("Test for uf1");
//        testUF(uf1, dataN, testN);
//        System.out.println("Test for uf2, use tree");
//        testUF(uf2, dataN, testN);
        System.out.println("Test for uf3, optimized by size");
        testUF(uf3, dataN, testN);
        System.out.println("Test for uf4, optimized by rank");
        testUF(uf4, dataN, testN);
        System.out.println("Test for uf5, optimized by compressed path");
        testUF(uf5, dataN, testN);
    }
    private static void testUF(UF uf, int dataN, int testN){
        Random random = new Random();
        long startTs = System.nanoTime();
        for (int i = 0; i < testN; i++) {
            uf.union(random.nextInt(dataN), random.nextInt(dataN));
        }
        for (int i = 0; i < testN; i++) {
            uf.isConnected(random.nextInt(dataN), random.nextInt(dataN));
        }
        long endTs = System.nanoTime();
        System.out.println("Elapsed: " + (endTs - startTs) / 1000000000.0 + "s");
    }
}
