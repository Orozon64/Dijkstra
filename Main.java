import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<GraphNode> nodes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner myScn = new Scanner(System.in);
        boolean should_stop = false;
        do{
            System.out.println("Podaj id wierzchołka");
            int nodekey = Integer.parseInt(myScn.nextLine());
            GraphNode n1 = new GraphNode(nodekey);
            nodes.add(n1);
            Connect(n1);
            System.out.println("Dodać jescze jakieś węzły? 0 - tak, 1 - nie");
            int continue_response = Integer.parseInt(myScn.nextLine());
            switch (continue_response){
                case 0:
                    break;
                case 1:
                    should_stop = true;
                    break;

            }
        }while (!should_stop);

        for (GraphNode no: nodes){
            System.out.println("Obecny węzeł ma klucz równy " + no.key);
            System.out.println("Jest powiązany z:");
            for(GraphEdge ge: no.edges){
                System.out.println("Węzłem o kluczu " + ge.nodeToConnect.key + " połączeniem o wadze " + ge.weight);
            }
        }
    }
    public static void Connect(GraphNode con_node){
        Scanner myScn = new Scanner(System.in);
        boolean connector_exists = false;
        System.out.println("Podaj wagę połączenia");
        int edge_weight = Math.abs(Integer.parseInt(myScn.nextLine()));//używamy w. bezwzględnej, aby uniknąć wartości ujemnych - inaczej alg. Dijkstry nie zadziała
        System.out.println("Podaj id wierzchołka, z jakim chcesz połączyć ten wierzchołek.");
        int connector_id = Integer.parseInt(myScn.nextLine());
        for(GraphNode gn : nodes){
            if(gn.key == connector_id){
                GraphEdge myedge = new GraphEdge(edge_weight, gn);
                con_node.edges.add(myedge);
                GraphEdge con_edge = new GraphEdge(edge_weight, con_node);
                gn.edges.add(con_edge);
                connector_exists = true;
            }

        }
        if(!connector_exists){
            GraphNode con = new GraphNode(connector_id);
            nodes.add(con);
            GraphEdge myedge = new GraphEdge(edge_weight, con);
            con_node.edges.add(myedge);
            GraphEdge con_edge = new GraphEdge(edge_weight, con_node);
            con.edges.add(con_edge);
        }

    }
    public static int Dijkstra(GraphNode s, GraphNode dest){
        ArrayList<GraphNode> Q = nodes;
        for(GraphNode inf_setter : Q){
            inf_setter.dis = Double.POSITIVE_INFINITY;
        }

        s.dis = 0;
        while (!Q.isEmpty()){
            GraphNode v = new GraphNode(-20); //rozwiązanie tymczasowe
            int distance = 9999;
            for(GraphNode ge: Q){ //szukamy wierzchołka o najmniejszej odległości
                if(ge.dis < distance){
                    distance = (int) ge.dis;
                    v = ge;
                }

            }
            Q.remove(v);
            for(GraphEdge k : v.edges){
                GraphNode u = k.nodeToConnect;
                    if(k.weight < u.dis){
                        u.dis = v.dis + k.weight;
                        u.previous = v;
                    }
                }

            }
        return (int) dest.dis;
        }
}
