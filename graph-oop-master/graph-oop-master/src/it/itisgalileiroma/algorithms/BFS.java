package it.itisgalileiroma.algorithms;

import it.itisgalileiroma.models.*;
import java.util.ArrayList;

public class BFS {

    private Graph grafo;

    // Costruttore: inizializza il grafo da analizzare
    public BFS(Graph grafo) {
        this.grafo = grafo;
    }

    /**
     * Calcola i percorsi minimi da un nodo sorgente a tutti gli altri nodi usando BFS
     * @param nodoSorgente il nodo di partenza
     * @return una lista con le distanze minime da nodoSorgente agli altri nodi
     */
    public ArrayList<Integer> computeShortestPathsList(Node nodoSorgente) {
        int numeroNodi = grafo.nodes().size();

        ArrayList<Integer> distanze = new ArrayList<>();
        ArrayList<Node> coda = new ArrayList<>();

        boolean[] visitato = new boolean[numeroNodi];


        for (int i = 0; i < numeroNodi; i++) {          // Inizializzazione della lista delle distanze
            distanze.add(Integer.MAX_VALUE);
        }


        distanze.set(nodoSorgente.id(), 0);         // Distanza dal nodo sorgente a se stesso è 0
        visitato[nodoSorgente.id()] = true;
        coda.add(nodoSorgente);


        while (!coda.isEmpty()) {
            Node nodoCorrente = coda.remove(0);                             // Dequeue
            int distanzaCorrente = distanze.get(nodoCorrente.id());

            int numVicini = nodoCorrente.neighbors().size();

            for (int i = 0; i < numVicini; i++) {

                int vicinoId = nodoCorrente.neighbors().get(i);

                if (!visitato[vicinoId]) {
                    visitato[vicinoId] = true;
                    distanze.set(vicinoId, distanzaCorrente + 1);
                    coda.add(grafo.getNode(vicinoId));
                }
            }
        }

        return distanze;
    }
}
