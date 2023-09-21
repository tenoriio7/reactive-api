package br.com.tenorio.reactiveapi.controller

import br.com.tenorio.reactiveapi.clients.CrocodilesClient
import br.com.tenorio.reactiveapi.mapper.CrocodileMapper
import br.com.tenorio.reactiveapi.models.Crododile
import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.service.CrocodileService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/crocodile")
class CrocodileController(private val crocodileService: CrocodileService, private val crocodilesClient : CrocodilesClient) {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @GetMapping
    fun getAll(): Flux<Crododile> {
        return crocodileService.findAll()
    }

    @GetMapping("/{id}", produces = ["application/json"])
    fun getByCrocodileById(@PathVariable id: String): Mono<String> {
        val response =  crocodilesClient.getCrocodileById(id.toLong())
        response.subscribe{
            crododile ->
            crocodileService.save(CrocodileMapper().convertToCrododile(crododile))
        }
        return  response
    }
}