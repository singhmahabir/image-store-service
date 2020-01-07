/**
 *
 */
package singh.mahabir.exception;

import lombok.Getter;

/**
 * @author Mahabir Singh
 *
 */
@Getter
public class ImageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImageNotFoundException(String error) {
	super(error);
    }

}
