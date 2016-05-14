package ch.mapirium.server.mapservice.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Definiert die REST-Resource für die Karte
 */
public class MapResource extends ResourceSupport {

    private String publicId;

    private Date createdAt;

    private String name;

    private String title;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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
