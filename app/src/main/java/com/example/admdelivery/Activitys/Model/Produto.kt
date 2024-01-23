package com.example.admdelivery.Activitys.Model

import java.io.Serializable

data class Produto(
    var codigo: String? = null,
    var tag: String? = null,
    var foto: String? = null,
    var nome: String? = null,
    var descricao: String? = null,
    var entrega_status: String?  = null


) : Serializable {

    var preco: String? = null


    constructor(
        codigo: String?,
        tag: String?,
        foto: String,
        nome: String?,
        preco: Double,
        descricao: String?,
        entrega_status: String?

        ) : this() {
        this.codigo = codigo
        this.tag = tag
        this.foto = foto
        this.nome = nome
        this.preco= preco?.toDouble().toString()
        this.descricao = descricao
        this.entrega_status = entrega_status

    }
}