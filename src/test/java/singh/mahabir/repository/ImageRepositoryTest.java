/**
 * All rights reserved.
 */

package singh.mahabir.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author Mahabir Singh
 *
 */

//@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class ImageRepositoryTest {

    @BeforeAll
    public static void inti() {
	MockitoAnnotations.initMocks(ImageRepositoryTest.class);
    }

    private ImageRepository rep = Mockito.mock(ImageRepository.class);

//    @Mock
//    private ImageRepository rep;

    @Test
    public void testCount() {

	Mockito.when(rep.count()).thenReturn(0l);
	assertThat(rep.count()).isEqualTo(0);
	ImageEntity entity = new ImageEntity();
	entity.setImageName("MyImage");
	AlbumEntity album = new AlbumEntity();
	album.setId("MyAlbum");
	entity.setAlbum(album);

	Mockito.when(rep.save(entity)).thenReturn(entity);
	ImageEntity save = rep.save(entity);

	assertThat(save).isEqualTo(entity);

//	Mockito.when(rep.findByAlbumId("MyAlbum)).thenReturn(entity);
//	List<ImageEntity> findByAlbumId = rep.findByAlbumId("MyAlbum");
//	long count = findByAlbumId.stream().filter(i -> i.getAlbum().getId().equals("MyAlbum")).count();
//	assertThat(count).isEqualTo(1);
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
