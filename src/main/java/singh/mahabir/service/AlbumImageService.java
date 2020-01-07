/**
 *
 */
package singh.mahabir.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import singh.mahabir.controller.model.ImageResponse;
import singh.mahabir.controller.model.ImageResponses;
import singh.mahabir.exception.AlbumNotFoundexception;
import singh.mahabir.exception.ImageNotFoundException;
import singh.mahabir.exception.ImageProcessingexception;
import singh.mahabir.publisher.NotificationPublisher;
import singh.mahabir.repository.AlbumEntity;
import singh.mahabir.repository.AlbumRepository;
import singh.mahabir.repository.ImageEntity;
import singh.mahabir.repository.ImageRepository;

/**
 * 
 * @author Mahabir Singh
 *
 */
@Service
@Slf4j
public class AlbumImageService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ImageRepository imaageRepository;

    @Autowired
    private NotificationPublisher publisher;

    public String createAlbum(String name) {
	AlbumEntity entity = new AlbumEntity();
	entity.setId(name);
	return albumRepository.save(entity).getId();
    }

    public boolean deleteAlbum(String name) {
	log.info("Album delete request at service level");
	return albumRepository.findById(name).map(album -> {
	    albumRepository.delete(album);
	    log.info("Album: {} delete successfully", name);
	    return true;
	}).orElseThrow(() -> new AlbumNotFoundexception("Album Name " + name + " not found"));
    }

    public List<String> getAll() {
	List<String> result = new ArrayList<>();
	List<AlbumEntity> entitys = albumRepository.findAll();
	entitys.forEach(entity -> result.add(entity.getId()));
	return result;

    }

    public Long createImageInAlbum(String albumName, MultipartFile multipartFile) {
	AlbumEntity album = albumRepository.findById(albumName).orElse(new AlbumEntity(albumName));

	ImageEntity image = createimage(multipartFile);
	image.setAlbum(album);
	return imaageRepository.save(image).getId();
    }

    private ImageEntity createimage(MultipartFile multipartFile) {
	final ImageEntity image = new ImageEntity();
	image.setImageName(multipartFile.getOriginalFilename());
	byte[] photoData;
	try {
	    photoData = new byte[multipartFile.getInputStream().available()];
	    multipartFile.getInputStream().read(photoData);
	} catch (IOException e) {
	    throw new ImageProcessingexception(e);
	}
	image.setBinaryData(photoData);
	return image;
    }

    public Long deleteImageInAlbum(String albumName, Long imageId) {
	ImageEntity entiry = imaageRepository.findByIdAndAlbumId(imageId, albumName)
		.orElseThrow(() -> new ImageNotFoundException("Image Id " + imageId + " not found"));
	ImageEntity remove = new ImageEntity();
	remove.setId(entiry.getId());
	imaageRepository.delete(remove);
	return entiry.getId();
    }

    public boolean publishCreateNotification(String message) {
	return !publisher.publishImageNotification(message, "create").isEmpty();
    }

    public boolean publishDeleteNotification(String message) {
	return !publisher.publishImageNotification(message, "delete").isEmpty();
    }

    public byte[] getImageFromAlbum(String albumName, Long imageId) {
	return imaageRepository.findByIdAndAlbumId(imageId, albumName).map(image -> {
	    return image.getBinaryData();
	}).orElseThrow(() -> new ImageNotFoundException("Image Id " + imageId + " not found"));

    }

    public ImageResponses getAllImageFromAlbum(String albumName) {
	ImageResponses response = new ImageResponses();
	List<ImageEntity> imaages = imaageRepository.findByAlbumId(albumName);
	imaages.forEach(image -> {
	    ImageResponse r = new ImageResponse();
	    r.setCreationDate(image.getCreationDate());
	    r.setImageId(image.getId());
	    r.setImageName(image.getImageName());
	    r.setImage(image.getBinaryData());
	    r.setAlbumName(image.getAlbum().getId());
	    response.getImages().add(r);
	});
	return response;
    }
}
