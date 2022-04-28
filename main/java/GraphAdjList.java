import java.io.File;
import java.util.*;

public class GraphAdjList implements IGraph{
    private int N;
    private int M;

    Set<Integer> nodeSet;
    Hashtable<Integer, ArrayList<Integer>> adj = new Hashtable<>();

    public Set<Integer> getNodeSet(){
        return this.nodeSet;
    }

    public Hashtable<Integer, ArrayList<Integer>> getAdj(){
        return this.adj;
    }

    @Override
    public void initializeGraph(Set<Integer> nodeSet, ArrayList<int[]> edgeList) {
        this.nodeSet = nodeSet;
        if(!nodeSet.isEmpty()) {
            for (int node : nodeSet) {
                ArrayList<Integer> adjList = new ArrayList<>();
                if (!adj.containsKey(node)) {
                    adj.put(node, adjList);
                }
            }
        }
        else{
            throw new RuntimeException("The graph data contains no edges");
        }

        for (int[] edgeListItem : edgeList) {
            addEdge(edgeListItem[0], edgeListItem[1]);
        }
    }

    @Override
    public void initializeGraph(Object path){
        nodeSet = Utility.nodeSetFromEdgeList(path);

        if(!nodeSet.isEmpty() || nodeSet != null) {
            for (int node : nodeSet) {
                ArrayList<Integer> adjList = new ArrayList<>();
                if (!adj.containsKey(node)) {
                    adj.put(node, adjList);
                }
            }
        }
        else{
            throw new RuntimeException("The graph data contains no edges");
        }

            Scanner graphScanner;
            String edgeListItem;
            int node1, node2;

            try {
                if (path instanceof File) {
                    graphScanner = new Scanner((File) path);
                } else {
                    graphScanner = new Scanner(new File((String) path));
                }

                while (graphScanner.hasNextLine()) {
                    edgeListItem = graphScanner.nextLine();
                    if(edgeListItem.charAt(0) == '#'){

                    }
                    else{
                        String[] edgeListItemsArray = edgeListItem.split(" ");

                        node1 = Integer.parseInt(edgeListItemsArray[0]);
                        node2 = Integer.parseInt(edgeListItemsArray[1]);

                        addEdge(node1, node2);
                    }
                }
            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
    }

    @Override
    public void addEdge(int u, int v){
        if(adj.containsKey(u) && adj.containsKey(v)){
            ArrayList<Integer> uList = adj.get(u);
            uList.add(v);
            adj.replace(u, uList);

            ArrayList<Integer> vList = adj.get(v);
            vList.add(u);
            adj.replace(v, vList);
        }
        else{
            throw new RuntimeException("Trying to access node(s) not present in the graph");
        }
    }

    @Override
    public int getOrder() {
        return this.nodeSet.size();
    }

    @Override
    public int getSize() {
        Enumeration<Integer> e = adj.keys();
        int key;
        int total = 0;
        while(e.hasMoreElements()) {
            key = e.nextElement();
            total += adj.get(key).size();
        }
        return total/2;
    }

    @Override
    public ArrayList<Integer> getNeighbours(int node){
        ArrayList<Integer> adjList = new ArrayList<>();

        if(adj.containsKey(node)){
            adjList = adj.get(node);
            return adjList;
        }
        else{
            throw new RuntimeException("Trying to access node not present in the graph");
        }
    }

    @Override
    public void adjListRep(){
        Enumeration<Integer> e = adj.keys();
        int key;
        ArrayList<Integer> adjList;

        while(e.hasMoreElements()){
            key = e.nextElement();
            adjList = adj.get(key);

            Utility.printAdjList(key, adjList);
        }
    }

    @Override
    public int Degree(int v) {
        if (containsNode(v)){
            Set<Integer> adjListSet = new HashSet<>(adj.get(v));
            return adjListSet.size();
        }
        else{
            throw new RuntimeException("Trying to access node not present in the graph");
        }
    }

    @Override
    public boolean containsNode(int node){
        return nodeSet.contains(node);
    }

    @Override
    public double averageDegree(){
        double averageDegree = (double)getSize()/getOrder();
        return Math.round(averageDegree*100.0)/100.0;
    }

    @Override
    public double edgeDensity(){
        int totalEdge = getSize();
        int totalNode = getOrder();

        double graphDensity = (double)totalEdge/((totalNode*(totalNode - 1)/2));
        return Math.round(graphDensity*100.0)/100.0;
    }

    @Override
    public int minimumDegree(){
        //Set<Integer> nodeSet = getNodeSet();
        ArrayList<Integer> adjList = getNeighbours(nodeSet.iterator().next());
        int minimumDegree = adjList.size();

        for(Integer node: nodeSet ){
            if(getNeighbours(node).size() < minimumDegree){
                minimumDegree = getNeighbours(node).size();
            }
        }
        return minimumDegree;
    }

    @Override
    public int maximumDegree(){
        //Set<Integer> nodeSet = graph.getNodeSet();
        ArrayList<Integer> adjList = getNeighbours(nodeSet.iterator().next());
        int maximumDegree = adjList.size();

        for(Integer node: nodeSet ){
            if(getNeighbours(node).size() > maximumDegree){
                maximumDegree = getNeighbours(node).size();
            }
        }
        return maximumDegree;
    }

}
