
package singh.mahabir.exception.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * This class is the default response of the application when application thrown
 * any exceptoin
 *
 * @author Mahabir Singh
 *
 */
// @XmlRootElement
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
	return status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @return the errors
     */
    public List<String> getErrors() {
	return errors;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HttpStatus status) {
	this.status = status;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<String> errors) {
	this.errors = errors;
    }

    public ApiError() {
	super();
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
	super();
	this.status = status;
	this.message = message;
	this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
	super();
	this.status = status;
	this.message = message;
	errors = Arrays.asList(error);
    }
}
