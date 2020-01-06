import java.util.Vector;

public class DenseGraph implements Graph {

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
    public Iterable<Integer> adj(int v){
        assert v >= 0 && v < n;
        Vector<Integer> adjV = new Vector<>();
        for(int i=0; i < n; i++)
            if(g[v][i])
                adjV.add(i);
        return adjV;
    }
    public boolean hasEdge(int v,int w){
        return g[v][w];
    }

    @Override
    public void show() {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                builder.append(g[i][j]);
                builder.append(", ");
            }
            builder.append("\n");
        }
        builder.append("]\n");
        System.out.println(builder.toString());
    }

}
