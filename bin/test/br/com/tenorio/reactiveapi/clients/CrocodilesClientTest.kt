package br.com.tenorio.reactiveapi.clients

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CrocodilesClientTest{

    val client = CrocodilesClient()

    @Test
    fun getCrododileById(){
        val response = client.getCrocodileById(1)
        println(response.block())
        assertNotNull(response.block())
    }
}