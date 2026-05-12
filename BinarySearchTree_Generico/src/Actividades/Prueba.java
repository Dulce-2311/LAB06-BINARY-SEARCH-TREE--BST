package Actividades;

import Actividades.bstreelinklistinterfgeneric.LinkedBST; // Importamos la implementación del árbol genérico

public class Prueba {
    public static void main(String[] args) {
        // --- PRUEBA EJERCICIO 02: COMPARACIÓN DE ÁREAS ---
        // Creamos dos árboles de enteros para validar el cálculo del área [cite: 529]
        LinkedBST<Integer> arbol1 = new LinkedBST<>();
        LinkedBST<Integer> arbol2 = new LinkedBST<>();

        try {
            // Llenamos el primer árbol
            arbol1.insert(50);
            arbol1.insert(30);
            arbol1.insert(70);

            // Llenamos el segundo árbol
            arbol2.insert(40);
            arbol2.insert(20);
            arbol2.insert(60);

            // Mostramos los resultados del cálculo del área (hojas * altura) [cite: 523, 524]
            System.out.println("Área Árbol 1: " + arbol1.areaBST());
            System.out.println("Área Árbol 2: " + arbol2.areaBST());
            
            // Verificamos si ambos árboles tienen la misma superficie [cite: 529]
            if (sameArea(arbol1, arbol2)) {
                System.out.println("Ambos árboles tienen la misma área.");
            }

            // --- EJERCICIO 05: GESTIÓN DE PRODUCTOS (Caso Aplicado) --- 
            // Implementación de un inventario real usando el BST genérico [cite: 564]
            System.out.println("\n--- EJERCICIO 5: INVENTARIO DE PRODUCTOS ---");
            LinkedBST<Producto> inventario = new LinkedBST<>();

            // a. Insertar productos en el sistema respetando el orden por código [cite: 566]
            inventario.insert(new Producto(100, "Laptop Gaming"));
            inventario.insert(new Producto(50, "Teclado Mecánico"));
            inventario.insert(new Producto(150, "Monitor 4K"));
            inventario.insert(new Producto(75, "Mouse Inalámbrico"));

            // d. Mostrar el inventario completo de mayor a menor código [cite: 569]
            System.out.println("Inventario en orden descendente:");
            inventario.printDescending();

            // b. Buscar productos dentro de un rango específico de IDs [cite: 567]
            System.out.println("\nBuscando productos entre ID 60 e ID 110:");
            inventario.searchRange(new Producto(60, ""), new Producto(110, ""));

            // c. Contar cuántos productos están en los nodos finales (hojas) del árbol [cite: 568]
            System.out.println("\n\nTotal de productos en nodos hoja: " + inventario.countLeaves());

        } catch (Exception e) {
            // Capturamos cualquier error o excepción que ocurra durante las operaciones
            System.out.println("Error detectado: " + e.getMessage());
        }
    }

    // --- EJERCICIO 02.c: sameArea --- 
    // Compara el área calculada de dos árboles distintos y retorna si son iguales [cite: 529]
    public static boolean sameArea(LinkedBST<?> tree1, LinkedBST<?> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
}