package Actividades.bstreeInterface;

import Actividades.Exceptions.ExceptionIsEmpty; // Importa la excepción que se lanzará cuando no se encuentre un elemento en el árbol
import Actividades.Exceptions.ItemDuplicated; // Importa la excepción que se usará cuando se intente operar sobre una estructura vacía
import Actividades.Exceptions.ItemNoFound;

public interface BinarySearchTree<E> { // Declara una interfaz genérica donde E representa el tipo de dato almacenado en el árbol

    void insert(E data) throws ItemDuplicated; // Define el método para insertar un dato; lanza excepción si el dato ya existe

    E search(E data) throws ItemNoFound; // Define el método para buscar un dato; retorna el dato encontrado o lanza excepción si no existe

    void delete(E data) throws ExceptionIsEmpty; // Define el método para eliminar un dato; lanza excepción si el árbol está vacío

    boolean isEmpty(); // Define un método que verifica si el árbol está vacío, retornando true o false según corresponda

    void destroy(); // Define un método para eliminar todos los elementos del árbol, dejándolo vacío

    String toString(); // Define un método que permite representar el árbol en forma de cadena de texto

    // --- MÉTODOS AÑADIDOS PARA EL EJERCICIO 01 ---

    void destroyNodes() throws ExceptionIsEmpty; // Define un método para eliminar todos los nodos validando si la estructura está vacía

    int countAllNodes(); // Define un método que retorna el número de nodos no-hojas del árbol

    int countNodes(); // Define un método que retorna el número de nodos no-hojas del árbol

    int height(E x); // Define un método que retorna la altura del subárbol cuya raíz tiene el valor 'x'

    int amplitude(); // Define un método que retorna el número máximo de nodos que existen en un nivel determinado

    int areaBST(); // Firma para el inciso a del Ejercicio 02
    void drawBST(); // Firma para el inciso b del Ejercicio 02
    String parenthesize(); // Firma para el Ejercicio 03
    boolean isValidBST(); // Firma para el Ejercicio 04

    // Define un método para buscar y mostrar elementos dentro de un rango de valores
    void searchRange(E min, E max);

    // Define un método que cuenta específicamente el número de nodos hoja
    int countLeaves();

    // Define un método para mostrar los elementos del árbol en orden descendente
    void printDescending();
}