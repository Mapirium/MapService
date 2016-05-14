package ch.mapirium.server.mapservice.model;

import ch.mapirium.server.common.jpa.model.CreateTimeTrackEntity;
import ch.mapirium.server.common.jpa.model.PublicIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Repräsentiert die Karte als oberstes Element in Mapirium. Hier können
 * anschliessend POIs gesetzt werden.
 */
@Entity
@Table(name = "map")
public class MapEntity extends PublicIdEntity {

    @Column(name = "name", unique = true, nullable = false)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "title", unique = false, nullable = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
