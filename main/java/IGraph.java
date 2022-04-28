import java.util.ArrayList;
import java.util.Set;

public interface IGraph {
    void initializeGraph(Object path);
    void initializeGraph(Set<Integer> nodeSet, ArrayList<int[]> edgeList);
    void addEdge(int u, int v);
    int getOrder();
    int getSize();
    ArrayList<Integer> getNeighbours(int node);
    void adjListRep();
    int Degree(int v);
    boolean containsNode(int node);
    double averageDegree();
    double edgeDensity();
    int minimumDegree();
    int maximumDegree();
}
