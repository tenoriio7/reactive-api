package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Crododile
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CrocodileRepository : ReactiveMongoRepository <Crododile, String> {
}