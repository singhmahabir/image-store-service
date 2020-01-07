
package singh.mahabir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import singh.mahabir.exception.model.ApiError;

/**
 * This is the class where we can handle any exception thrown by our application
 * and we can re-send the appropriate response to consumer.
 *
 * @author Mahabir Singh
 *
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerMapper {

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ApiError> imageNotFoundException(ImageNotFoundException ex) {
	final ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString());
	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlbumNotFoundexception.class)
    public ResponseEntity<ApiError> albumNotFoundException(AlbumNotFoundexception ex) {
	final ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString());
	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageProcessingexception.class)
    public ResponseEntity<ApiError> imageProcessingexception(ImageProcessingexception ex) {
	final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.toString());
	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> exceptionHandler(Exception ex) {
	final ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.toString());
	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
