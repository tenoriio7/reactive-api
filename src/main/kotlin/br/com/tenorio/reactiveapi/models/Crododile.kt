package br.com.tenorio.reactiveapi.models

import org.springframework.data.annotation.Id


data class Crododile(
        @Id
        val id: String?,
        val name: String,
        val sex: String,
        val date_of_birth: String,
        val age: String
)