package Actividades.bstreeInterface;

import Actividades.Exceptions.ItemNoFound; // Importa la excepción que se lanzará cuando no se encuentre un elemento en el árbol
import Actividades.Exceptions.ExceptionIsEmpty; // Importa la excepción que se usará cuando se intente operar sobre una estructura vacía
import Actividades.Exceptions.ItemDuplicated;

public interface BinarySearchTree<E> { // Declara una interfaz genérica donde E representa el tipo de dato almacenado en el árbol

    void insert(E data) throws ItemDuplicated; // Define el método para insertar un dato; lanza excepción si el dato ya existe

    E search(E data) throws ItemNoFound; // Define el método para buscar un dato; retorna el dato encontrado o lanza excepción si no existe

    void delete(E data) throws ExceptionIsEmpty; // Define el método para eliminar un dato; lanza excepción si el árbol está vacío

    boolean isEmpty(); // Define un método que verifica si el árbol está vacío, retornando true o false según corresponda

    void destroy(); // Define un método para eliminar todos los elementos del árbol, dejándolo vacío

    String toString(); // Define un método que permite representar el árbol en forma de cadena de texto
}