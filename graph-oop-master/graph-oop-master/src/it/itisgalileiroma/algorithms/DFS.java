package it.itisgalileiroma.algorithms;

import it.itisgalileiroma.models.Graph;
import it.itisgalileiroma.models.Node;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    Graph mGraph;
    Node mSource;

    public DFS(Graph g, Node source) {
        this.mGraph = g;
        this.mSource = g.getNode(source.id());
    }

    public ArrayList<Node> run() {
        // Inizializza uno stack vuoto S

        // non sapendo se esiste la struttura dati stack in Java
        // possiamo decidere di implementarla come preferiamo utilizzando
        // le strutture dati che conosciamo in Java. E' chiaro che dovendo essere uno stack
        // DOBBIAMO RICORDARCI di estrare gli elementi secondo quanto previsto dallo stack
        // cioè LIFO
        // Al momento, sembrerebbe che l'unica struttura dati che ci ricordiamo è l'ArrayList
        // e quindi... utilizziamo l'ArrayList
        // E' possibile anche utilizzare un array classico, ma sappiamo che l'array classico
        // ha il problema delle dimensioni fissate a priori, mentre l'ArrayList
        // è una struttura più flessibile, poiché possiamo aggiungere e rimuovere gli elementi
        // senza pensare alla dimensione a priori dell'ArrayList
        // todo esiste un modo per implementare lo stack con l'array?
        ArrayList<Node> stack = new ArrayList<>();

        // Inizializza un insieme Visitati vuoto
        ArrayList<Node> visited = new ArrayList<>();

        // Inserisci v in S
        stack.add(mSource);
        while (!stack.isEmpty()) { // Mentre S non è vuoto
            // Estrai u da S
            // per u si intende un nodo qualsiasi ma LIFO
            // quindi dobbiamo estrarre da S l'ultimo nodo entrato
            // lo estraiamo cioé lo leggiamo e lo inseriamo in una variabile
            // perché ci serve dopo...
            Node u = stack.get(stack.size() - 1);
            // una volta memorizzato in una variabile, dobbiamo eliminarlo
            // dallo stack
            stack.remove(stack.size() - 1);

            if (!visited.contains(u)) { // Se u non è in Visitati
                visited.add(u); // Aggiungi u a Visitati
                // Per ogni vicino w di u in G (in ordine inverso)

                // Prima di tutto dobbiamo prenderci i vicini
                // del nodo che abbiamo estratto (il nodo è nella variabile u)
                // La classe Node ha il getter dei vicini che restituisce
                // una lista di Integer cioè di id interi. A noi servirebbe una lista di Node
                // ma non è un problema, perché recuperiamo l'oggetto Node
                // a partire dall'id intero tramite il metodo del Graph getNode
                List<Integer> neighborsOfNodeU = u.neighbors();
                // Iteriamo sugli elementi della lista
                // cioè sui vicini del nodo rappresentati tramite id interi
                for(int i = neighborsOfNodeU.size()-1; i >= 0; i--) {
                    int wId = neighborsOfNodeU.get(i);
                    // recuperiamo l'oggetto Node
                    Node w = mGraph.getNode(wId);
                    // Se w non è in Visitati cioè se il vicino
                    // del nodo non è dentro visited
                    if (!visited.contains(w)) {
                        // Inserisci w cioè il vicino del nodo in S
                        stack.add(w);
                    }
                }
            }
        }
        return visited;
    }
}
