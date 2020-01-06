public class Components {

    Graph G;
    private boolean[] visited;
    private int ccount; // count of component
    private int[] group; // the group for every nodes

    void dfs(int v){
        visited[v] = true;
        group[v] = ccount;

        for(int i: G.adj(v)){
            if(!visited[i])
                dfs(i);
        }
    }
    public int count(){
        return ccount;
    }
    public boolean isConnected(int v, int w){
        return group[v] == group[w];
    }
    public Components(Graph graph){
        G = graph;
        visited = new  boolean[graph.V()];
        group = new int[graph.V()];
        for (int i = 0; i < group.length; i++) {
            group[i] = -1;
        }
        ccount = 0;
        for (int i = 0; i < graph.V(); i++) {
            if(!visited[i]){
                ccount++;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) {
        Graph sparseGraph = new SparseGraph(13, false);
        ReadGraph readGraph = new ReadGraph(sparseGraph, "testG1.txt");
        Components components = new Components(sparseGraph);
        System.out.println(components.count());
        assert components.isConnected(0, 1);
        assert !components.isConnected(0, 10);
    }
}
