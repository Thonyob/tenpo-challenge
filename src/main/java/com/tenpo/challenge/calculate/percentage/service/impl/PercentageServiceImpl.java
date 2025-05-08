package com.tenpo.challenge.calculate.percentage.service.impl;

import com.tenpo.challenge.calculate.percentage.event.ErrorEvent;
import com.tenpo.challenge.calculate.percentage.exception.ExternalServiceException;
import com.tenpo.challenge.calculate.percentage.service.PercentageService;
import com.tenpo.challenge.calculate.percentage.webclient.ExternalPercentageWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PercentageServiceImpl implements PercentageService {

    private final CacheManager cacheManager;
    private final ApplicationEventPublisher publisher;
    private final ExternalPercentageWebClient externalPercentageClient;

    public PercentageServiceImpl(CacheManager cacheManager,
                                 ApplicationEventPublisher publisher,
                                 ExternalPercentageWebClient externalPercentageClient) {
        this.cacheManager = cacheManager;
        this.publisher = publisher;
        this.externalPercentageClient = externalPercentageClient;
    }

    @Override
    public double getPercetange() {
        try {
            double percentage = externalPercentageClient.getPercentage();
            cacheManager.getCache("percentageCache").put("lastPercentage", percentage);
            return percentage;
        } catch (Exception ex) {
            Cache.ValueWrapper cached = cacheManager.getCache("percentageCache").get("lastPercentage");
            if (cached == null || cached.get() == null || (Double) cached.get() == 0.0) {
                publisher.publishEvent(new ErrorEvent("Error el servicio fallo"));
                throw new ExternalServiceException("Fallo el servicio externo y no hay caché disponible.");
            }
            return (Double) cached.get();
        }
    }
}
