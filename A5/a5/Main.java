package a5;

public class Main {


    public static void main(String[] args) {

        //You are encouraged (but not required) to include your testing code here.

        //Hint: Try to test basic operations (e.g., adding a few nodes and edges to graphs)
        //before you implement more complex methods
        exTest();

    }
    public static void exTest() {
        GraphImpl graph = new GraphImpl();
        //graph.dijkstra("f");
        System.out.println("adding a node");
        System.out.println(graph.addNode("A"));
        System.out.println(graph.addNode("B"));
        System.out.println(graph.addNode("C"));
        System.out.println(graph.addNode("D"));
        System.out.println(graph.addNode("E"));
        System.out.println("--------------------------------------------------");
        System.out.println("adding an edge");
        System.out.println(graph.addEdge("A", "B", 3.0));
        System.out.println(graph.addEdge("A", "C", 1.0));
        System.out.println(graph.addEdge("B", "C", 7.0));
        System.out.println(graph.addEdge("B", "E", 1.0));
        System.out.println(graph.addEdge("B", "D", 5.0));
        System.out.println(graph.addEdge("C", "D", 2.0));
        System.out.println(graph.addEdge("D", "E", 7.0));
        System.out.println("adding same edge");
        System.out.println(graph.addEdge("D", "E", 7.0));
        System.out.println("adding nonexistent edge");
        System.out.println(graph.addEdge("A", "G", 7.0));
        System.out.println("--------------------------------------------------");
        System.out.println("delete node");
        System.out.println(graph.numNodes());
        System.out.println(graph.deleteNode("F"));
//        System.out.println(graph.deleteNode("A"));
//        System.out.println(graph.deleteNode("A"));
//        System.out.println(graph.doesTheEdgeExist("A"));
        System.out.println(graph.numNodes());
        System.out.println("--------------------------------------------------");
        System.out.println(graph.deleteEdge("A", "B"));
//        System.out.println(graph.deleteEdge("A", "C"));

        System.out.println("--------------------------------------------------");
//        System.out.println(graph.dijkstra("A"));
        System.out.println(graph.dijkstra("A"));

    }
}
