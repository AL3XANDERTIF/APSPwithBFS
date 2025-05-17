import it.itisgalileiroma.models.*;
import it.itisgalileiroma.algorithms.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Creazione del grafo di esempio
        Graph grafo = new Graph();

        // Aggiunta dei nodi
        for (int i = 0; i < 5; i++) {
            grafo.addNode(new Node(i));
        }

        // Aggiunta degli archi tra i nodi utilizzando oggetti Node e peso
        grafo.addEdge(new Edge(grafo.getNode(0), grafo.getNode(1), 1));
        grafo.addEdge(new Edge(grafo.getNode(0), grafo.getNode(2), 1));
        grafo.addEdge(new Edge(grafo.getNode(1), grafo.getNode(3), 1));
        grafo.addEdge(new Edge(grafo.getNode(2), grafo.getNode(3), 1));
        grafo.addEdge(new Edge(grafo.getNode(3), grafo.getNode(4), 1));

        // Stampa del grafo
        System.out.println("Grafo di esempio:");
        for (int i = 0; i < grafo.nodes().size(); i++) {
            System.out.println("Nodo " + i + " con vicini: " + grafo.nodes().get(i).neighbors());
        }

        // Calcolo APSP
        Apsp apsp = new Apsp(grafo);
        try {
            ArrayList<ArrayList<Integer>> distanze = apsp.calcolaPercorsiMinimi();
            System.out.println("\nDistanze minime tra tutte le coppie di nodi:");
            for (int i = 0; i < distanze.size(); i++) {
                System.out.println("Dal nodo " + i + ": " + distanze.get(i));
            }
        } catch (InterruptedException e) {
            System.err.println("Errore nel calcolo APSP: " + e.getMessage());
        }
    }
}

