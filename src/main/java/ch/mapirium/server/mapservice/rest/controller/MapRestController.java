package ch.mapirium.server.mapservice.rest.controller;

import ch.mapirium.server.mapservice.rest.gateway.PublicIdGateway;
import ch.mapirium.server.mapservice.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Schnittstelle für die Karte
 */
@RestController
@RequestMapping(path="/map")
public class MapRestController {

    @Autowired
    private PublicIdGateway publicIdGateway;

    @RequestMapping(path="/test", method = RequestMethod.GET)
    public PublicIdResource test(){
        // ID erstellen
        PublicIdResource newPublicId = publicIdGateway.createNewPublicId();
        return newPublicId;
    }
}
