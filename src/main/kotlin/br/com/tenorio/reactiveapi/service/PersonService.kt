package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.repository.PersonRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

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
        val savedPerson = personRepository.save(person)
        savedPerson.subscribe { person ->
            // Faça algo com o objeto Person
            kafkaTemplate.send("person", person)
        }

        return savedPerson
    }

    fun deleteById(id: String): Mono<Void> {
        return personRepository.deleteById(id)
    }
}