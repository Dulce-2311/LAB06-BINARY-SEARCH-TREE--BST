package Actividades.bstreelinklistinterfgeneric; // Define el paquete donde se encuentra la implementación del árbol BST

import Actividades.Exceptions.ExceptionIsEmpty; // Importa la interfaz que contiene los métodos obligatorios del BST
import Actividades.Exceptions.ItemDuplicated; // Importa la excepción para elementos duplicados
import Actividades.Exceptions.ItemNoFound; // Importa la excepción para elementos no encontrados
import Actividades.bstreeInterface.BinarySearchTree; // Importa la excepción para estructuras vacías
import java.util.LinkedList; // Importación necesaria para el uso de colas en métodos iterativos
import java.util.Queue;

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

    // =========================================================================
    // NUEVOS MÉTODOS - EJERCICIO 01 (Implementados por Estudiante 4)
    // =========================================================================

   // a. destroyNodes(): Borra todo el contenido del árbol, pero primero chequea que no esté vacío
    public void destroyNodes() throws ExceptionIsEmpty {
        // Si no hay nodos, lanzamos la excepción personalizada según pide la guía [cite: 510]
        if (isEmpty()) {
            throw new ExceptionIsEmpty("No se puede destruir: El árbol ya está vacío");
        }
        // Al setear la raíz en null, Java desconecta toda la estructura y libera la memoria [cite: 190]
        this.root = null;
    }

    // b. countAllNodes(): Punto de entrada para contar los nodos internos (no-hojas) [cite: 511]
    public int countAllNodes() {
        return countNonLeafRec(root);
    }

    // c. countNodes(): Función duplicada para cumplir con los incisos b y c de la guía [cite: 513]
    public int countNodes() {
        return countNonLeafRec(root);
    }

    // Lógica recursiva para contar solo nodos que tienen al menos un hijo
    private int countNonLeafRec(Node<E> node) {
        // Si el nodo es nulo o es una hoja (no tiene hijos), no lo contamos [cite: 119]
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        // Si llegamos aquí es un nodo interno: sumamos 1 y seguimos buscando en ambas ramas
        return 1 + countNonLeafRec(node.left) + countNonLeafRec(node.right);
    }

    // d. height(x): Busca un valor 'x' y calcula la altura de ese subárbol sin usar recursividad [cite: 514, 515]
    public int height(E x) {
        Node<E> current = root;
        // Primero recorremos el BST para localizar el nodo que contiene el dato 'x'
        while (current != null) {
            int cmp = x.compareTo(current.data);
            if (cmp == 0) break; // Lo encontramos
            // Bajamos por la izquierda si es menor o por la derecha si es mayor [cite: 171, 172]
            current = (cmp < 0) ? current.left : current.right;
        }

        // Si el bucle termina y current es null, el valor no existe en el árbol [cite: 516]
        if (current == null) return -1; 

        // Una vez localizado, calculamos su altura con un algoritmo iterativo
        return calculateHeightIterative(current);
    }

    // Método de apoyo para medir la profundidad máxima usando una cola (recorrido por niveles) [cite: 521]
    private int calculateHeightIterative(Node<E> startNode) {
        if (startNode == null) return -1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(startNode);
        int h = -1; // La altura de la raíz sola se considera 0 [cite: 125]
        
        while (!queue.isEmpty()) {
            int size = queue.size(); // Cantidad de nodos en el nivel actual
            h++; 
            // Procesamos todos los nodos de este nivel antes de pasar al siguiente
            while (size-- > 0) {
                Node<E> temp = queue.poll();
                // Encolamos los hijos para procesarlos como el siguiente nivel de altura
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return h;
    }

    // e. amplitude(): Calcula el ancho máximo del árbol (el nivel con más nodos) [cite: 521]
    public int amplitude() {
        if (root == null) return 0;
        int maxW = 0; // Aquí guardaremos el récord de nodos encontrados en un nivel
        Queue<Node<E>> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            // El tamaño actual de la cola nos dice cuántos nodos hay exactamente en este nivel
            int count = q.size();
            // Si este nivel es más ancho que los anteriores, actualizamos el máximo
            if (count > maxW) maxW = count;
            
            // Vaciamos el nivel actual y metemos los hijos de estos nodos para el siguiente ciclo
            while (count-- > 0) {
                Node<E> temp = q.poll();
                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            }
        }
        return maxW;
    }

    // --- EJERCICIO 02.a: areaBST() ---
    // El área es: (número de hojas) * (altura del árbol)
    public int areaBST() {
        if (isEmpty()) return 0;

        int leafCount = 0;
        int height = -1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        // Usamos un recorrido por niveles (BFS) para contar hojas y calcular altura simultáneamente
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++; // Cada vez que entramos aquí, subimos un nivel de altura
            
            for (int i = 0; i < levelSize; i++) {
                Node<E> temp = queue.poll();
                
                // Si no tiene hijos, es una hoja
                if (temp.left == null && temp.right == null) {
                    leafCount++;
                }
                
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return leafCount * height;
    }

    // --- EJERCICIO 02.b: drawBST() ---
    // Método público para iniciar la impresión gráfica del árbol en la consola [cite: 527]
    public void drawBST() {
        // Validamos si no hay nada que mostrar para evitar errores
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }
        // Mostramos primero el recorrido lineal y luego la estructura jerárquica
        System.out.println("Representación del Árbol (InOrder): " + this.toString());
        drawRec(root, "", true); // Llamada al método recursivo con el prefijo inicial
    }

    // Método auxiliar para dibujar las ramas y nodos usando caracteres visuales
    private void drawRec(Node<E> node, String prefix, boolean isLeft) {
        if (node != null) {
            // Imprime el prefijo actual y el símbolo de la rama dependiendo de si es hijo izquierdo o derecho
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.data);
            
            // Llamadas recursivas para bajar por el árbol
            // El prefijo se va extendiendo para mantener la alineación de las líneas verticales
            drawRec(node.left, prefix + (isLeft ? "│   " : "    "), true);
            drawRec(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    // --- EJERCICIO 03: parenthesize() ---
    // Método principal para llamar a la recursión desde la raíz y armar la cadena con paréntesis
    public String parenthesize() {
        return parenthesizeRec(root);
    }

    // Lógica recursiva para mostrar la estructura jerárquica del BST
    private String parenthesizeRec(Node<E> node) {
        if (node == null) {
            return ""; // Caso base: si llegamos a un espacio vacío, no devolvemos texto
        }
        
        // Empezamos guardando el valor del nodo actual como texto
        String result = node.data.toString();
        
        // Si no tiene hijos a los lados, es una hoja y solo retornamos su valor
        if (node.left == null && node.right == null) {
            return result;
        }
        
        // Si llegamos acá es porque tiene descendientes, así que abrimos paréntesis para agruparlos
        result += " (";
        // Si el hijo izquierdo existe lo procesamos, si no, ponemos un guion como marcador de vacío
        result += (node.left != null) ? parenthesizeRec(node.left) : "-"; 
        result += ", "; // Separador entre los dos subárboles
        // Hacemos lo mismo con el lado derecho
        result += (node.right != null) ? parenthesizeRec(node.right) : "-";
        result += ")"; // Cerramos el nivel actual
        
        return result;
    }

    // --- EJERCICIO 04: isValidBST() ---
    // Método principal que arranca la validación de todo el árbol
    public boolean isValidBST() {
        // Iniciamos la recursión pasando null en los límites para representar el infinito
        return validateBST(root, null, null);
    }

    // Método de apoyo que revisa si los nodos respetan sus límites según la posición
    private boolean validateBST(Node<E> node, E min, E max) {
        // Si llegamos a una hoja o el árbol está vacío, por lógica es válido
        if (node == null) {
            return true;
        }

        // Si el nodo actual es menor o igual al mínimo permitido, rompemos la regla
        if (min != null && node.data.compareTo(min) <= 0) {
            return false;
        }
        
        // Si el nodo actual es mayor o igual al máximo permitido, también está mal
        if (max != null && node.data.compareTo(max) >= 0) {
            return false;
        }

        // Seguimos revisando hacia abajo:
        // Al ir a la izquierda, el nodo actual se vuelve el nuevo "máximo"
        // Al ir a la derecha, el nodo actual se convierte en el nuevo "mínimo"
        return validateBST(node.left, min, node.data) && 
               validateBST(node.right, node.data, max);
    }

    // --- EJERCICIO 05.b: searchRange(min, max) ---
    // Busca y muestra los productos que están en el rango de códigos indicado
    public void searchRange(E min, E max) {
        searchRangeRec(root, min, max);
    }

    private void searchRangeRec(Node<E> node, E min, E max) {
        if (node == null) return;

        // Si el valor actual es mayor al mínimo, puede haber más a la izquierda
        if (min.compareTo(node.data) < 0) {
            searchRangeRec(node.left, min, max);
        }

        // Si está dentro del rango, lo imprimimos
        if (min.compareTo(node.data) <= 0 && max.compareTo(node.data) >= 0) {
            System.out.print(node.data + " ");
        }

        // Si el valor actual es menor al máximo, puede haber más a la derecha
        if (max.compareTo(node.data) > 0) {
            searchRangeRec(node.right, min, max);
        }
    }

    // --- EJERCICIO 05.c: countLeaves() ---
    // Cuenta cuántos productos son nodos hoja (los que no tienen hijos)
    public int countLeaves() {
        return countLeavesRec(root);
    }

    private int countLeavesRec(Node<E> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1; // Encontramos una hoja
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    // --- EJERCICIO 05.d: printDescending() ---
    // Muestra los productos de mayor a menor (Derecha - Raíz - Izquierda)
    public void printDescending() {
        printDescendingRec(root);
        System.out.println();
    }

    private void printDescendingRec(Node<E> node) {
        if (node != null) {
            printDescendingRec(node.right); // Primero lo más grande
            System.out.print(node.data + " ");
            printDescendingRec(node.left); // Al final lo más pequeño
        }
    }

}