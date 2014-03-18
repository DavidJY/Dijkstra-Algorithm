package fds.practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.PatternSyntaxException;

/**
 * Es la clase principal de la práctica. Realiza el algoritmo de Dijkstra. Permite:
 * <ul>
 * <li>Leer un fichero de texto
 * <li>Guardar un fichero de texto con la solución
 * <li>Ejecutar el algoritmo de Dijkstra sobre el conjunto de datos obtenido
 *  de la lectura del fichero.
 * </ul>
 * <p>
 * Al tener todas las operaciones necesarias, se puede ejecutar la aplicación
 *  independientemente de si ejecutamos la versión con interfaz gráfica o la
 *  versión en consola.
 * <p>
 * Técnica utilizada: Dijkstra modificado para ser no dirigido.
 * 
 * @author      David Julián Yela
 * @version     1.0
 */

public class Algoritmo {
    
     /**
     * <code>ArrayList</code> que almacena todos los nodos del grafo, o lo que es lo mismo,
     * todas las aulas de la Universidad.
     */
    
    private ArrayList<Nodo> grafo;
    
     /**
     * <code>ArrayList</code> que almacena la solución después de haber aplicado el algoritmo 
     * de Dijkstra.
     */
    
    private ArrayList<String> asignaturas;
    
     /**
     * <code>ArrayList</code> que guarda las asignaturas en las que se ha 
     * matriculado el alumno.
     */
    
    private ArrayList<String> matriculas;
    
    /**
     * <code>String</code> que define la línea separatoria que se espera leer
     * del fichero.
     */
    
    private String linea_separatoria = "<MATRICULADO>";
    
     /**
     * Constructor de la clase Algoritmo.
     */
    
    public Algoritmo () {
        this.grafo = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.matriculas = new ArrayList<>();
        
    }
    
     /** 
     * Este método lee un fichero de texto con la descripción del grafo. El método
     * crea la estructura del grafo y avisa si se ha leído correctamente o se han
     * producido fallos.
     * 
     * @param ruta      Recibe el nombre y la ruta del fichero que se va a leer.
     * @return          <code>true</code> si la lectura se ha hecho satisfactoriamente, <code>false</code> 
     *  en caso contrario.
     * 
     * @see             FileNotFoundException
     * @see             IOException
     * @see             NumberFormatException
     * @see             PatternSyntaxException
     */
    
    public boolean leerFichero (String ruta) {
        
        boolean error_formato = false;
        boolean resultado; // Variable que almacenará el estado del proceso y que se devolverá.            
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(ruta));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            // asignaturas_matriculadas: booleano que se utiliza para saber
            // cuando se ha detectado la linea_separatoria
            boolean asignaturas_matriculadas = false;
            
