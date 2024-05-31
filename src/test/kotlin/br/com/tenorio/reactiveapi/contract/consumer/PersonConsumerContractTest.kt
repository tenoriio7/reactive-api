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


@ExtendWith(PactConsumerTestExt::class)
class PersonConsumerContractTest : PersonPacts(){

    @Test
    @PactTestFor(providerName = "createPerson")
    fun testPerson(mockServer: MockServer) {
        val person = Person(id = 2, name = "vini", age = 32)
        val webClient = WebClient.create(mockServer.getUrl())
        val response: Mono<String> = webClient.post()
            .uri("/person")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Define o Content-Type como application/json
            .bodyValue("""{"name": "vini","age": 32}""")
            .retrieve()
            .bodyToMono(String::class.java)

        val expectedResponse = """{"id": 1, "name": "vini", "age": 32}"""
        assertEquals(expectedResponse, response.block())
    }
}
