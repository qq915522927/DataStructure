import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class BellmanFord<Weight extends Number & Comparable> {

    private WeightedGraph graph;
    private int s;
    private Number[] distTo;
    private Edge<Weight>[] from;
    boolean hasNegativeCycle;

    public BellmanFord(WeightedGraph graph, int s){
        this.graph = graph;
        this.s = s;
        distTo = new Number[graph.V()];
        from = new Edge[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            from[i] = null;
        }

        distTo[s] = 0.0;
        from[s] = new Edge<Weight>(s, s, (Weight) (Number)0.0);

        for(int pass=0; pass < graph.V() - 1; pass ++){
            for (int v = 0; v < graph.V() ; v++) {
                for(Object item: graph.adj(v)){
                    Edge<Weight> e = (Edge<Weight>) item;
                    if(from[e.v()] != null && (from[e.w()] == null || distTo[e.v()].doubleValue() + e.wt().doubleValue() < distTo[e.w()].doubleValue())){
                        distTo[e.w()] = distTo[e.v()].doubleValue() + e.wt().doubleValue();
                        from[e.w()] = e;
                    }
                }

            }
        }
        hasNegativeCycle = hasNegativeCycle();
    }

    private boolean hasNegativeCycle(){
        // V -1 次遍历后， 再来一遍， 如果仍然有relaxtion 说明有负权环
        for (int v = 0; v < graph.V() ; v++) {
            for(Object item: graph.adj(v)){
                Edge<Weight> e = (Edge<Weight>) item;
                if(from[e.v()] != null && (from[e.w()] == null || distTo[e.v()].doubleValue() + e.wt().doubleValue() < distTo[e.w()].doubleValue())){
                    return true;
                }
            }
        }
        return false;
    }

    public void showPath(int w){
        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i).v() + "->");
            if(i == path.size() - 1){
                System.out.print(path.elementAt(i).w() + "\n");

            }

        }

    }
    public Vector<Edge<Weight>> shortestPath(int w){
        Stack<Edge<Weight>> stack = new Stack<>();

        while (from[w].v() != from[w].w()){
            stack.push(from[w]);
            w = from[w].other(w);
        }
        Vector<Edge<Weight>> res = new Vector<>();
        while (!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }
    public static void main(String[] args) throws Exception {
        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph reader = new ReadWeightedGraph(g, "weightedG.txt");
        BellmanFord<Double> bellmanFord = new BellmanFord<>(g, 0);
        for (int i = 0; i < g.V() ; i++) {

            bellmanFord.showPath(i);
        }
    }
}
