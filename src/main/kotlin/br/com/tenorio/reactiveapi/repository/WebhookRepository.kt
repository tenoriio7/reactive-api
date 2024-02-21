package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Webhook
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface WebhookRepository : R2dbcRepository<Webhook, Long> {
}