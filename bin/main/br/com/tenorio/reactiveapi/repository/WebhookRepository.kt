package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Webhook
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface WebhookRepository : ReactiveMongoRepository <Webhook, String> {
}