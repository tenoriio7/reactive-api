package br.com.tenorio.reactiveapi.service

import br.com.tenorio.reactiveapi.models.Person
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class KafkaConsumerServiceTest{

    @Test
    fun testConsume() {
        val kafkaConsumerService = KafkaConsumerService()
        kafkaConsumerService.personService = Mockito.mock(PersonService::class.java)

        val objectMapper = jacksonObjectMapper()
        val personJson = """{"id":1,"name":"John Doe"}"""
        val person = objectMapper.readValue(personJson, Person::class.java)

        kafkaConsumerService.consume(personJson)
        // Verificar se o método save da PersonService foi chamado
        Mockito.verify(kafkaConsumerService.personService, Mockito.times(1)).save(person)
    }

}