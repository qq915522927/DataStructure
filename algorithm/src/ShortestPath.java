import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ShortestPath {

    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] from;
    private int[] ord;

    private void bfs(int w){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(w);
        visited[w] = true;
        ord[w] = 0;
        while (!queue.isEmpty()){
            int v = queue.remove();
            for(int i: G.adj(v)){
                if(!visited[i]){
                    queue.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }

    }
    public ShortestPath(Graph graph, int s){
        G = graph;
        assert s >= 0 && s< graph.V();
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
        bfs(s);
    }
    public boolean hasPath(int w){
        return visited[w];
    }
    public Vector<Integer> path(int w){
        assert hasPath(w);
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1){
            stack.add(p);
            p = from[p];
        }
        Vector<Integer> vec = new Vector<>();
        while (!stack.isEmpty()){
            vec.add(stack.pop());
        }
        return vec;
    }
    public void showPath(int w){
        assert hasPath(w);
        Vector<Integer> vec = path(w);
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.elementAt(i));
            if(i!=vec.size() - 1)
                System.out.print(" -> ");
            else
                System.out.println();
        }
    }

    public int length(int w){
        assert hasPath(w);
        return ord[w];
    }

    public static void main(String[] args) {
        Graph denseGraph = new DenseGraph(13, false);
        ReadGraph readGraph = new ReadGraph(denseGraph, "testG1.txt");
        ShortestPath path = new ShortestPath(denseGraph, 0);
        path.showPath(3);
        System.out.println("length: " + path.length(3));
    }
}