            while ((line != null)&&(!error_formato)) {
                
                if (line.equalsIgnoreCase(linea_separatoria)) {
                    asignaturas_matriculadas = true;
                    
                } else if (!asignaturas_matriculadas) { // Todas las lineas que definen el grafo
                    // Por cada linea hay un nodo y una arista que se guarda
                    // en ambos sentidos.

                    String[] linea = line.split(" ");
                    
                    if (linea.length == 3) {
                        // contador: entero que permite saber qué columna se está leyendo.
                        int contador = 0;

                        Nodo origen = null;                
                        Nodo destino = null;
                        int distancia = 0;

                        for (String s : linea) {

                            switch (contador) {
                                case 0: // Nodo origen
                                    if (!asignaturas.contains(s)) {
                                        // Si la asignatura no existía, se crea un nuevo
                                        // nodo y se añade el nombre al ArrayList de asignaturas
                                        origen = new Nodo(s);
                                        asignaturas.add(s);

                                    } else {
                                        // Si la asignatura ya existía, se recupera el nodo
                                        // del grafo construido hasta el momento.
                                        for (int i=0; i<grafo.size(); i++) {
                                            if (grafo.get(i).nombre.equalsIgnoreCase(s)) {
                                                origen = grafo.get(i);
                                                grafo.remove(origen);
                                            }
                                        }
                                    }

                                    break; // Fin del caso 0
                                case 1: // Nodo destino
                                    if (!asignaturas.contains(s)) {
                                        // Si la asignatura no existía se crea un nuevo nodo
                                        destino = new Nodo(s);
                                        asignaturas.add(s);
                                        grafo.add(destino);  

                                    } else {
                                        // Si la asignatura existía se recupera el nodo del grafo
                                        for (int i=0; i<grafo.size(); i++) {
                                            if (grafo.get(i).toString().equalsIgnoreCase(s)) {
                                                destino = grafo.get(i);
                                            }
                                        }
                                    }

                                    break; // Fin del case 1
                                case 2: // Distancia de la arista
                                    distancia = Integer.parseInt(s);
                                    break;
                                default: 

                            } // Fin del switch(contador)
                            contador++;                                             

                        } // Fin del for
                        
                        // Se crea la arista en ambos sentidos:
                        Arista arista = new Arista(destino, distancia);
                        Arista aristaInversa = new Arista(origen, distancia);

                        // Se añaden las aristas a los nodos:
                        origen.adyacente.add(arista);
                        destino.adyacente.add(aristaInversa);

                        grafo.add(origen);
                    
                    } else {
                        System.out.println("La entrada no tiene el formato esperado!");
                        error_formato = true;
                    }
                    
                } else { // Asignaturas matriculadas
                    matriculas.add(line);
                    
                }
                              
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine(); 
               
            } // Fin del while que lee el fichero       
            
            br.close();
            
