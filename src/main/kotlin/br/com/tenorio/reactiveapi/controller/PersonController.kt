package br.com.tenorio.reactiveapi.controller

import br.com.tenorio.reactiveapi.clients.CrocodilesClient
import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.service.PersonService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService, private val crocodilesClient : CrocodilesClient) {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @GetMapping
    fun getAll(): Flux<Person> {
        return personService.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Mono<Person> {
        return personService.findById(id)
    }


    @GetMapping("/crocodiles/{id}", produces = ["application/json"])
    fun getByCrocodileById(@PathVariable id: String): Mono<String> {
        val response =  crocodilesClient.getCrocodileById(id.toLong())
        return  response
    }

    @PostMapping
    fun create(@RequestBody person: Person): Mono<Person> {
        return personService.save(person)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): Mono<Void> {
        return personService.deleteById(id)
    }
}