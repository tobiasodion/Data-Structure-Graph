import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Graph {
    public static void main(String[] arg) throws IOException {
        //GraphAdjList myGraph = new GraphAdjList("/Users/tobiasodion/IdeaProjects/Graph/target/classes/graph.txt");
        GraphAdjList testGraph = new GraphAdjList();

        System.out.println("PART 2 - Adjacency list representation of an undirected graph");
        System.out.println("Select option 1 or 2 to initialize graph");
        System.out.println("1 - Graph.txt");
        System.out.println("2 - Keyboard");
        System.out.println();

        int option;
        String optionText = "nil";
        Scanner myScanner = new Scanner(System.in);

        while(optionText == "nil"){
            System.out.print("Enter Option: ");
            option = myScanner.nextInt();

            if(option == 1){
                testGraph.initializeGraph(Utility.getFileFromResources("graph.txt"));
                optionText = "graph.txt";
            }
            else if (option == 2){
                keyBoardInitialization(testGraph);
                optionText = "keyboard";
            }
            else{
                System.out.println("Invalid Input. Try Again");
            }
        }
        System.out.println();

        System.out.println("Adjacent List Representation");
        testGraph.adjListRep();

        System.out.println();

        System.out.println(optionText);
        System.out.println("The total order of the graph is: " + testGraph.getOrder());
        System.out.println("The total size of the graph is: " + testGraph.getSize());
        System.out.println();

        System.out.println(optionText + " node Neighbors");
        for(Integer n : testGraph.nodeSet){
            if(testGraph.containsNode(n)){
                Utility.printNeighbors(n, testGraph.getNeighbours(n));
                System.out.println();
            }
        }
        System.out.println();

        System.out.println(optionText + " node Degrees");
        for(Integer n : testGraph.nodeSet ){
            if(testGraph.containsNode(n)){
                int degree = testGraph.Degree(n);
                Utility.printDegree(n, degree);
                System.out.println();
            }
        }
        System.out.println();

        System.out.println(optionText + " metrics");
        System.out.println("Average Degree: " + testGraph.averageDegree());
        System.out.println("Minimum Degree: " + testGraph.minimumDegree());
        System.out.println("Maximum Degree: " + testGraph.maximumDegree());
        System.out.println("Edge Density: " + testGraph.edgeDensity());
        System.out.println();

        System.out.println("PART 3 - Bigger Graph");
        System.out.println("Select option 1 or 2 to initialize graph");
        System.out.println("1 - AdjList Representation/facebook_combined.txt");
        System.out.println("2 - AdjMatrix Representation/facebook_combined.txt");
        System.out.println("3 - AdjList Representation/Wiki-Vote.txt");
        System.out.println("4 - AdjMatrix Representation/Wiki-Vote.txt");

        System.out.println();

        optionText = "nil";

        while(optionText == "nil"){
            System.out.print("Enter Option: ");
            option = myScanner.nextInt();

            if(option == 1){
                GraphAdjList testGraph2 = new GraphAdjList();
                testGraph2.initializeGraph(Utility.getFileFromResources("facebook_combined.txt"));
                optionText = "AdjList Representation/facebook_combined.txt";

                System.out.println(optionText + " metrics");
                System.out.println("Average Degree: " + testGraph2.averageDegree());
                System.out.println("Minimum Degree: " + testGraph2.minimumDegree());
                System.out.println("Maximum Degree: " + testGraph2.maximumDegree());
                System.out.println("Edge Density: " + testGraph2.edgeDensity());
                System.out.println();
            }
            else if (option == 2){
                GraphAdjMatrix testGraph2 = new GraphAdjMatrix();
                testGraph2.initializeGraph(Utility.getFileFromResources("facebook_combined.txt"));
                optionText = "AdjMatrix Representation/facebook_combined.txt";

                System.out.println(optionText + " metrics");
                System.out.println("Average Degree: " + testGraph2.averageDegree());
                System.out.println("Minimum Degree: " + testGraph2.minimumDegree());
                System.out.println("Maximum Degree: " + testGraph2.maximumDegree());
                System.out.println("Edge Density: " + testGraph2.edgeDensity());
                System.out.println();
            }

            else if (option == 3){
                GraphAdjList testGraph2 = new GraphAdjList();
                testGraph2.initializeGraph(Utility.getFileFromResources("Wiki-Vote.txt"));
                optionText = "AdjList Representation/Wiki-Vote.txt";

                System.out.println(optionText + " metrics");
                System.out.println("Average Degree: " + testGraph2.averageDegree());
                System.out.println("Minimum Degree: " + testGraph2.minimumDegree());
                System.out.println("Maximum Degree: " + testGraph2.maximumDegree());
                System.out.println("Edge Density: " + testGraph2.edgeDensity());
                System.out.println();
            }

            else if (option == 4){
                GraphAdjMatrix testGraph2 = new GraphAdjMatrix();
                testGraph2.initializeGraph(Utility.getFileFromResources("Wiki-Vote.txt"));
                optionText = "AdjMatrix Representation/Wiki-Vote.txt";

                System.out.println(optionText + " metrics");
                System.out.println("Average Degree: " + testGraph2.averageDegree());
                System.out.println("Minimum Degree: " + testGraph2.minimumDegree());
                System.out.println("Maximum Degree: " + testGraph2.maximumDegree());
                System.out.println("Edge Density: " + testGraph2.edgeDensity());
                System.out.println();
            }
            else{
                System.out.println("Invalid Input. Try Again");
            }
        }
        System.out.println();


    }

    public static void keyBoardInitialization(GraphAdjList graph) throws IOException {
        int nodes;
        int edges;
        String[] input;
        int node1, node2;

        Set<Integer> nodeSet = new HashSet<>();
        ArrayList<int[]> edgeList = new ArrayList<>();

        Scanner myScanner = new Scanner(System.in);
        System.out.print("Number of Vertices ? ");
        nodes = myScanner.nextInt();
        System.out.print("Number of Edges ? ");
        edges = myScanner.nextInt();
        System.out.println();

        for (int i=0; i<edges; i++){
            System.out.print("Edge " + (i+1) + " ? ");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            input = in.readLine().split(" ");

            int[] edgeListItem = new int[2];
            edgeListItem[0] = Integer.parseInt(input[0]);
            edgeListItem[1] = Integer.parseInt(input[1]);

            edgeList.add(edgeListItem);

            nodeSet.add(edgeListItem[0]);
            nodeSet.add(edgeListItem[1]);
        }

        if(!nodeSet.isEmpty()){
            graph.initializeGraph(nodeSet, edgeList);
        }
        else{
            System.out.println("No edge data available");
        }
    }
}
