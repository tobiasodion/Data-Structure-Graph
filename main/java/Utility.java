import java.io.File;
import java.util.*;

public class Utility {
    public static File getFileFromResources(String fileName){
        File file = new File(Utility.class.getResource(fileName).getPath());
        System.out.println(file.getPath());
        return file;
    }

    public static Set<Integer> nodeSetFromEdgeList(Object path){
        Scanner graphScanner;
        String edgeListItem;
        Set<Integer> nodeSet = new HashSet<>();

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
                    edgeListItem.replace(" \t", " ");
                    String[] edgeListItemsArray = edgeListItem.split(" ");

                    nodeSet.add(Integer.parseInt(edgeListItemsArray[0]));
                    nodeSet.add(Integer.parseInt(edgeListItemsArray[1]));
                }
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        return nodeSet;
    }

    public static void printNeighbors(int node, List<Integer> neighbors){
            if (neighbors.isEmpty()){
                System.out.println("Neighbors of Node " + node + ": ");
                System.out.println("Node " + node + " has no neighbours");
            }
            else {
                Set<Integer> neighborsSet = new HashSet<>(neighbors);

                System.out.println("Neighbors of Node " + node + ": ");
                for (int vNeighbor : neighborsSet) {
                    System.out.println(vNeighbor);
                }
            }
    }

    public static void printDegree(int node, int degree){
        if(degree > -1){
            System.out.println("Node " + node +" Degree: " + degree);
        }
        else{
            System.out.println("Node " + node + " does not exist in graph");
        }
    }

    public static void printAdjList(int node, ArrayList<Integer> adjList){
        Set<Integer> adjListSet = new HashSet<Integer>(adjList);

        System.out.print(node);

        for(int adjNode : adjListSet){
                System.out.print("->" + adjNode);
            }
            System.out.println();
    }
}
