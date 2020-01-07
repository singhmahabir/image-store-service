/**
 *
 */
package singh.mahabir.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mahabir Singh
 *
 */
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    /**
     * This will delete the {@link ImageEntity} by imageId
     * 
     * @param imageName imageName
     * @return List of deleted {@link ImageEntity}
     */
    List<ImageEntity> deleteByImageName(String imageName);

    /**
     * Return {@link ImageEntity} if found by ImageId and AlbumName
     * 
     * @param imageId   imageId
     * @param albumName albumName
     * @return Optional {@link ImageEntity} if deleted
     */
    Optional<ImageEntity> findByIdAndAlbumId(Long imageId, String albumName);

    /**
     * Return List of {@link ImageEntity} if found by AlbumId or Album Name
     * 
     * @param albumName albumName
     * @return List of deleted {@link ImageEntity}
     */
    List<ImageEntity> findByAlbumId(String albumName);

}
