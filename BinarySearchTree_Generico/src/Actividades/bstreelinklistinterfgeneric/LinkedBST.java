package Actividades.bstreelinklistinterfgeneric; // Define el paquete donde se encuentra la implementación del árbol BST

import Actividades.bstreeInterface.BinarySearchTree; // Importa la interfaz que contiene los métodos obligatorios del BST
import Actividades.Exceptions.ItemDuplicated; // Importa la excepción para elementos duplicados
import Actividades.Exceptions.ItemNoFound; // Importa la excepción para elementos no encontrados
import Actividades.Exceptions.ExceptionIsEmpty; // Importa la excepción para estructuras vacías

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> { // Define una clase genérica BST cuyos elementos pueden compararse

    private Node<E> root; // Referencia al nodo raíz del árbol, desde donde inicia toda la estructura

    public LinkedBST() { // Constructor que crea un árbol vacío
        root = null; // Inicializa la raíz como null indicando que no existen nodos
    }

    @Override
    public boolean isEmpty() { // Método que verifica si el árbol contiene elementos
        return root == null; // Retorna true si la raíz es null, es decir, el árbol está vacío
    }

    @Override
    public void destroy() { // Método que elimina todos los nodos del árbol
        root = null; // Elimina la referencia a la raíz dejando el árbol vacío
    }

    @Override
    public void insert(E data) throws ItemDuplicated { // Método público que inserta un dato en el árbol
        root = insertRec(root, data); // Llama al método recursivo de inserción y actualiza la raíz
    }

    private Node<E> insertRec(Node<E> node, E data) throws ItemDuplicated { // Método recursivo que inserta respetando las reglas BST

        if (node == null) { // Si el nodo actual está vacío
            return new Node<>(data); // Crea un nuevo nodo e inserta el dato en esa posición
        }

        int cmp = data.compareTo(node.data); // Compara el nuevo dato con el dato del nodo actual

        if (cmp == 0) { // Si el resultado es 0 significa que el dato ya existe
            throw new ItemDuplicated("Elemento duplicado"); // Lanza excepción porque no se permiten repetidos

        } else if (cmp < 0) { // Si el resultado es negativo el dato es menor
            node.left = insertRec(node.left, data); // Inserta recursivamente en el subárbol izquierdo

        } else { // Si el resultado es positivo el dato es mayor
            node.right = insertRec(node.right, data); // Inserta recursivamente en el subárbol derecho
        }

        return node; // Retorna el nodo actualizado manteniendo la estructura del árbol
    }

    @Override
    public E search(E data) throws ItemNoFound { // Método público que busca un elemento dentro del árbol

        Node<E> result = searchRec(root, data); // Llama al método recursivo iniciando desde la raíz

        if (result == null) { // Si el resultado es null significa que el elemento no existe
            throw new ItemNoFound("Elemento no encontrado"); // Lanza excepción indicando que no se encontró el dato
        }

        return result.data; // Retorna el dato encontrado dentro del nodo
    }

    private Node<E> searchRec(Node<E> node, E data) { // Método recursivo encargado de recorrer el árbol buscando el dato

        if (node == null) { // Si el nodo es null significa que se llegó al final sin encontrar el dato
            return null; // Retorna null indicando ausencia del elemento
        }

        int cmp = data.compareTo(node.data); // Compara el dato buscado con el nodo actual

        if (cmp == 0) { // Si son iguales
            return node; // Retorna el nodo encontrado

        } else if (cmp < 0) { // Si el dato buscado es menor
            return searchRec(node.left, data); // Continúa la búsqueda en el lado izquierdo

        } else { // Si el dato buscado es mayor
            return searchRec(node.right, data); // Continúa la búsqueda en el lado derecho
        }
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty { // Método público encargado de eliminar un elemento

        if (isEmpty()) { // Verifica si el árbol está vacío antes de eliminar
            throw new ExceptionIsEmpty("El árbol está vacío"); // Lanza excepción porque no hay elementos que eliminar
        }

        root = deleteRec(root, data); // Llama al método recursivo de eliminación
    }

    private Node<E> deleteRec(Node<E> node, E data) { // Método recursivo que elimina respetando las reglas BST

        if (node == null) { // Si el nodo es null no hay nada que eliminar
            return null; // Retorna null manteniendo la estructura
        }

        int cmp = data.compareTo(node.data); // Compara el dato a eliminar con el nodo actual

        if (cmp < 0) { // Si el dato es menor
            node.left = deleteRec(node.left, data); // Busca el elemento en el subárbol izquierdo

        } else if (cmp > 0) { // Si el dato es mayor
            node.right = deleteRec(node.right, data); // Busca el elemento en el subárbol derecho

        } else { // Si el dato fue encontrado

            if (node.left == null) { // Caso donde no existe hijo izquierdo
                return node.right; // El hijo derecho reemplaza al nodo eliminado
            }

            if (node.right == null) { // Caso donde no existe hijo derecho
                return node.left; // El hijo izquierdo reemplaza al nodo eliminado
            }

            Node<E> min = findMin(node.right); // Busca el menor nodo del subárbol derecho

            node.data = min.data; // Reemplaza el dato del nodo actual por el menor encontrado

            node.right = deleteRec(node.right, min.data); // Elimina el nodo duplicado del lado derecho
        }

        return node; // Retorna el nodo actualizado
    }

    private Node<E> findMin(Node<E> node) { // Método que encuentra el menor elemento del árbol o subárbol

        while (node.left != null) { // Mientras exista un hijo izquierdo
            node = node.left; // Continúa avanzando hacia la izquierda
        }

        return node; // Retorna el nodo más pequeño encontrado
    }

    @Override
    public String toString() { // Método que convierte el árbol en texto
        return inOrder(root); // Retorna el recorrido inorder iniciando desde la raíz
    }

    private String inOrder(Node<E> node) { // Método recursivo que realiza el recorrido inorder

        if (node == null) { // Si el nodo es null
            return ""; // Retorna cadena vacía para finalizar la recursión
        }

        return inOrder(node.left) + node.data + " " + inOrder(node.right); // Recorre izquierda, raíz y derecha concatenando resultados
    }
    
    public void inOrderPrint() { // Método público que inicia el recorrido inorder mostrando datos en consola
        inOrderPrintRec(root); // Llama al método recursivo comenzando desde la raíz
    }

    // INORDER: Método público que inicia el recorrido inorder desde la raíz del árbol para mostrar los elementos en orden ascendente IN ORDER
    private void inOrderPrintRec(Node<E> node) { // Método recursivo que recorre el árbol en inorder

        if (node != null) { // Verifica que el nodo exista antes de continuar

            inOrderPrintRec(node.left); // Recorre primero el subárbol izquierdo

            System.out.print(node.data + " "); // Imprime el dato actual del nodo

            inOrderPrintRec(node.right); // Recorre finalmente el subárbol derecho
        }
    }
    // PREORDER: Método público que inicia el recorrido preorder desde la raíz del árbol mostrando primero la raíz y luego sus subárboles
    public void preOrderPrint() { // Método que inicia el recorrido preorder
        preOrderPrintRec(root); // Llama al método recursivo comenzando desde la raíz
    }

    private void preOrderPrintRec(Node<E> node) { // Método recursivo que realiza el recorrido preorder

        if (node != null) { // Verifica que el nodo actual exista antes de continuar

            System.out.print(node.data + " "); // Imprime primero el dato del nodo actual

            preOrderPrintRec(node.left); // Recorre luego el subárbol izquierdo

            preOrderPrintRec(node.right); // Finalmente recorre el subárbol derecho
        }
    }
    
    // POSTORDER: Método público que inicia el recorrido PostOrder desde la raíz del árbol mostrando primero los subárboles y al final la raíz
    public void postOrderPrint() { // Método que inicia el recorrido postorder
        postOrderPrintRec(root); // Llama al método recursivo comenzando desde la raíz
    }

    private void postOrderPrintRec(Node<E> node) { // Método recursivo que realiza el recorrido postorder

        if (node != null) { // Verifica que el nodo actual exista antes de continuar

            postOrderPrintRec(node.left); // Recorre primero el subárbol izquierdo

            postOrderPrintRec(node.right); // Recorre luego el subárbol derecho

            System.out.print(node.data + " "); // Imprime finalmente el dato del nodo actual
        }
    }
    // VALOR MINIMO: Método público que obtiene el nodo con el valor mínimo del árbol recorriendo hacia la izquierda
    public E findMin() { // Método que retorna el menor elemento del BST

        Node<E> current = root; // Comienza el recorrido desde la raíz

        while (current.left != null) { // Mientras exista un hijo izquierdo
            current = current.left; // Continúa avanzando hacia la izquierda
        }

        return current.data; // Retorna el dato mínimo encontrado
    }

    // VALOR MAXIMO: Método público que obtiene el nodo con el valor máximo del árbol recorriendo hacia la derecha
    public E findMax() { // Método que retorna el mayor elemento del BST

        Node<E> current = root; // Comienza el recorrido desde la raíz

        while (current.right != null) { // Mientras exista un hijo derecho
            current = current.right; // Continúa avanzando hacia la derecha
        }

        return current.data; // Retorna el dato máximo encontrado
    }
}