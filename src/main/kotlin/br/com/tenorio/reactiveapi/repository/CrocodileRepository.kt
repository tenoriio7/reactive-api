package br.com.tenorio.reactiveapi.repository

import br.com.tenorio.reactiveapi.models.Crododile
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface CrocodileRepository : R2dbcRepository<Crododile, String> {
}