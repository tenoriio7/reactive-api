package br.com.tenorio.reactiveapi.contract.pacts

import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.V4Pact
import au.com.dius.pact.core.model.annotations.Pact
import java.util.*


open class PersonPacts {
    @Pact(provider = "reactive_api", consumer = "other_api")
    fun createPerson(builder: PactDslWithProvider): V4Pact {
        return builder
            .given("need create a person")
            .uponReceiving("Retrieve create")
            .path("/person") // Especifique um valor fixo para o ID, qualquer valor servirá para este contrato
            .method("POST")
            .body("""{"name": "vini","age": 32}""")
            .willRespondWith()
            .status(200)
            .body("""{"id": 1, "name": "vini", "age": 32}""".trimIndent())
            .toPact(V4Pact::class.java)
    }
}