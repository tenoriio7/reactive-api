package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Person
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService {

    @Autowired
    lateinit var personService:PersonService

    @KafkaListener(topics = ["person-async"], groupId = "reactive-api")
    fun consume(message: String) {
        println("Consumed message service: $message")
        val mapper: ObjectMapper = jacksonObjectMapper()
        val person: Person = mapper.readValue(message)
        person.id += 0L
        personService.save(person)
    }
}