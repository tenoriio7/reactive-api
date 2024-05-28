import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import br.com.tenorio.reactiveapi.clients.CrocodilesClient
import br.com.tenorio.reactiveapi.contract.pacts.CrocodilePacts
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.web.reactive.function.client.WebClient
import java.io.IOException


@ExtendWith(PactConsumerTestExt::class)
class CrocodilePactsConsumerPactTest : CrocodilePacts(){
    @Test
    @PactTestFor(providerName = "getCrocodile1")
    fun test(mockServer: MockServer) {

        val crocodileClient = CrocodilesClient(mockServer.getUrl())
        val statusCode = 0
        val response = crocodileClient.getCrocodileById(1).block()
        assertEquals(response,"""{"id": 1,"name": "Bert","sex": "M","date_of_birth": "2010-06-27","age": 13}""")
        }



    @Test
    @PactTestFor(providerName = "getCrocodile2")
    fun tests(mockServer: MockServer) {

        val crocodileClient = CrocodilesClient(mockServer.getUrl())
        val statusCode = 0
        val response = crocodileClient.getCrocodileById(1).block()
        assertEquals(response,"""{"id": 1,"name": "Bert","sex": "M","date_of_birth": "2010-06-27","age": 13}""")
    }


}
