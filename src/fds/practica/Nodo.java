package fds.practica;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Esta clase representa cada aula (nodo) del problema (grafo).
 * <p>
 * Técnica utilizada: Dijkstra modificado para ser no dirigido.
 * 
 * @author      David Julián Yela
 * @version     1.0
 */
public class Nodo implements Comparable<Nodo> {
    
    /**
     * <code>String</code> que representa el nombre del aula.
     */
    
    public final String nombre;
    
    /**
     * <code>ArrayList</code> que guarda todas las aristas
     * que parten del nodo.
     */
    
    public ArrayList<Arista> adyacente;
    
    /**
     * <code>int</code> que se utiliza para guardar el camino
     * más corto para llegar al nodo. Es necesaria para ejecutar el algoritmo.
     */
    
    public int minDistancia;
    
    /**
     * <code>Nodo</code> que indica el mejor nodo padre.
     */
    
    public Nodo previo;
    
    private static final int INFINITO = Integer.MAX_VALUE;   
    
    /** 
     * Constructor de la clase Nodo que inicializa las variables cuando se crea un
     * objeto de esta clase.
     *
     * @param auxNombre     Es el nombre del aula (nodo).
     * 
     * @see                 Arista
     * @see                 Integer.MAX_VALUE
     */
    
    public Nodo (String auxNombre) { 
        this.nombre = auxNombre;
        this.adyacente = new ArrayList<>();
        this.minDistancia  = INFINITO; // Al principio todos los nodos tienen coste infinito
        previo = null;
        
    }
    
     /** 
     * Este método devuelve el nombre del aula permitiendo mostrarlo por pantalla de una
     * forma más cómoda con la llamada objeto.toString().
     *
     * @return          Devuelve el nombre del aula.
     */
    
    @Override
    public String toString() { 
        return this.nombre;
    }
    
     /** 
     * Este método sobreescribe <code>compareTo</code> y permite que la clase <code>PriorityQueue</code>
     * pueda colocar correctamente los nodos cuando se le añade un nuevo nodo.
     * 
     * @param other     Recibe el nodo que se va a comparar con el otro nodo que ha llamado al método.
     * @return          Devuelve el nombre del aula.
     * 
     * @see             PriorityQueue
     */
    
    @Override
    public int compareTo(Nodo other) {
        return Double.compare(this.minDistancia, other.minDistancia);
    }    
    
} // FIN Clase Nodo
