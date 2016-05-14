package ch.mapirium.server.mapservice.rest.model;

import ch.mapirium.server.mapservice.model.MapEntity;
import ch.mapirium.server.mapservice.rest.controller.MapRestController;
import ch.mapirium.server.mapservice.rest.model.MapResource;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität einer Karte
 */
@Component
public class MapMapper {

    public MapResource fromEntity(MapEntity entity) {
        MapResource result = new MapResource();
        result.setPublicId(entity.getPublicId());
        result.setName(entity.getName());
        result.setTitle(entity.getTitle());
        result.setCreatedAt(entity.getCreatedAt());

        result.add(linkTo(methodOn(MapRestController.class).getMapByPublicId(entity.getPublicId())).withSelfRel());

        return result;
    }

    public MapEntity toEntity(MapResource resource) {
        MapEntity result = new MapEntity();
        result.setPublicId(resource.getPublicId());
        result.setName(resource.getName());
        result.setTitle(resource.getTitle());

        return result;
    }
}
