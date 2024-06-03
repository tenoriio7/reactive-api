import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import br.com.tenorio.reactiveapi.contract.pacts.PersonPacts
import br.com.tenorio.reactiveapi.models.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.kotlin.test.test


@ExtendWith(PactConsumerTestExt::class)
class PersonConsumerContractTest : PersonPacts(){

    @Test
    @PactTestFor(providerName = "reactive_api")
    fun testPerson(mockServer: MockServer) {
        val webClient = WebClient.create(mockServer.getUrl())
        val expectedResponse = """{"id": 1, "name": "vini", "age": 32}"""
         webClient.post()
            .uri("/person")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Define o Content-Type como application/json
            .bodyValue("""{"name": "vini","age": 32}""")
            .retrieve()
            .bodyToMono(String::class.java)
            .test()
            .assertNext{
                assertEquals(expectedResponse, it)
            }.verifyComplete()
    }
}
