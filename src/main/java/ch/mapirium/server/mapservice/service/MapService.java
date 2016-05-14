package ch.mapirium.server.mapservice.service;

import ch.mapirium.server.mapservice.model.MapEntity;
import ch.mapirium.server.mapservice.repo.MapRepository;
import ch.mapirium.server.mapservice.rest.gateway.PublicIdGateway;
import ch.mapirium.server.mapservice.rest.model.MapResource;
import ch.mapirium.server.mapservice.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Verwaltet die Karten-Daten
 */
@Component
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private PublicIdGateway publicIdGateway;

    public MapEntity createMap(MapEntity newMap) {
        // Öffentliche ID lösen
        PublicIdResource newPublicId = publicIdGateway.createNewPublicId();
        newMap.setPublicId(newPublicId.getPublicId());

        // Karte speichern
        MapEntity savedMap = mapRepository.save(newMap);

        return savedMap;
    }

}
