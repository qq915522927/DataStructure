public interface Map<K, V>{
    void add(K k, V v);
    void set(K k, V v);
    V get(K k);
    boolean contains(K k);
    V remove(K k);
    int getSize();
    boolean isEmpty();
}
