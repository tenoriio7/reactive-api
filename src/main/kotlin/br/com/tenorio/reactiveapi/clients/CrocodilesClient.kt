package br.com.tenorio.reactiveapi.clients

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class CrocodilesClient {
    private val webClient: WebClient = WebClient.create("https://test-api.k6.io")

    fun getCrocodileById(id: Long): Mono<String> {
        return webClient.get()
                .uri("/public/crocodiles/{id}/", id)
                .retrieve()
                .bodyToMono(String::class.java)
    }
}