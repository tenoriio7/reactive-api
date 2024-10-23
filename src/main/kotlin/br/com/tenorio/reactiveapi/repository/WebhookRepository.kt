package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Webhook
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface WebhookRepository : R2dbcRepository<Webhook, Long> {

    @Query("DELETE FROM webhook WHERE created_at < :thresholdTime")
    fun deleteByCreatedAtBefore(thresholdTime: LocalDateTime): Mono<Void>
}