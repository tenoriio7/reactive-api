package br.com.tenorio.reactiveapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ReactiveApiApplication

fun main(args: Array<String>) {
	runApplication<ReactiveApiApplication>(*args)
}