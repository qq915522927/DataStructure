/**
 * The interface of UnionFind set
 * @param <E>
 */
public interface UF<E> {
    void union(int p, int q);
    boolean isConnected(int p, int q);
}
