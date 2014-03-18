package fds.practica;

/**
 * Esta clase representa cada pasillo (arista) de la Universidad (grafo).
 * <p>
 * Técnica utilizada: Dijkstra modificado para ser no dirigido.
 * 
 * @author      David Julián Yela
 * @version     1.0
 */

public class Arista {
    
     /**
     * <code>Nodo</code> que representa el destino (nodo) de la arista.
     */
    
    public final Nodo destino;
    
     /**
     * <code>Integer</code> que representa la distancia (peso) del pasillo (arista).
     */
    
    public final int distancia;
    
     /** 
     * Constructor de la clase Arista.
     *
     * @param nodo      Es el nodo destino de la arista.
     * @param peso      Es la distancia de la arista.
     */
    
    public Arista(Nodo nodo, int peso) { 
        destino = nodo; 
        distancia = peso; 
    }    
    
}
