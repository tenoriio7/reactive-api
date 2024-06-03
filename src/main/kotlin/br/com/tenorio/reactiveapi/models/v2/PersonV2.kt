package br.com.tenorio.reactiveapi.models.v2
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class PersonV2(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        val nome: String,
        val idade: Int,
        val sexo:String
)