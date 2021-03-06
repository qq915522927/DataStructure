import java.util.ArrayList;
import java.util.Vector;

// 稀疏图，　邻接表
public class SparseGraph implements Graph {
    private int n;
    private int m;
    private boolean directed;
    private Vector<Integer>[] g;

    public SparseGraph(int n, boolean directed){
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;
        // 初始化为ｎ个空的邻接表，　表示没有任何边
        g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Integer>();
        }
    }
    public int V(){
        return n;
    }
    public int E(){
        return m;
    }
    public void addEdge(int v, int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        g[v].add(w);
        if(v!=w && !directed)
            // 无向图，　两边都要加上边
            g[w].add(v);
        m++;
    }
    public boolean hasEdge(int v, int w){
        for (int i = 0; i < g[v].size(); i++) {
            if(g[v].elementAt(i) == w)
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
        for(int adjV : adj(v)){
            DFS(adjV, arrived, nodes);
        }
    }

    @Override
    public void show() {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for (int i = 0; i < g.length; i++) {
            Vector<Integer> v = g[i];
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

    public Iterable<Integer> adj(int v){
        assert v >= 0 && v < n;
        return g[v];
    }



    public static void main(String[] args) {
        SparseGraph graph = new SparseGraph(7, false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 6);
        graph.show();
    }
}
