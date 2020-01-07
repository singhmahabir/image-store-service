/**
 *
 */
package singh.mahabir.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import singh.mahabir.controller.model.ImageResponses;

/**
 * @author Mahabir Singh
 *
 */
@Api(value = "AlbumImage", description = "AlbumImage API's")
@RequestMapping("/album")
public interface AlbumImageProcessingController {

    @GetMapping()
    @ApiOperation(value = "View a list of Albums Available ", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public List<String> getImageAlbums();

    @PostMapping("/{albumName}")
    @ApiOperation(value = "Create Album", response = String.class, code = 201)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Created Album"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public String createImageAlbum(@PathVariable("albumName") String name);

    @DeleteMapping("/{albumName}")
    @ApiOperation(value = "Delete Album", response = Boolean.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 400, message = "Bad Request"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Boolean deleteImageAlbum(@PathVariable("albumName") String name);

    @PostMapping("/{albumName}/image")
    @ApiOperation(value = "Create Image In Album", response = Long.class, code = 201)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Created Album"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Long createImageInAlbum(@PathVariable("albumName") String albumName,
	    @RequestPart("image") MultipartFile multipartFile);

    @DeleteMapping("/{albumName}/image/{imageId}")
    @ApiOperation(value = "Delete Image by Image-id In Album", response = Long.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 400, message = "Bad Request"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Long deleteImageInAlbum(@PathVariable("albumName") String albumName, @PathVariable("imageId") Long imageId);

    @GetMapping(value = "/{albumName}/image/{imageId}", produces = { MediaType.IMAGE_JPEG_VALUE,
	    MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Retrieve Image by Image Id From Album", response = Byte[].class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 400, message = "Bad Request"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"), })
    public byte[] getImageFromAlbum(@PathVariable("albumName") String albumName, @PathVariable("imageId") Long imageId);

    @GetMapping(value = "/{albumName}/image", produces = { MediaType.APPLICATION_JSON_VALUE, })
    @ApiOperation(value = "Retrieve All Image From Album", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public ResponseEntity<ImageResponses> getAllImageFromAlbum(@PathVariable("albumName") String albumName);
}
