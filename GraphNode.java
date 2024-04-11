import java.util.ArrayList;
import java.util.Scanner;
public class GraphNode {
    int key;
    ArrayList<GraphEdge> edges = new ArrayList<GraphEdge>();
    public GraphNode(int id){
        key = id;
    }

}