import java.io.File;
import java.util.*;

public class GraphAdjMatrix implements IGraph{
    private int N;
    private int M;
    private boolean[][] adj;

    Hashtable<Integer, Integer> labelIndexMap = new Hashtable<>();
    Hashtable<Integer, Integer> indexLabelMap = new Hashtable<>();
    Set<Integer> nodeSet = new HashSet<>();

    public boolean[][] getAdj(){
        return this.adj;
    }

    public void initializeMatrix(int N){
        this.N = N;
        this.M = 0;
        this.adj = new boolean[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                adj[i][j] = false;
            }
        }
    }

    @Override
    public void initializeGraph(Object path){
        Scanner graphScanner;
        String element;
        int node1, node2;
        int i = 0;
        int index;

        nodeSet = Utility.nodeSetFromEdgeList(path);
        if(!nodeSet.isEmpty()){
            for (int node : nodeSet){
                index = i;
                labelIndexMap.put(node,index);
                indexLabelMap.put(index, node);
                i++;
            }

            initializeMatrix(getOrder());
        }
        else{
            throw new RuntimeException("The graph data contains no nodes");
        }

        try {
            if (path instanceof File) {
                graphScanner = new Scanner((File) path);
            } else {
                graphScanner = new Scanner(new File((String) path));
            }

            while (graphScanner.hasNextLine()) {
                element = graphScanner.nextLine();
                if(element.charAt(0) == '#'){

                }
                else{
                    String[] elementArray = element.split(" ");

                    //get the first node
                    node1 = Integer.parseInt(elementArray[0]);
                    //get the second node
                    node2 = Integer.parseInt(elementArray[1]);
                    //add edge between nodes
                    addEdge(labelIndexMap.get(node1), labelIndexMap.get(node2));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void initializeGraph(Set<Integer> nodeSet, ArrayList<int[]> edgeList) {

    }

    public void addEdge (int u, int v){
        adj[u][v] = true;
    }

    @Override
    public int getOrder() {
        return nodeSet.size();
    }

    @Override
    public int getSize() {
        int size = 0;
        for (int i = 0; i<N; i++){
            for (int j = 0; j<N; j++){
                if(adj[i][j]){
                    size++;
                }
            }
        }
        return size;
    }

    @Override
    public ArrayList<Integer> getNeighbours(int node) {
        return null;
    }

    @Override
    public void adjListRep() {

    }

    @Override
    public int Degree(int v) {
        int degree = 0;
        if(containsNode(v)){
            int nodeIndex = labelIndexMap.get(v);
            for (int i = 0; i<labelIndexMap.size(); i++){
                if(adj[nodeIndex][i]){
                    degree++;
                }
            }
        }
        else{
            throw new RuntimeException("Trying to access node not present in the graph");
        }

        return degree;
    }

    @Override
    public boolean containsNode(int node) {
        return labelIndexMap.containsKey(node);
    }

    @Override
    public double averageDegree() {
        double averageDegree = (double)getSize()/getOrder();
        return Math.round(averageDegree*100.0)/100.0;
    }

    @Override
    public double edgeDensity() {
        int totalEdge = getSize();
        int totalNode = getOrder();

        double graphDensity = (double)totalEdge/((totalNode*(totalNode - 1)/2));
        return Math.round(graphDensity*100.0)/100.0;
    }

    @Override
    public int minimumDegree() {
        int label = indexLabelMap.get(0);
        //int index = labelIndexMap.get(label);
        int minimumDegree = Degree(label);
        int key;
        Enumeration<Integer> e = indexLabelMap.keys();

        while (e.hasMoreElements()){
            key = e.nextElement();
            label = indexLabelMap.get(key);
            if(Degree(label) < minimumDegree){
                minimumDegree = Degree(key);
            }
        }

        return minimumDegree;
    }

    @Override
    public int maximumDegree() {
        int label = indexLabelMap.get(0);
        //int index = labelIndexMap.get(label);
        int maximumDegree = Degree(label);
        int key;
        Enumeration<Integer> e = indexLabelMap.keys();

        while (e.hasMoreElements()){
            key = e.nextElement();
            label = indexLabelMap.get(key);
            if(Degree(label) > maximumDegree){
                maximumDegree = Degree(key);
            }
        }

        return maximumDegree;
    }
}
