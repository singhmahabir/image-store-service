/**
 *
 */
package singh.mahabir.controller.model;

import java.util.ArrayList;
import java.util.List;

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
@ApiModel("ImageResponses")
public class ImageResponses {

    @ApiModelProperty(notes = "List Of Image response")
    List<ImageResponse> images = new ArrayList<>();
}
