package br.com.tenorio.reactiveapi.controller

import br.com.tenorio.reactiveapi.models.Webhook
import br.com.tenorio.reactiveapi.service.WebhookService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/webhook")
class WebhookController(
    private val webhookService: WebhookService,
    private val messagingTemplate: SimpMessageSendingOperations
) {


    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @GetMapping
    fun getAll(): Flux<Webhook> {
        return webhookService.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Mono<Webhook> {
        return webhookService.findById(id)
    }


    @PostMapping
    fun create(@RequestBody webhook: Webhook): Mono<Webhook> {
        messagingTemplate.convertAndSend("/topic/webhook", "Nova mensagem do webhook")
        return webhookService.save(webhook)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): Mono<Void> {
        return webhookService.deleteById(id)
    }
}