package br.com.tenorio.reactiveapi.mapper

import br.com.tenorio.reactiveapi.models.Crododile
import com.google.gson.Gson

class CrocodileMapper {
    fun convertToCrododile(json: String): Crododile {
        // Criar uma instância do Gson
        val gson = Gson()

        // Converter o JSON em uma instância da classe Crododile
        return gson.fromJson(json, Crododile::class.java)
    }
}