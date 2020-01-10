import java.util.ArrayList;
import java.util.Vector;

public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph<Weight> {
    private int n;
    private int m;
    private boolean directed;
    private Vector<Edge<Weight>>[] g;

    public SparseWeightedGraph(int n, boolean directed){
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;
        // 初始化为ｎ个空的邻接表，　表示没有任何边
        g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Edge<Weight>>();
        }
    }
    public int V(){
        return n;
    }
    public int E(){
        return m;
    }
    public void addEdge(Edge e){
        assert e.v() >= 0 && e.v() < n;
        assert e.w() >= 0 && e.w() < n;
        g[e.v()].add(e);
        if(e.v()!=e.w() && !directed)
            // 无向图，　两边都要加上边
            g[e.w()].add(e);
        m++;
    }
    public boolean hasEdge(int v, int w){
        for (int i = 0; i < g[v].size(); i++) {
            if(g[v].elementAt(i).other(v) == w)
                return true;
        }
        return false;
    }

    public void DFS(int v){
        boolean[] arrived = new boolean[n];
        ArrayList<Integer> nodes = new ArrayList<>();
        DFS(v, arrived, nodes);
        System.out.println("Depth First Result: " + nodes);
    }
    private void DFS(int v, boolean[] arrived, ArrayList<Integer> nodes){
        if(arrived[v])
            return;
        arrived[v] = true;
        nodes.add(v);
        for(Edge adjV : adj(v)){
            DFS(adjV.w(), arrived, nodes);
        }
    }

    @Override
    public void show() {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for (int i = 0; i < g.length; i++) {
            Vector<Edge<Weight>> v = g[i];
            if(v.size()==0){
                builder.append("No edge\n");
                continue;
            }
            for (int j = 0; j < v.size(); j++) {
                builder.append(v.elementAt(j));
                builder.append(", ");
            }
            builder.append("\n");
        }
        builder.append(']');
        System.out.println(builder.toString());

    }

    public Iterable<Edge<Weight>> adj(int v){
        assert v >= 0 && v < n;
        return g[v];
    }



    public static void main(String[] args) {
        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph reader = new ReadWeightedGraph(g, "weightedG.txt");
        g.show();
    }
}
