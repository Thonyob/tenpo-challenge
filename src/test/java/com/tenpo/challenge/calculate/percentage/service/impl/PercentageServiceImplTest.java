package com.tenpo.challenge.calculate.percentage.service.impl;

import com.tenpo.challenge.calculate.percentage.event.ErrorEvent;
import com.tenpo.challenge.calculate.percentage.exception.ExternalServiceException;
import com.tenpo.challenge.calculate.percentage.webclient.ExternalPercentageWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PercentageServiceImplTest {

    private PercentageServiceImpl percentageService;
    private CacheManager cacheManager;
    private ApplicationEventPublisher publisher;
    private ExternalPercentageWebClient externalClient;
    private Cache cache;

    @BeforeEach
    void setUp() {
        externalClient = mock(ExternalPercentageWebClient.class);
        cacheManager = mock(CacheManager.class);
        publisher = mock(ApplicationEventPublisher.class);
        cache = mock(Cache.class);

        when(cacheManager.getCache("percentageCache")).thenReturn(cache);

        percentageService = new PercentageServiceImpl(cacheManager, publisher, externalClient);
    }

    @Test
    void testGetPercentage_success() {
        when(externalClient.getPercentage()).thenReturn(10.0);

        double result = percentageService.getPercetange();

        assertEquals(10.0, result);
        verify(cache).put("lastPercentage", 10.0);
        verifyNoInteractions(publisher);
    }

    @Test
    void testGetPercentage_usesCacheOnFailure() {
        when(externalClient.getPercentage()).thenThrow(new RuntimeException("External service failed"));
        Cache.ValueWrapper valueWrapper = mock(Cache.ValueWrapper.class);
        when(valueWrapper.get()).thenReturn(5.0);
        when(cache.get("lastPercentage")).thenReturn(valueWrapper);

        double result = percentageService.getPercetange();

        assertEquals(5.0, result);
        verify(publisher, never()).publishEvent(any());
    }

    @Test
    void testGetPercentage_noCacheAndFails() {
        when(externalClient.getPercentage()).thenThrow(new RuntimeException("External service failed"));
        when(cache.get("lastPercentage")).thenReturn(null);

        ExternalServiceException ex = assertThrows(ExternalServiceException.class, () ->
                percentageService.getPercetange());

        assertEquals("Fallo el servicio externo y no hay caché disponible.", ex.getMessage());

        ArgumentCaptor<ErrorEvent> eventCaptor = ArgumentCaptor.forClass(ErrorEvent.class);
        verify(publisher).publishEvent(eventCaptor.capture());
        assertEquals("Error el servicio fallo", eventCaptor.getValue().getResponse());
    }
}
