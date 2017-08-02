package ch.mapirium.server.mapservice.rest.model;

import ch.mapirium.server.mapservice.model.MapEntity;
import ch.mapirium.server.mapservice.rest.controller.MapRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mappt eine Liste von Karten auf eine REST-Ressource
 */
@Component
public class MapListMapper {

    @Autowired
    private MapMapper mapMapper;

    public MapListResource fromEntity(Iterable<MapEntity> entities) {
        MapListResource result = new MapListResource();
        result.add(linkTo(methodOn(MapRestController.class).getAll()).withSelfRel());

        // Die einzelnen Entitäten mappen
        List<MapResource> mapResources = StreamSupport.stream(entities.spliterator(), false).map(mapMapper::fromEntity).collect(Collectors.toList());
        result.embed("maps", mapResources);

        return result;
    }
}
