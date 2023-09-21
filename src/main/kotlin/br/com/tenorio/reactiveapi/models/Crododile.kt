package br.com.tenorio.reactiveapi.models

import com.google.gson.annotations.SerializedName
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Crododile(
        @Id
        val id: String?,
        val name: String,
        val sex: String,
        @SerializedName("date_of_birth")
        val dateOfBirth: String,
        val age: String
)