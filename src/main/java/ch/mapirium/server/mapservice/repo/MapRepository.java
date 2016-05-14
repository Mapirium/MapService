package ch.mapirium.server.mapservice.repo;

import ch.mapirium.server.mapservice.model.MapEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by surech on 08.05.16.
 */
public interface MapRepository extends CrudRepository<MapEntity, Long> {

    @Query("SELECT m FROM MapEntity m WHERE m.publicId = :publicId")
    public MapEntity findByPublicId(@Param("publicId") String publicId);

    @Query("SELECT m FROM MapEntity m WHERE m.name = :name")
    public MapEntity findByName(@Param("name") String name);
}
