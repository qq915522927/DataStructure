import java.util.Stack;
import java.util.Vector;

public class Path {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] from;
    private void dfs(int v){
        visited[v] = true;
        for(int i: G.adj(v)){
            if(!visited[i]){
                from[i] = v;
                dfs(i);
            }
        }
    }

    public Path(Graph graph, int s){
        G = graph;
        assert s >= 0 && s< graph.V();
        visited = new boolean[G.V()];
        from = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        this.s = s;
        dfs(s);
    }

    public boolean hasPath(int w){
        return visited[w];
    }

    public Vector<Integer> path(int w){
        assert hasPath(w);

        Stack<Integer> stack = new Stack<>();
        int p = w;
        stack.push(p);
        while (from[p] != -1){
            stack.push(from[p]);
            p = from[p];
        }
        Vector<Integer> res = new Vector<>();

        while (!stack.isEmpty())
           res.add(stack.pop());
        return res;
    }

    public void showPath(int w){
        assert hasPath(w);
        Vector<Integer> vec = path(w);
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.elementAt(i));
            if(i != vec.size() - 1){
                System.out.print(" -> ");
            } else {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Graph denseGraph = new DenseGraph(13, false);
        ReadGraph readGraph = new ReadGraph(denseGraph, "testG1.txt");
        Path path = new Path(denseGraph, 0);
        path.showPath(3);
    }
}
