/**
 *
 */
package singh.mahabir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import singh.mahabir.controller.model.ImageResponses;
import singh.mahabir.service.AlbumImageService;

/**
 * @author Mahabir Singh
 *
 */

@RestController
@Slf4j
public class AlbumImageProcessingControllerImpl implements AlbumImageProcessingController {

    @Autowired
    private AlbumImageService service;

    @Override
    public List<String> getImageAlbums() {
	log.info("All Album retrieve request come");
	return service.getAll();
    }

    @Override
    public String createImageAlbum(@PathVariable("albumName") String name) {
	log.info("Album create request come with Album name: {}", name);
	return service.createAlbum(name);
    }

    @Override
    public Boolean deleteImageAlbum(@PathVariable("albumName") String name) {
	log.info("Album delete request come for Album name: {}", name);
	return service.deleteAlbum(name);
    }

    @Override
    public Long createImageInAlbum(@PathVariable("albumName") String albumName,
	    @RequestPart("image") MultipartFile multipartFile) {
	log.info("Image create request come with name {} under Album {}", multipartFile.getOriginalFilename(),
		albumName);
	Long id = service.createImageInAlbum(albumName, multipartFile);
	service.publishCreateNotification("Image with name " + multipartFile.getOriginalFilename() + " created");
	return id;
    }

    @Override
    public Long deleteImageInAlbum(@PathVariable("albumName") String albumName, @PathVariable("imageId") Long imageId) {
	log.info("Image delete request come with Image-Id {} under Album {}", imageId, albumName);
	Long id = service.deleteImageInAlbum(albumName, imageId);
	service.publishDeleteNotification("Image with id " + id + " deleted");
	return id;
    }

    @Override
    public byte[] getImageFromAlbum(@PathVariable("albumName") String albumName,
	    @PathVariable("imageId") Long imageId) {
	log.info("Image delete request come with Image-Id {} under Album {}", imageId, albumName);
	return service.getImageFromAlbum(albumName, imageId);
    }

    @Override
    public ResponseEntity<ImageResponses> getAllImageFromAlbum(@PathVariable("albumName") String albumName) {
	log.info("Retrieve All Image from Album {}", albumName);
	ImageResponses images = service.getAllImageFromAlbum(albumName);
	return ResponseEntity.ok(images);
    }
}
