package fds.practica;

/**
 * Esta clase ejecuta el algoritmo sin ninguna interacción del usuario.
 * Sólo es necesario que el fichero "entrada.txt" esté en la misma ubicación
 * que el ejecutable.
 * <p>
 * Técnica utilizada: Dijkstra modificado para ser no dirigido.
 * 
 * @author      David Julián Yela
 * @version     1.0
 */

public class MainConsola{
    
    /**
    * <code>String</code> que indica nombre y ubicación del fichero de entrada.
    */
    
    private static final String RUTA = "entrada.txt";
    
    /**
    * <code>String</code> que indica nombre y ubicación del fichero de salida.
    */
    
    private static final String SALIDA = "salida.txt";    
        
    /**
    * <code>String</code> que indica cuál es el nodo origen del grafo, por defecto
    *  es "FUENTE" pero puede cambiarse desde el programa con UI.
    */
    
    private static String origen = "FUENTE";
    
    public static void main(String[] args)
    {
        
        Algoritmo programa = new Algoritmo();
        
        if (programa.leerFichero(RUTA)){
            System.out.println("Se ha cargado correctamente el fichero: "+RUTA);
            
            String solucion = programa.executeDijkstra(origen);
            System.out.println("Se ha ejecutado el algoritmo:\n"+solucion);
             
            if (programa.guardarFichero(solucion, SALIDA)) {
                System.out.println("Se ha guardado la salida en el fichero: "+SALIDA);
                
            } else {
                System.out.println("No ha sido posible ejecutar el algoritmo.");
                
            }
            
            System.out.println("Saliendo de la aplicación...");
            
        } else { // No se ha podido leer el fichero
            
            System.exit(0);
            
        }
        
    }
    
}