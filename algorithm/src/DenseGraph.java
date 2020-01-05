public class DenseGraph {

    // 稠密图，　使用邻接矩阵
    private int n; // ｎｕｍ of node
    private int m; // num of edge
    private boolean directed;
    private boolean[][] g;

    public DenseGraph(int n, boolean directed){
        assert n>0;
        this.n = n;
        this.m = 0;
        this.directed = directed;

        // 初始化全部为ｆａｌｓｅ,表示没有任何边
        g = new boolean[n][n];
    }
    public int V(){ return n;}
    public int E(){ return m;}
    public void addEdge(int v, int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        if(hasEdge(v, w))
            return;
        g[v][w] = true;
        m++;
    }
    private boolean hasEdge(int v,int w){
        return g[v][w];
    }

}
