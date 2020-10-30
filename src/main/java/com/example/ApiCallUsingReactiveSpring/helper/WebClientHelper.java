package com.example.ApiCallUsingReactiveSpring.helper;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author rishi
 */
@Component
public class WebClientHelper {

  private WebClient webClient;

  public WebClientHelper() {
    webClient = WebClient.create();
  }

  public <T> Mono<T> performGetToMono(URI uri, MultiValueMap<String, String> params, Class<? extends T> clazzResponse){
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .scheme(uri.getScheme())
            .host(uri.getHost())
            .port(uri.getPort())
            .path(uri.getPath())
            .queryParams(params)
            .build()
        )
        .exchange()
        .flatMap(clientResponse -> clientResponse.bodyToMono(clazzResponse));
  }

  public <T> Flux<T> performGetToFlux(URI uri, MultiValueMap<String, String> params, Class<? extends T> clazzResponse){
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .scheme(uri.getScheme())
            .host(uri.getHost())
            .port(uri.getPort())
            .path(uri.getPath())
            .queryParams(params)
            .build()
        )
        .exchange()
        .flatMapMany(clientResponse -> clientResponse.bodyToFlux(clazzResponse));
  }

  public <T> Mono<T> performPostToMono(URI uri, Object requestBody, Class<? extends T> clazzResponse){
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .scheme(uri.getScheme())
            .host(uri.getHost())
            .port(uri.getPort())
            .path(uri.getPath())
            .build()
        )
        .body(BodyInserters.fromValue(requestBody))
        .exchange()
        .flatMap(clientResponse -> clientResponse.bodyToMono(clazzResponse));
  }

  public <T> Mono<T> performPutToMono(URI uri, Object requestBody, Class<? extends T> clazzResponse){
    return webClient.put()
        .uri(uriBuilder -> uriBuilder
            .scheme(uri.getScheme())
            .host(uri.getHost())
            .port(uri.getPort())
            .path(uri.getPath())
            .build()
        )
        .body(BodyInserters.fromValue(requestBody))
        .exchange()
        .flatMap(clientResponse -> clientResponse.bodyToMono(clazzResponse));
  }

  public <T> Mono<T> performDeleteToMono(URI uri, MultiValueMap<String, String> params, Class<? extends T> clazzResponse){
    return webClient.delete()
        .uri(uriBuilder -> uriBuilder
            .scheme(uri.getScheme())
            .host(uri.getHost())
            .port(uri.getPort())
            .path(uri.getPath())
            .queryParams(params)
            .build()
        )
        .exchange()
        .flatMap(clientResponse -> clientResponse.bodyToMono(clazzResponse));
  }
}
