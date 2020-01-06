import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadGraph {
    private Scanner scanner;
    public ReadGraph(Graph graph, String filename){
        readFile(filename);
        try {
            int V = scanner.nextInt();
            if(V < 0)
                throw new IllegalArgumentException("Must be nonegative");
            assert V == graph.V();

            int E = scanner.nextInt();
            if(E < 0)
                throw new IllegalArgumentException("Number of edges must be nonegative");
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(v, w);
            }
        }
        catch (InputMismatchException e){
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read an 'int' but got " + token);
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException("Attemps to read an 'int', but got none");
        }
    }
    private void readFile(String filename){
        assert filename != null;
        try {
            File file = new File(filename);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }
            else
                throw new IllegalArgumentException(filename + "doesn't exist");
        } catch (IOException ioe){
            throw new IllegalArgumentException("Could not open " + filename, ioe);
        }
    }

    public static void main(String[] args) {
        Graph denseGraph = new DenseGraph(13, false);
        new ReadGraph(denseGraph, "testG1.txt");
        denseGraph.show();

        SparseGraph sparseGraph = new SparseGraph(13, false);
        new ReadGraph(sparseGraph, "testG1.txt");
        sparseGraph.show();
        sparseGraph.DFS(0);
    }
}
