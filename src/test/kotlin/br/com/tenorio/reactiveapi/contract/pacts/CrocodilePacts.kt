package br.com.tenorio.reactiveapi.contract.pacts

import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.V4Pact
import au.com.dius.pact.core.model.annotations.Pact
import java.util.*


open class CrocodilePacts {
    @Pact(provider = "crododile_api", consumer = "reactive_api")
    fun getCrocodile(builder: PactDslWithProvider): V4Pact {
        return builder
            .given("threre is a crocodile with id 1")
            .uponReceiving("Retrieve crocodile 1")
            .path("/public/crocodiles/1") // Especifique um valor fixo para o ID, qualquer valor servirá para este contrato
            .method("GET")
            .willRespondWith()
            .status(200)
            .body("""{"id": 1,"name": "Bert","sex": "M","date_of_birth": "2010-06-27","age": 13}""".trimIndent())
            .toPact(V4Pact::class.java)
    }
}