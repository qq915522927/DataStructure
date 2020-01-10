import java.util.Vector;

public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph  {
    // 稠密图，　使用邻接矩阵
    private int n; // ｎｕｍ of node
    private int m; // num of edge
    private boolean directed;
    private Edge<Weight>[][] g;

    public DenseWeightedGraph(int n, boolean directed){
        assert n>0;
        this.n = n;
        this.m = 0;
        this.directed = directed;

        // 初始化全部为false,表示没有任何边
        g = new Edge[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = null;
            }
        }
    }
    public int V(){ return n;}
    public int E(){ return m;}
    public void addEdge(Edge edge){
        assert edge.v() >= 0 && edge.v() < n;
        assert edge.w() >= 0 && edge.w() < n;
        if(hasEdge(edge.v(), edge.w()))
            return;
        g[edge.v()][edge.w()] = edge;
        if(edge.v() != edge.w() && !directed)
            g[edge.w()][edge.v()] = edge;
        m++;
    }
    public Iterable<Edge<Weight>> adj(int v){
        // 返回所有领边
        assert v >= 0 && v < n;
        Vector<Edge<Weight>> adjV = new Vector<>();
        for(int i=0; i < n; i++)
            if(g[v][i] != null)
                adjV.add(g[v][i]);
        return adjV;
    }
    public boolean hasEdge(int v,int w){
        return g[v][w] != null;
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

    public static void main(String[] args) {
        DenseWeightedGraph<Double> g = new DenseWeightedGraph<>(8, false);
        ReadWeightedGraph reader = new ReadWeightedGraph(g, "weightedG.txt");
        g.show();
    }
}
