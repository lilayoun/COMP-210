package a5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeImpl implements Node {

    /* You will include the method signatures (return type, name, and arg types) for any node methods you
    need in this file. */

    /*Hint: Make sure you update the Node interface in Node.java when you add a new method implementation
    in NodeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any node fields you want to add for the object should go in this file.  */
    public Map<String, Edge> another = new HashMap<String, Edge>();
    // public ArrayList<EdgeImpl> array;
    public double distance = Double.MAX_VALUE;
    private boolean visited;
    private Node dest;
    private Node src;
    private final String label;

    public NodeImpl(String label) {
        this.label = label;
        this.another = new HashMap<String, Edge>();
    }

    @Override
    public String getName() {
        return label;
    }

    @Override
    public void addEdge(Node dest, double weight) {
        Edge edge = new EdgeImpl(dest, this, weight);
        another.put(dest.getName(), edge);
    }

    @Override
    public void removeEdge(Node dest) {
        Map<String, Edge> list = this.another;
        if (doesTheEdgeExist(dest)){
            list.remove(dest.getName());
        }
    }

    @Override
    public boolean doesTheEdgeExist(Node dest) {
        Map<String, Edge> list = this.another;
        return list.containsKey(dest.getName());
    }

    public double getDistance(){
        return distance;
    }
    public void setDistance(double newDistance){
        distance=newDistance;
    }

    public Map<String, Edge> getAnother(){
        return another;
    }

    @Override
    public Edge getEdge(Node dest) {
        Map<String, Edge> list = this.another;
        if (doesTheEdgeExist(dest)){
            return list.get(dest.getName());
        }
        return null;
    }

}
