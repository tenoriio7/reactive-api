package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Person
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PersonRepository : ReactiveMongoRepository <Person, String> {
}