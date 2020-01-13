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
}
