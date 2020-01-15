import javax.xml.stream.events.EntityDeclaration;
import java.util.Stack;
import java.util.Vector;

public class Dijkstra<Weight extends Number & Comparable>{
    private WeightedGraph graph;
    private int s;
    private Number[] distTo; // 储存各点的路径长度
    private boolean[] marked;
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph graph, int start) throws Exception {
        this.graph = graph;
        s = start;
        distTo = new Number[graph.V()];
        from = new Edge[graph.V()];
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }

        IndexHeap<Weight> ipq = new IndexHeap<>(graph.V());
        distTo[s] = 0.0;
        from[s] = new Edge<>(s, s, (Weight)(Number) 0.0);
        ipq.insert(s, (Weight) distTo[s]);
        marked[s] = true;

        while (!ipq.isEmpty()){
            int v = ipq.extractMinIndex();
            marked[v] = false;

            for(Object item: graph.adj(v)){
                Edge<Weight> e = (Edge<Weight>) item;
                int w = e.other(v);

                if(!marked[w]){
                    if( from[w] == null || distTo[v].doubleValue() + e.wt().doubleValue() < distTo[w].doubleValue()){
                        distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                        from[w] = e;
                        if(ipq.contains(w))
                            ipq.update(w, (Weight) distTo[w]);
                        else
                            ipq.insert(w, (Weight)distTo[w]);
                    }

                }
            }
        }

    }

    public Number shortestPathTo(int w){
        assert w >= 0 && w < graph.V();
        assert hasPathTo(w);
        return distTo[w];
    }
    private boolean hasPathTo(int w){
        return from[w] != null;
    }
    public Vector<Edge<Weight>> shortestPath(int w){

        assert hasPathTo(w);
        Stack<Edge<Weight>> stack = new Stack<>();
        Edge<Weight> e = from[w];
        while (e.v() != e.w()){ // 定义的　起始点的ｆｒｏｍ是自己
            stack.push(e);
            w = e.other(w);
            e = from[w];
        }
        Vector<Edge<Weight>> res = new Vector<>();
        while (!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }
    public void showPath(int w){
        assert hasPathTo(w);

        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i).v() + "－>");
            if(i == path.size() - 1){
                System.out.print(path.elementAt(i).w() + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph reader = new ReadWeightedGraph(g, "weightedG.txt");
        Dijkstra<Double> dijkstra = new Dijkstra<>(g, 0);
        for (int i = 0; i < g.V() ; i++) {

            dijkstra.showPath(i);
        }
    }
}
