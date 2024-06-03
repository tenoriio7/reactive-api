import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import br.com.tenorio.reactiveapi.clients.CrocodilesClient
import br.com.tenorio.reactiveapi.contract.pacts.CrocodilePacts
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(PactConsumerTestExt::class)
class CrocodileConsumerContractTest : CrocodilePacts(){

    @Test
    @PactTestFor(providerName = "crododile_api")
    fun test(mockServer: MockServer) {
        val crocodileClient = CrocodilesClient(mockServer.getUrl())
        val response = crocodileClient.getCrocodileById(1).block()
        assertEquals(response,"""{"id": 1,"name": "Bert","sex": "M","date_of_birth": "2010-06-27","age": 13}""")
        }
}