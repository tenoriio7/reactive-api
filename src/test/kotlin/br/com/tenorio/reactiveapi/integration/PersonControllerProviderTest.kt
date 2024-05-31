import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import br.com.tenorio.reactiveapi.ReactiveApiApplication
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.SpringApplication
import org.springframework.web.context.ConfigurableWebApplicationContext


@Provider("reactive_api")
@PactFolder("target/pacts")
class PersonControllerProviderTest {


    companion object {
        var application: ConfigurableWebApplicationContext? = null

        @JvmStatic
        @BeforeAll
        fun start(): Unit {
            application = SpringApplication.run(ReactiveApiApplication::class.java) as ConfigurableWebApplicationContext
        }
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }


    @State("threre create person")
    fun toPostState() {
    }

}