            if (error_formato) {
                resultado = false;
            } else {
                resultado = true;
            }
            
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se ha encontrado el archivo: "+ruta+"\n"+e.getMessage());    
            resultado = false;
            
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo: "+ruta+"\n"+e.getMessage());
            resultado = false;
            
        } catch (NumberFormatException e) {
            System.out.println("Error al leer la tercera columna que debe contener valores numéricos:\n"+e.getMessage());
            resultado = false;
            
        } catch (PatternSyntaxException e) {
            System.out.println("Error en el patrón del fichero. Recuerde que debe contener tres columnas separadas"
                    + " por espacios. En la primera columna el origen, en la segunda el destino y en la tercera la distancia.\n"+e.getMessage());
            resultado = false;
            
        }
        
        return resultado;
        
    } // FIN leerfichero()
    
     /** 
     * Este método lee un fichero de texto con la descripción del grafo. El método
     * crea la estructura del grafo y avisa si se ha leído correctamente o se han
     * producido fallos.
     * 
     * @param solucion      Es el texto que se guardará en el fichero.
     * @param ruta          Es la ruta y el nombre del fichero donde se guardará el resultado.
     * 
     * @return              <code>true</code> si la escritura se ha hecho satisfactoriamente, <code>false</code> 
     *                      en caso contrario.
     * 
     * @see             FileNotFoundException
     * @see             IOException
     * @see             File
     * @see             FileWriter
     * @see             BufferedWriter
     * @see             PrintWriter
     */
    
    public boolean guardarFichero (String solucion, String ruta) {
        
        boolean estado;        
        File fichero = new File(ruta);
	
	try {
            
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter wr = new PrintWriter(bw);

            String [] parciales = solucion.split("\n");
            
            for (String linea : parciales) {
                wr.write(linea);//escribimos la linea en el archivo
                wr.append(System.lineSeparator());
            }


            wr.close();
            bw.close();
            
            estado = true;
            
	} catch (IOException | SecurityException | IllegalArgumentException e) {
            
            System.out.println("Error al escribir el fichero.\n"+e.getMessage());
            estado = false;
            
        } 
        
        return estado;

    }
    

     /** 
     * Este método calcula el grafo realizando el algoritmo de Dijkstra, para ello necesita
     * recibir el nodo origen desde el cual empezaran y acabarán todas las rutas.
     * 
     * @param origen        Nodo que representa el nodo origen.
     * 
     * @see                 PriorityQueue
     * @see                 Nodo
     * @see                 Arista
     */
    
    private void construirGrafo (Nodo origen) {
        
        origen.minDistancia = 0;
        
        PriorityQueue<Nodo> cola = new PriorityQueue<>();
      	cola.add(origen);

	while (!cola.isEmpty()) {

	    Nodo u = cola.poll();

            for (Arista e : u.adyacente) {
                
                Nodo v = e.destino;
                int auxDistancia = e.distancia;
                int distanciaAcumulada = u.minDistancia + auxDistancia;
                
		if (distanciaAcumulada < v.minDistancia) {
		    cola.remove(v);
                    this.grafo.remove(v);
		    v.minDistancia = distanciaAcumulada ;
		    v.previo = u;
		    cola.add(v);
                    this.grafo.add(v);
		}
            }
        }
    }

     /** 
     * Este método recibe el destino al cual se quiere ir y genera la solución
     * para que pueda mostrarse al usuario.
     * 
     * @param destino       Nodo destino que representa el aula que se desea calcular.
     * 
     * @see                 List
     * @see                 Collections.reverse
     * @see                 Arista
     */
    
    private List<Nodo> calcularRutaNodo (Nodo destino) {
        List<Nodo> path = new ArrayList<>();
        
        for (Nodo aux = destino; aux != null; aux = aux.previo) {
            path.add(aux);
        }
        
        Collections.reverse(path);
        
        return path;
    }
    
     /** 
     * Este método ejecuta y gestiona los pasos necesarios para llevar a cabo el algoritmo.
     * 
     * @param nodo          Se trata del nodo origen del grafo.
     * 
     * @return              Devuelve la solución en formato <code>String</code>.
     * 
     * @see                 StringBuffer
     * @see                 Collections.reverse
     * @see                 Arista
     */
    
    public String executeDijkstra (String nodo) {
        
        StringBuilder buffer = new StringBuilder();
        
        final String origen = nodo;
            
        int posicion = 0;
        int costeTotal = 0;
        
        // Se obtiene la posición donde se encuentra el nodo origen
        for (int i=0; i<grafo.size(); i++) {
            if (grafo.get(i).toString().equalsIgnoreCase(origen)) {
                posicion = i;
            }            
        }
        
        // Se construye el grafo indicando el nodo origen
        construirGrafo(grafo.get(posicion));

        // Se calculan los caminos mínimos para las asignaturas matriculadas
        for (int i= 0; i<matriculas.size(); i++) {
            
            String clase = matriculas.get(i);
            int contador = 0;
            while (!grafo.get(contador).toString().equalsIgnoreCase(clase)) {
                contador++;
            }
            
            Nodo claseMatriculada = grafo.get(contador);
            List<Nodo> path = calcularRutaNodo (claseMatriculada);

            for (int j=0; j<path.size();j++) {
                buffer.append(path.get(j)).append(" ");
                
            }

            buffer.append("CosteIda = ")
                    .append(claseMatriculada.minDistancia)
                    .append("\n");
            
            costeTotal = costeTotal + claseMatriculada.minDistancia;

        }
        
        buffer.append("Coste Total = ")
                .append(costeTotal*2);
        
        System.out.println("Coste Total = " + costeTotal*2);
        
        return buffer.toString();
    }
    
     /** 
     * Este método devuelve un <code>ArrayList</code> de <code>String</code> con
     * la solución del problema.
     * 
     * @param nodo          Se trata del nodo origen del grafo.
     * 
     * @return              Devuelve la solución en formato <code>String</code>.
     */
    public ArrayList<String> getAsignaturas () {
        return this.asignaturas;
    }
    
    public ArrayList<String> getMatriculadas() {
        return this.matriculas;
    }
}
