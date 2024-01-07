package br.com.tenorio.reactiveapi.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.kafka.core.KafkaTemplate
import reactor.core.publisher.Mono

class CrocodileServiceTest{

    @InjectMocks
    private lateinit var service: CrocodileService

    @Mock
    private lateinit var repository: CrocodileRepository

    @Mock
    private lateinit var kafkaTemplate: KafkaTemplate<String, kotlin.Any>

    @BeforeEach
    fun setUpMock() {
        MockitoAnnotations.openMocks(this) //carrega meus mocks para essa classe
    }

    fun createMockCrocodile(): Crododile {
        return Crododile("234234234234234","tenorio","male", dateOfBirth = "1995-02-27","30")
    }

    fun createUsedCrocodile(): Crododile {
        return Crododile("23423423423444434","jacke","female", dateOfBirth = "1995-02-27","30")
    }

    @Test
    fun create() {
        Mockito.`when`(repository.save(any(Crododile::class.java))).thenReturn(Mono.just(createMockCrocodile()))
        val returnedValue = service.save(createUsedCrocodile())
        verify(repository,times(1)).save(createUsedCrocodile())
    }

}