package it.itisgalileiroma.algorithms;

import it.itisgalileiroma.models.*;
import java.util.ArrayList;

public class Apsp {

    private Graph grafo;

    // Costruttore: inizializza il grafo da analizzare
    public Apsp(Graph grafo) {
        this.grafo = grafo;
    }

    /**
     * Calcola il percorso più breve tra tutte le coppie di nodi utilizzando BFS.
     * @return una matrice con le distanze minime tra i nodi
     * @throws InterruptedException se i thread vengono interrotti
     */
    public ArrayList<ArrayList<Integer>> calcolaPercorsiMinimi() throws InterruptedException {
        int numeroNodi = grafo.nodes().size();
        ArrayList<ArrayList<Integer>> distanze = new ArrayList<>();

        // Inizializzazione della matrice delle distanze
        for (int i = 0; i < numeroNodi; i++) {
            ArrayList<Integer> riga = new ArrayList<>();
            for (int j = 0; j < numeroNodi; j++) {
                riga.add(Integer.MAX_VALUE); // Distanza iniziale infinita
            }
            distanze.add(riga);
        }

        // Creazione e avvio dei thread per ogni nodo
        Thread[] threads = new Thread[numeroNodi];
        for (int i = 0; i < numeroNodi; i++) {
            final int indiceSorgente = i;
            threads[i] = new Thread(() -> {
                Node nodoSorgente = grafo.nodes().get(indiceSorgente);
                BFS bfs = new BFS(grafo);
                ArrayList<Integer> percorsiMinimi = bfs.computeShortestPathsList(nodoSorgente);
                distanze.set(indiceSorgente, percorsiMinimi);
            });
            threads[i].start();
        }

        // Attesa del completamento di tutti i thread
        for (int i = 0; i < numeroNodi; i++) {
            threads[i].join();
        }

        return distanze;
    }
}
