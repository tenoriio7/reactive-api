package br.com.tenorio.reactiveapi

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestReactiveApiApplication

fun main(args: Array<String>) {
	fromApplication<ReactiveApiApplication>().with(TestReactiveApiApplication::class).run(*args)
}
