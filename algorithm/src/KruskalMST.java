import java.util.Vector;

public class KruskalMST<Weight extends Number & Comparable> {
    private WeightedGraph graph;
    private Vector<Edge<Weight>> mst;
    private Edge<Weight>[] edges;
    private UnionFind6<Integer> uf;
    private Number weight;
    public KruskalMST(WeightedGraph graph){
        this.graph = graph;
        Integer[] nodes = new Integer[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            nodes[i] = i;
        }
        mst = new Vector<>();
        uf = new UnionFind6(nodes);
        edges = new Edge[graph.E()];
        int edgeNum = 0;
        for (int i = 0; i < nodes.length; i++) {
            for(Object item: graph.adj(i)){
                Edge<Weight> e = (Edge<Weight>) item;
                if(e.v() >= e.w()){
                    edges[edgeNum] = e;
                    edgeNum ++;
                }
            }
        }
        SortAlgo.quickSort3Ways(edges);
        for (int i = 0; i < edges.length; i++) {
            Edge<Weight> e = edges[i];
            if(uf.isConnected(e.v(), e.w())){
                continue;
            }
            uf.union(e.v(), e.w());
            mst.add(e);
            if(mst.size() == nodes.length - 1)
                break;
        }
        weight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size() ; i++) {
            weight = mst.elementAt(i).wt().doubleValue() + weight.doubleValue();
        }
    }
    public void showMst(){
        System.out.println();
        for(Edge e: mst){
            System.out.print(e + "   ");
        }
        System.out.println();
    }
    public Number weight(){
        return weight;
    }
    public static void main(String[] args) throws Exception {

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph reader = new ReadWeightedGraph(g, "weightedG.txt");
        g.show();
        LazyPrimeMST<Double> mst = new LazyPrimeMST<>(g);
        mst.showMst();
        System.out.println(mst.weight());

        KruskalMST<Double> mstKru = new KruskalMST<>(g);
        mstKru.showMst();
        System.out.println(mstKru.weight());
    }
}
