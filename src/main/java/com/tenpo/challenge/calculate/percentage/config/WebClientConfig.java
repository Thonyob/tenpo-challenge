package com.tenpo.challenge.calculate.percentage.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .exchangeFunction(mockExchangeFunction())
                .build();
    }

    private ExchangeFunction mockExchangeFunction() {
        return clientRequest -> {

            ClientResponse mockResponse = ClientResponse.create(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body("{\"percentage\": 10.0}")
                    .build();

            return Mono.just(mockResponse);
        };
    }


}
