package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Webhook
import br.com.tenorio.reactiveapi.repository.WebhookRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class WebhookService(
    private val webhookRepository: WebhookRepository,
) {

    fun findAll(): Flux<Webhook> {
        return webhookRepository.findAll()
    }

    fun findById(id: String): Mono<Webhook> {
        return webhookRepository.findById(id.toLong())
    }

    fun save(webhook: Webhook): Mono<Webhook> {
        val savedWebhook = webhookRepository.save(webhook)
        return savedWebhook
    }

    fun deleteById(id: String): Mono<Void> {
        return webhookRepository.deleteById(id.toLong())
    }
}