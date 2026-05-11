package Actividades.Exceptions; // Indica que esta clase pertenece al paquete "Exceptions", permitiendo organizar y reutilizar el código mediante importaciones

public class ItemDuplicated extends Exception { // Define una excepción personalizada que hereda de Exception para manejar errores cuando un dato ya existe

    public ItemDuplicated(String message) { // Constructor que recibe un mensaje descriptivo del error al momento de lanzar la excepción
        super(message); // Llama al constructor de la clase padre (Exception) y le pasa el mensaje para que sea gestionado y mostrado
    }
}