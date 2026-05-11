package Actividades.Exceptions; // Define el paquete al que pertenece esta clase, facilitando su organización en el proyecto

public class ItemNoFound extends Exception { // Crea una excepción personalizada para indicar que un elemento no fue encontrado en la estructura

    public ItemNoFound(String message) { // Constructor que permite especificar el mensaje del error
        super(message); // Transfiere el mensaje al constructor de Exception para su manejo interno
    }
}