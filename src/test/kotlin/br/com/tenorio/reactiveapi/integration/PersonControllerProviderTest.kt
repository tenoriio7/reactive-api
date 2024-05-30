package br.com.tenorio.reactiveapi.integration

import au.com.dius.pact.provider.junit.PactRunner
import au.com.dius.pact.provider.junit.target.HttpTarget
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactUrl
import au.com.dius.pact.provider.junitsupport.target.Target
import au.com.dius.pact.provider.junitsupport.target.TestTarget
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(PactRunner::class)
@Provider("person")
@PactUrl(urls = ["file:src/test/resources/reactive_api-createPerson.json"])
class PersonControllerProviderTest {

    @TestTarget
    val target = HttpTarget("localhost",8080)

    @Test
    fun aaa(){

    }

}