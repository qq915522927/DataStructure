import java.util.Stack;
import java.util.Vector;

public class LazyPrimeMST<Weight extends Number & Comparable> {
    // 最小生成树
    private WeightedGraph<Weight> graph;
    private IndexHeap<Edge<Weight>> heap;
    private boolean[] marked; // 标记节点是否被访问过
    private Vector<Edge> mst; //　最小生成树的边
    private Number weight; // 最小生成树的权值

    public LazyPrimeMST(WeightedGraph<Weight> graph) throws Exception {
        this.graph = graph;
        heap = new IndexHeap<>(graph.V());
        marked = new boolean[graph.V()];
        mst = new Vector<>();
        visit(0);
        while (!heap.isEmpty()){
            Edge minEdge = heap.extractEle();
            assert (!marked[minEdge.w()]) || (!marked[minEdge.v()]);
            mst.add(minEdge);
            if(marked[minEdge.w()])
                visit(minEdge.v());
            else
                visit(minEdge.w());

        }
        weight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size() ; i++) {
            weight = mst.elementAt(i).wt().doubleValue() + weight.doubleValue();
        }
    }
    public Number weight(){
        return weight;
    }
    public void showMst(){
        System.out.println();
        for(Edge e: mst){
            System.out.print(e + "   ");
        }
        System.out.println();
    }

    private void visit(int v) throws Exception {
        if(marked[v])
            return;
        marked[v] = true;
        for(Edge e: graph.adj(v)){
            if(heap.getEleByIndex(e.other(v)) == null) {
                if (!marked[e.other(v)])
                    heap.insert(e.other(v), e);
            }
            else {
                if (heap.getEleByIndex(e.other(v)).wt().compareTo(e.wt()) > 0)
                    heap.update(e.other(v), e);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph reader = new ReadWeightedGraph(g, "weightedG.txt");
        g.show();
        LazyPrimeMST<Double> mst = new LazyPrimeMST<>(g);
        mst.showMst();
    }
}
