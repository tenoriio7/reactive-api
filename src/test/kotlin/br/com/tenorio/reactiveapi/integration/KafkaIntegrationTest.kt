package br.com.tenorio.reactiveapi.integration

import br.com.tenorio.reactiveapi.ReactiveApiApplication
import br.com.tenorio.reactiveapi.controller.PersonController
import br.com.tenorio.reactiveapi.models.Person
import br.com.tenorio.reactiveapi.repository.PersonRepository
import br.com.tenorio.reactiveapi.service.KafkaConsumerService
import br.com.tenorio.reactiveapi.service.PersonService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.record.Record
import org.apache.kafka.common.serialization.StringDeserializer
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.Duration


@EnableKafka
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [ReactiveApiApplication::class, KafkaConsumerService::class, PersonService::class]
)
@EmbeddedKafka(
    partitions = 4,
    controlledShutdown = false,
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092"
    ], topics = ["person", "person-async"]
)
@TestPropertySource(properties = ["spring.kafka.bootstrap-servers=\${spring.embedded.kafka.brokers}"])
class KafkaIntegrationTest {


    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var personRepository: PersonRepository

    @Autowired
    var embeddedKafkaBroker: EmbeddedKafkaBroker? = null

    lateinit var consumer: KafkaConsumer<String, String>

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Any>


    @BeforeEach
    fun setup() {
        val bootstrapServers = embeddedKafkaBroker!!.brokersAsString
        println(bootstrapServers)
        System.setProperty("spring.kafka.bootstrap-servers", bootstrapServers)

        val consumerProps = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafkaBroker)
        consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        consumerProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        consumer = KafkaConsumer(consumerProps, StringDeserializer(), StringDeserializer())
        consumer.subscribe(listOf("person"))
    }

    @Test
    fun testCreatePerson() {
        val person = Person(
            name = "vinicius",
            age = 30,
            id = 1
        )

        webTestClient.post()
            .uri("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(person)
            .exchange()


        `when`(personRepository.save(any(Person::class.java))).thenReturn(Mono.just(person))

        webTestClient.post()
            .uri("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(person)
            .exchange()

        val records: ConsumerRecords<String, String>? = consumer.poll(Duration.ofSeconds(10))
        val consumedMessages = mutableListOf<String>()
        records!!.iterator().forEachRemaining { consumedMessages.add(it.value()) }

        assert(consumedMessages.isNotEmpty())

        val message = consumedMessages.get(0)
        println("kkkk cachorro $message")
    }
    
}