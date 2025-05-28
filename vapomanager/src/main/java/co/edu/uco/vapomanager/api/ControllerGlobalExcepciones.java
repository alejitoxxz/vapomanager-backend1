package co.edu.uco.vapomanager.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.edu.uco.vapomanager.crosscutting.excepciones.VapomanagerException;

@ControllerAdvice
public class ControllerGlobalExcepciones {
	
    @ExceptionHandler(VapomanagerException.class)
    public ResponseEntity<String> controlarVapomanagerException(VapomanagerException excepcion) {
        excepcion.printStackTrace();
        return new ResponseEntity<>(excepcion.getMensajeUsuario(), HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> controlarException(Exception excepcion) {
        excepcion.printStackTrace();
        return new ResponseEntity<>("Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada", 
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
