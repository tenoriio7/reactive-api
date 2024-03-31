package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Person
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : R2dbcRepository<Person, String> {
}