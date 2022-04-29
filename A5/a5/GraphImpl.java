package a5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;

public class GraphImpl implements Graph {
    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name

    private int weight;
    private Node src;
    private Node dest;
    private int[] name;
    private int numNodes;
    private int numEdges;

    public GraphImpl() {
        nodes = new HashMap<>();
    }


    @Override
    public boolean addNode(String label) {
        if (nodes.containsKey(label)) {
            return false;
        }
        if (label==null){
            return false;
        }
        Node node = new NodeImpl(label);
        nodes.put(label, node);
        return true;
    }

    @Override
    public boolean deleteNode(String label) {
        //Hint: Do you need to remove edges when you delete a node?
        if (!nodes.containsKey(label)){
            return false;
        }
        for (Node node :nodes.values()){
            if (node.doesTheEdgeExist(nodes.get(label))){
                node.removeEdge(nodes.get(label));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if (weight<0){
            return false;
        }
        if (src == null || !nodes.containsKey(src)){
            return false;
        }
        if (dest == null || !nodes.containsKey(dest)){
            return false;
        }
        if (nodes.get(src).doesTheEdgeExist(nodes.get(dest))){
            return false;
        }
        nodes.get(src).addEdge(nodes.get(dest), weight);
        return true;
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        if (!nodes.get(src).doesTheEdgeExist(nodes.get(dest))){
            return false;
        }
        nodes.get(src).removeEdge(nodes.get(dest));
        return true;
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int numEdges() {
        int numEdges = 0;
        for (String name : nodes.keySet()){
            numEdges += nodes.get(name).getAnother().size();
        }
        return numEdges;
    }

    @Override
    public Map<String, Double> dijkstra(String start) {
        Map<String, Double> nodeToDistance = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2){
                if (o1.getDistance() < o2.getDistance()){
                    return -1;
                } else if (o1.getDistance() > o2.getDistance()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        priorityQueue.add(nodes.get(start));
        nodes.get(start).setDistance(0);
        HashSet<Node> set = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Node currNode = priorityQueue.poll();
            if (!set.contains(currNode)){
                set.add(currNode);
                for (Edge edge : currNode.getAnother().values()){
                    Node another = edge.getDest();
                    if (another.getDistance() > currNode.getDistance() + edge.getWeight()){
                        another.setDistance(currNode.getDistance() + edge.getWeight());
                    }
                    priorityQueue.add(another);
                }
                nodeToDistance.put(currNode.getName(),currNode.getDistance());
            }
        }
        return nodeToDistance;
    }
}
