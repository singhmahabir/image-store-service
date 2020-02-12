/**
 * All rights reserved.
 */

package singh.mahabir.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mahabir Singh
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
//@ExtendWith(SpringExtension.class)
public class ImageRepositoryMockTest {

    @Autowired
    private ImageRepository rep;

    @Test
    public void testCount() {
	assertThat(rep.count()).isEqualTo(0);
	ImageEntity entity = new ImageEntity();
	entity.setImageName("MyImage");
	AlbumEntity album = new AlbumEntity();
	album.setId("MyAlbum");
	entity.setAlbum(album);

	ImageEntity save = rep.save(entity);

	assertThat(save).isEqualTo(entity);

	List<ImageEntity> findByAlbumId = rep.findByAlbumId("MyAlbum");
	long count = findByAlbumId.stream().filter(i -> i.getAlbum().getId().equals("MyAlbum")).count();
	assertThat(count).isEqualTo(1);
    }

    @Test
    @Sql("/createAlbumUser.sql")
    public void sql_Test() {
	assertThat(rep.count()).isEqualTo(2);

	Optional<ImageEntity> findById = rep.findById(1l);
	assertThat("msdeo").isEqualTo(findById.get().getImageName());
	assertThat("1").isEqualTo(findById.get().getAlbum().getId());

    }
}
