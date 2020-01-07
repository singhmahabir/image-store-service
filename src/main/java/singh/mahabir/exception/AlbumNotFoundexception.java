/**
 *
 */
package singh.mahabir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mahabir Singh
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlbumNotFoundexception extends RuntimeException {

    /**
     * @param string
     */
    public AlbumNotFoundexception(String ex) {
	super(ex);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
