package br.com.tenorio.reactiveapi.controller

import br.com.tenorio.reactiveapi.handler.WebsocketHandler
import br.com.tenorio.reactiveapi.models.Webhook
import br.com.tenorio.reactiveapi.service.WebhookService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import org.springframework.web.socket.TextMessage
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
@RequestMapping("/webhook")
class WebhookController(private val webhookService: WebhookService) {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var websocketHandler: WebsocketHandler

    @GetMapping
    fun getAll(): Flux<Webhook> {
        return webhookService.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Mono<Webhook> {
        return webhookService.findById(id)
    }


//    @PostMapping
//    fun create(@RequestBody webhook: Webhook): Mono<Webhook> {
//        websocketHandler.sendMessageToAll(TextMessage(webhook.notify))
//        return webhookService.save(webhook)
//    }

    @PostMapping
    fun create(@RequestBody payload: Map<String, Any>): Mono<Webhook> {
        val jsonPayload = ObjectMapper().writeValueAsString(payload)
        websocketHandler.sendMessageToAll(TextMessage(jsonPayload))
       return Mono.just(Webhook(notify = jsonPayload))

    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): Mono<Void> {
        return webhookService.deleteById(id)
    }
}