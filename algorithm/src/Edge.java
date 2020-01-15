public class Edge<Weight extends Number & Comparable> implements Comparable<Edge> {

    private int a, b; // 2 points
    private Weight weight;

    public Edge(int a, int b, Weight weight){
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
    public int v(){
        return a;
    }
    public int w(){
        return b;
    }
    public Weight wt(){return weight;}

    public int other(int x){
        assert x == a || x == b: String.format("a %s, b %s, x %s", a, b, x);
        return x == a ? b : a;
    }

    @Override
    public String toString() {
        return String.format("%d-%d:%s", a, b, weight);
    }

    @Override
    public int compareTo(Edge edge) {
        if(weight.compareTo(edge.wt())<0){
            return -1;
        } else if (weight.compareTo(edge.wt())>0){
            return 1;
        } else
            return 0;
    }
}
