package Actividades.Exceptions; // Establece que esta clase forma parte del paquete Exceptions

public class ExceptionIsEmpty extends Exception { // Define una excepción personalizada que se lanza cuando la estructura está vacía

    public ExceptionIsEmpty(String message) { // Constructor que recibe el mensaje que describe el error
        super(message); // Envía el mensaje a la clase base Exception para que pueda ser mostrado cuando ocurra la excepción
    }
}