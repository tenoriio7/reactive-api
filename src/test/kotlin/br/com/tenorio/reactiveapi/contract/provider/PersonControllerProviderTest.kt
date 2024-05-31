import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import au.com.dius.pact.provider.junitsupport.target.Target
import au.com.dius.pact.provider.junitsupport.target.TestTarget
import br.com.tenorio.reactiveapi.ReactiveApiApplication
import br.com.tenorio.reactiveapi.contract.pacts.CrocodilePacts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient
import java.util.*


@Provider("reactive_api")
@PactFolder("target/pacts")
@SpringBootTest(classes = [ReactiveApiApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension::class)
class PersonControllerProviderTest : CrocodilePacts(){
    @LocalServerPort
    var port: Int = 0

    private lateinit var webClient: WebClient

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.target= HttpTestTarget("localhost", port)
        context.verifyInteraction()
    }

    @State("threre create person")
    fun cleanUpCreatePersonState() {
    }

    @State("need create a person")
    fun cleanUpCreatePersonState2() {
    }

}