package br.com.tenorio.reactiveapi.models
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "webhook")
data class Webhook(
        @Id
        val id: String?,
        val notify: String,
)