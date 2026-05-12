package Actividades;

public class Producto implements Comparable<Producto> {
    private int codigo;
    private String nombre;

    public Producto(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Producto o) {
        // El BST usará el código para ordenar los productos
        return Integer.compare(this.codigo, o.codigo);
    }

    @Override
    public String toString() {
        return "[ID:" + codigo + " " + nombre + "]";
    }
}