/**
 *
 */
package singh.mahabir.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mahabir Singh
 *
 */
@EnableBinding(NotificationSource.class)
@Service
@Slf4j
public class NotificationPublisher {

    @Autowired
    NotificationSource mysource;

    public String publishImageNotification(String message, String type) {
	log.info("publishing notification message {}", message);
	String result = "";
	if (type.equalsIgnoreCase(NotificationConstant.CREATE.name())) {
	    mysource.createImageInAlbum().send(MessageBuilder.withPayload(message).build());
	    result = NotificationConstant.SUCCESS.name();
	} else if (type.equalsIgnoreCase(NotificationConstant.DELETE.name())) {
	    mysource.deleteImageInAlbum().send(MessageBuilder.withPayload(message).build());
	    result = NotificationConstant.SUCCESS.name();
	}
	return result;
    }
}
