package ch.mapirium.server.mapservice.rest.controller;

import ch.mapirium.server.mapservice.model.MapEntity;
import ch.mapirium.server.mapservice.repo.MapRepository;
import ch.mapirium.server.mapservice.rest.model.MapMapper;
import ch.mapirium.server.mapservice.rest.model.MapResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
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
    private MapMapper mapMapper;

    @InjectMocks
    private MapRestController sut;

    @Test
    public void getAll() throws Exception {
        // Testdaten erstellen
        MapEntity e1 = mock(MapEntity.class);
        MapEntity e2 = mock(MapEntity.class);
        MapEntity e3 = mock(MapEntity.class);
        List<MapEntity> entities = Arrays.asList(e1, e2, e3);

        MapResource r1 = mock(MapResource.class);
        MapResource r2 = mock(MapResource.class);
        MapResource r3 = mock(MapResource.class);

        // Mock aufsetzen
        when(mapRepository.findAll()).thenReturn(entities);
        when(mapMapper.fromEntity(e1)).thenReturn(r1);
        when(mapMapper.fromEntity(e2)).thenReturn(r2);
        when(mapMapper.fromEntity(e3)).thenReturn(r3);

        // Test ausführen
        List<MapResource> result = sut.getAll();

        // Überprüfen
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(r1));
        Assert.assertTrue(result.contains(r2));
        Assert.assertTrue(result.contains(r3));
    }
}