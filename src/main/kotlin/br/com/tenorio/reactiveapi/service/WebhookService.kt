package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Webhook
import br.com.tenorio.reactiveapi.repository.WebhookRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration


@Service
class WebhookService(
    private val webhookRepository: WebhookRepository,
) {

    fun cleanExpiredWebhooks() {
        webhookRepository.deleteAll().block()
    }

    fun findAll(): Flux<Webhook> {
        return webhookRepository.findAll()
    }

    @Scheduled(fixedRate = 100000) // Executa a cada 10 segundos
    fun delete(): Mono<Void> {
        return  webhookRepository.deleteAll()
    }

    fun findById(id: Long): Mono<Webhook> {
        return webhookRepository.findById(id)
    }

    fun save(webhook: Webhook): Mono<Webhook> {
        val savedWebhook = webhookRepository.save(webhook)
        return savedWebhook
    }

    fun deleteById(id: Long): Mono<Void> {
        return webhookRepository.deleteById(id)
    }
}