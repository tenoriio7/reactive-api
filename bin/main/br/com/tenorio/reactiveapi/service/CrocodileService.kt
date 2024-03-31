package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Crododile
import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.repository.CrocodileRepository
import br.com.tenorio.reactiveapi.repository.PersonRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CrocodileService(private val crododileRepository: CrocodileRepository,
                       private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun findAll(): Flux<Crododile> {
        return crododileRepository.findAll()
    }

    fun findById(id: String): Mono<Crododile> {
        return crododileRepository.findById(id)
    }

    fun save(crocodile: Crododile): Mono<Crododile> {
        val savedCrododile = crododileRepository.save(crocodile)
        savedCrododile.subscribe { crocodile ->
            // Faça algo com o objeto Person
            kafkaTemplate.send("crocodile", crocodile)
        }
        return savedCrododile
    }

    fun deleteById(id: String): Mono<Void> {
        return crododileRepository.deleteById(id)
    }
}