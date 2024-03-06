package br.com.tenorio.reactiveapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("br.com.tenorio.reactiveapi.models")
class ReactiveApiApplication

fun main(args: Array<String>) {
	runApplication<ReactiveApiApplication>(*args)
}