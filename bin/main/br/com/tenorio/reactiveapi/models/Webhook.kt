package br.com.tenorio.reactiveapi.models
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "webhooks")
data class Webhook(
        @Id
        val id: String?,
        val notify: String,
)