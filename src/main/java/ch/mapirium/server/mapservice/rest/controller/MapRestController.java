package ch.mapirium.server.mapservice.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.mapservice.model.MapEntity;
import ch.mapirium.server.mapservice.repo.MapRepository;
import ch.mapirium.server.mapservice.rest.gateway.PublicIdGateway;
import ch.mapirium.server.mapservice.rest.model.MapMapper;
import ch.mapirium.server.mapservice.rest.model.MapResource;
import ch.mapirium.server.mapservice.rest.model.PublicIdResource;
import ch.mapirium.server.mapservice.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST-Schnittstelle für die Karte
 */
@RestController
@RequestMapping(path="/map")
public class MapRestController {

    @Autowired
    private PublicIdGateway publicIdGateway;

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private MapMapper mapMapper;

    @Autowired
    private MapService mapService;

    @RequestMapping(method = RequestMethod.POST)
    public MapResource createMap(@RequestBody MapResource newMap) {
        // Entität erstellen
        MapEntity entity = mapMapper.toEntity(newMap);

        // Und speichern
        MapEntity savedMap = mapService.createMap(entity);

        // Resource erstellen
        MapResource result = mapMapper.fromEntity(savedMap);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MapResource> getAll() {
        // Alle karten laden
        Iterable<MapEntity> all = mapRepository.findAll();

        // Mappen
        List<MapResource> result = new ArrayList<>();
        for (MapEntity mapEntity : all) {
            MapResource mapResource = mapMapper.fromEntity(mapEntity);
            result.add(mapResource);
        }

        return result;
    }

    @RequestMapping(path = "/id/{publicId}", method = RequestMethod.GET)
    public MapResource getMapByPublicId(@PathVariable("publicId") String publicId){
        // Karte suchen
        MapEntity entity = mapRepository.findByPublicId(publicId);

        // Wenn wir nichts gefunden haben, geben wir einen Fehler zurück
        if (entity == null) {
            throw new NotFoundException("Keine Karte mit der ID " + publicId + " gefunden");
        } else {
            MapResource result = mapMapper.fromEntity(entity);
            return result;
        }
    }

    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
    public MapResource getMapByName(@PathVariable("name") String name){
        // Karte suchen
        MapEntity entity = mapRepository.findByPublicId(name);

        // Wenn wir nichts gefunden haben, geben wir einen Fehler zurück
        if (entity == null) {
            throw new NotFoundException("Keine Karte mit dem Namen '" + name + "' gefunden");
        } else {
            MapResource result = mapMapper.fromEntity(entity);
            return result;
        }
    }

    @RequestMapping(path="/test", method = RequestMethod.GET)
    public PublicIdResource test(){
        // ID erstellen
        PublicIdResource newPublicId = publicIdGateway.createNewPublicId();
        return newPublicId;
    }
}
