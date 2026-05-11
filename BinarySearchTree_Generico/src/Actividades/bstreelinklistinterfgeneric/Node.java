package Actividades.bstreelinklistinterfgeneric;

public class Node<E> { // Define una clase genérica donde E es el tipo de dato que almacenará el nodo

    E data; // Variable que guarda el valor del nodo (el dato real)
    Node<E> left; // Referencia al hijo izquierdo (valores menores)
    Node<E> right; // Referencia al hijo derecho (valores mayores)

    public Node(E data) { // Constructor que inicializa el nodo con un dato
        this.data = data; // Asigna el valor recibido al nodo actual
        this.left = null; // Inicializa el hijo izquierdo como vacío
        this.right = null; // Inicializa el hijo derecho como vacío
    }
}