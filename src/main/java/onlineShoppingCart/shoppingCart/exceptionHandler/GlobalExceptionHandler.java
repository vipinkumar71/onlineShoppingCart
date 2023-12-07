package onlineShoppingCart.shoppingCart.exceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import onlineShoppingCart.shoppingCart.customException.*;
import onlineShoppingCart.shoppingCart.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        System.out.println("in global handler : method arg invalid");
        Map<String, String> errorMap = ex.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        System.out.println("in global handler : method arg type mismatch " + ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(
                "Type mismatch for param : " + ex.getName() + " Details : " + ex.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        System.out.println("in global handler : method arg invalid");
        Map<Object, String> errorMap = new HashMap<>(); // Stream<FieldError>
        ex.getConstraintViolations().forEach((e) -> {
            Object key = e.getPropertyPath();
            String val = e.getMessage();
            errorMap.put(key, val);
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        System.out.println("err " + e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        System.out.println("in run time Exception handler");
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(ElementAlreadyExistsException elementException) {
        return new ResponseEntity<String>(
                "Conflict:" + elementException.getElementName() + elementException.getErrorMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<String> handleOutOfStockException(OutOfStockException elementException) {
        return new ResponseEntity<String>(
                "Conflict:" + elementException.getElementName() + " " + elementException.getErrorMessage(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceException) {
        return new ResponseEntity<String>("Not found: Resource you are trying to find not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {
        return new ResponseEntity<String>("Input fields are empty", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ElementNotFountException.class)
    public ResponseEntity<String> handleElementNotFoundException(ElementNotFountException elementException) {
        return new ResponseEntity<String>(elementException.getElementName() + " " + elementException.getErrorMessage(),
                HttpStatus.NOT_FOUND);
    }
}
