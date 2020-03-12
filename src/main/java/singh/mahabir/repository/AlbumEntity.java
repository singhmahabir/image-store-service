/**
 *
 */
package singh.mahabir.repository;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mahabir Singh
 *
 */

@Entity
@Table(name = "album")
@Setter
@Getter
@NoArgsConstructor
public class AlbumEntity {

    public AlbumEntity(String albumName) {
	id = albumName;
    }

    @Id
    private String id;

    private Date creationDate = Date.valueOf(LocalDate.now());

    private Date lastUpdateDate = Date.valueOf(LocalDate.now());

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<ImageEntity> images;
}
