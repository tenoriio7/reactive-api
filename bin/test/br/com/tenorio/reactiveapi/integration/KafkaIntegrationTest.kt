package br.com.tenorio.reactiveapi.integration

import br.com.tenorio.reactiveapi.ReactiveApiApplication
import br.com.tenorio.reactiveapi.controller.PersonController
import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.repository.PersonRepository
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono


@EnableKafka
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [ReactiveApiApplication::class])
@EmbeddedKafka(
    partitions = 4,
    controlledShutdown = false,
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092"
    ], topics = ["testTopic"]
)
class KafkaIntegrationTest {


    private val TEST_TOPIC = "testTopic"

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var personRepository: PersonRepository

    @Autowired
    var embeddedKafkaBroker: EmbeddedKafkaBroker? = null


    @Test
    fun testReceivingKafkaEvents() {
        val producer: Producer<Int, String> = configureProducer()

        producer.send(ProducerRecord<Int, String>(TEST_TOPIC, 123, "my-test-value"))

        producer.close()
    }

    @Test
    fun testCreatePerson() {
        val person = Person(
            name = "vinicin",
            age = 30,
            id = 1
        )
        println("aquiiii ${embeddedKafkaBroker?.brokersAsString}")

        `when`(personRepository.save(any(Person::class.java))).thenReturn(Mono.just(person))

        webTestClient.post()
            .uri("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(person)
            .exchange()
    }


    private fun configureProducer(): Producer<Int, String> {
        val producerProps: Map<String, Any> = HashMap(KafkaTestUtils.producerProps(embeddedKafkaBroker))
        return DefaultKafkaProducerFactory<Int, String>(producerProps).createProducer()
    }
}