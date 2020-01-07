/**
 *
 */
package singh.mahabir.controller.model;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mahabir Singh
 *
 */
@Setter
@Getter
@ApiModel("ImageResponse")
public class ImageResponse {

    @ApiModelProperty(notes = "Binary of Image")
    byte[] image;

    @ApiModelProperty(notes = "Image Id")
    private Long imageId;

    @ApiModelProperty(notes = "Image Name")
    private String imageName;

    @ApiModelProperty(notes = "Image Created date")
    private Date creationDate;

    @ApiModelProperty(notes = "Image Album Name")
    private String albumName;

}
