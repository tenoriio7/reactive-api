package br.com.tenorio.reactiveapi.contract.pacts

import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.core.model.V4Pact
import au.com.dius.pact.core.model.annotations.Pact


open class CrocodilePacts {
    @Pact(provider = "getCrocodile1", consumer = "reactive_api")
    fun getCrocodile1(builder: PactDslWithProvider): V4Pact {
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

    @Pact(provider = "getCrocodile2", consumer = "reactive_api")
    fun getCrocodile2(builder: PactDslWithProvider): V4Pact {
        return builder
            .given("threre is a crocodile with id 1")
            .uponReceiving("Retrieve crocodile 1")
            .path("/public/crocodiles/1") // Especifique um valor fixo para o ID, qualquer valor servirá para este contrato
            .method("GET")
            .willRespondWith()
            .status(200)
            .body("""{"id": 2,"name": "Bert","sex": "M","date_of_birth": "2010-06-27","age": 13}""".trimIndent())
            .toPact(V4Pact::class.java)
    }

}