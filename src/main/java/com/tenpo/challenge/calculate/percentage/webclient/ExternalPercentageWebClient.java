package com.tenpo.challenge.calculate.percentage.webclient;

import com.tenpo.challenge.calculate.percentage.webclient.dto.external.ExternalPercentageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ExternalPercentageWebClient {

    private final WebClient webClient;

    public Double getPercentage() {
        return webClient.get()
                .uri("http://mock-api/percentage")
                .retrieve()
                .bodyToMono(ExternalPercentageResponse.class)
                .map(ExternalPercentageResponse::getPercentage)
                .block();
    }

}