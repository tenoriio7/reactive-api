package br.com.tenorio.reactiveapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveApiApplication

fun main(args: Array<String>) {
    runApplication<ReactiveApiApplication>(*args)
}