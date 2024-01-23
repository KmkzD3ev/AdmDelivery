package com.example.admdelivery.Activitys.Adapter


import Pedido
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admdelivery.Activitys.Activitys.Atualizar_Entrega

import com.example.admdelivery.Activitys.Model.Produto
import com.example.admdelivery.databinding.PedidoItemBinding

class PedidoAdapter(private val context: Context, private val  listaPedidos:MutableList<Pedido>):
    RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    inner class PedidoViewHolder(binding: PedidoItemBinding):RecyclerView.ViewHolder(binding.root){

        val textEndereco = binding.textEndereco
        val textCelular = binding.textCelular
        val textListaProduto = binding.textListaProduto
        val textStatus_pagamento = binding.textStatuspagamento
        val textEntrega_Status = binding.textEntregaStatus
        val txtnomUsuario = binding.txtnomeUsuario


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
       val item_lista = PedidoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return PedidoViewHolder(item_lista)
    }

    override fun getItemCount()=
       listaPedidos.size


    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
       holder.textEndereco.text = listaPedidos[position].endereco
        holder.textCelular.text = listaPedidos[position].celular
        val produtosText = buildProductsText(listaPedidos[position].listaprodutos)
        holder.textListaProduto.text = produtosText
        holder.textEntrega_Status.text = listaPedidos[position].entregastatus
        holder.textStatus_pagamento.text = listaPedidos[position].status_pagamentos
        val nomeUsuario = listaPedidos[position].nomeUsuario
        val clienteText = "Cliente: $nomeUsuario" // Concatenando "Cliente: " com o nome do usuário
        holder.txtnomUsuario.text = clienteText

        holder.textEntrega_Status.setOnClickListener{
            val intent = Intent(context,Atualizar_Entrega::class.java)
            intent.putExtra("pedidoiD",listaPedidos[position].pedidoId)
            intent.putExtra("usuarioId",listaPedidos[position].UsuarioId)

           // Log.d("PedidoAdapter PASSADNO AS INFORMAÇOES", "Pedido ID: ${listaPedidos[position].pedidoId}, Usuário ID: ${listaPedidos[position].UsuarioId}")
            context.startActivity(intent)
           // Log.d("Pedido", "Produto TELA PEDIDO SAIDA $intent")
        }


    }


    private fun buildProductsText(produtos: List<Produto>): String {
        val produtosTextList = produtos.map { produto ->
            val nome = produto.nome
            val preco = produto.preco
            "Nome: $nome - Preço: R$ $preco"
        }
        return produtosTextList.joinToString("\n")
        Log.d("Pedido", "Produto TELA PEDIDO SAIDA $produtosTextList")
    }


}