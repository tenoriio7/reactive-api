package br.com.tenorio.reactiveapi.models
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("WEBHOOK")
data class Webhook(
        @Id
        val id: Long? = null,
        val notify: String
)