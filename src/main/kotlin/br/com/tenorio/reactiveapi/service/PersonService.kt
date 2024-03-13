package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.repository.PersonRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.concurrent.TimeoutException

@Service
class PersonService(private val personRepository: PersonRepository,
                    private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun findAll(): Flux<Person> {
        return personRepository.findAll()
    }

    fun findById(id: String): Mono<Person> {
        return personRepository.findById(id)
    }

    fun save(person: Person): Mono<Person> {
        return personRepository.save(person)
            .timeout(Duration.ofSeconds(1))
            .doOnSuccess { kafkaTemplate.send("person", it) }
            .onErrorResume(TimeoutException::class.java) {
                println("Timeout ao salvar a pessoa")
                throw RuntimeException("Timeout ao salvar a pessoa")
            }
    }

    fun deleteById(id: String): Mono<Void> {
        return personRepository.deleteById(id)
    }
}