package ch.mapirium.server.mapservice.rest.controller;

import ch.mapirium.server.mapservice.model.MapEntity;
import ch.mapirium.server.mapservice.repo.MapRepository;
import ch.mapirium.server.mapservice.rest.model.MapListMapper;
import ch.mapirium.server.mapservice.rest.model.MapListResource;
import ch.mapirium.server.mapservice.rest.model.MapMapper;
import ch.mapirium.server.mapservice.rest.model.MapResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test für die Klasse {@link MapRestController}
 */
@RunWith(MockitoJUnitRunner.class)
public class MapRestControllerTest {

    @Mock
    private MapRepository mapRepository;

    @Mock
    private MapListMapper mapListMapper;

    @InjectMocks
    private MapRestController sut;

    @Test
    public void getAll() throws Exception {
        // Testdaten erstellen
        MapEntity e1 = mock(MapEntity.class);
        MapEntity e2 = mock(MapEntity.class);
        MapEntity e3 = mock(MapEntity.class);
        List<MapEntity> entities = Arrays.asList(e1, e2, e3);

        MapListResource listResource = mock(MapListResource.class);

        // Mock aufsetzen
        when(mapRepository.findAll()).thenReturn(entities);
        when(mapListMapper.fromEntity(entities)).thenReturn(listResource);

        // Test ausführen
        MapListResource result = sut.getAll();

        // Überprüfen
        Assert.assertEquals(listResource, result);
    }
}