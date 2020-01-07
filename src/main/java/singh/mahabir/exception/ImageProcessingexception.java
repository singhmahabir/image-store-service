/**
 *
 */
package singh.mahabir.exception;

import java.io.IOException;

/**
 * @author Mahabir Singh
 *
 */
public class ImageProcessingexception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImageProcessingexception() {
    }

    /**
     * @param e
     */
    public ImageProcessingexception(IOException e) {
	super(e);
    }

}
