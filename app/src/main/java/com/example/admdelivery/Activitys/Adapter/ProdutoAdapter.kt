package com.example.admdelivery.Activitys.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.admdelivery.Activitys.Activitys.Atualizar_produto
import com.example.admdelivery.Activitys.DataSource.DB
import com.example.admdelivery.Activitys.Model.Produto

import com.example.admdelivery.databinding.ProutosItemBinding

class ProdutoAdapter(private  val context: Context,private val listaPrdutos:MutableList<Produto>):
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {
    val db = DB()

    inner class ProdutoViewHolder(binding: ProutosItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val codigoProduto = binding.CodiGOProduto
        val fotoProduto = binding.imagProduto
        val nomeProduto = binding.NomeProduto
        val descricaoProduto = binding.descricaoProduto
        val precoProduto = binding.pRECOProduto
        val btAtualizar_produto = binding.btAtualizar
        val btDeletar = binding.btDeletar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = ProutosItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProdutoViewHolder(itemLista)
    }


    override fun getItemCount() = listaPrdutos.size



    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.codigoProduto.text = "Codigo:${listaPrdutos[position].codigo} "
        Glide.with(context).load(listaPrdutos[position].foto).into((holder.fotoProduto))
        holder.nomeProduto.text = listaPrdutos[position].nome
        holder.precoProduto.text = listaPrdutos[position].preco?.toString() ?: "Preço não disponível"
        holder.descricaoProduto.text = listaPrdutos[position].descricao
        holder.btAtualizar_produto.setOnClickListener{
            val intent = Intent(context, Atualizar_produto::class.java)
            intent.putExtra("codigo",listaPrdutos[position].codigo)
            intent.putExtra("foto",listaPrdutos[position].foto)
            intent.putExtra("preco",listaPrdutos[position].preco)
            intent.putExtra("nome",listaPrdutos[position].nome)
            intent.putExtra("descricao",listaPrdutos[position].descricao)

            context.startActivity(intent)

        }
        holder.btDeletar.setOnClickListener{
            val codigo:String? = listaPrdutos[position].codigo
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Excluir Produto")
            alertDialog.setMessage("Deseja excluir esse iten?")
            alertDialog.setPositiveButton("Sim") { _, _ ->
                if (codigo != null) {
                    db.deletarProduto(codigo)
                    listaPrdutos.removeAt(position)
                    notifyDataSetChanged()
                }


            }
            alertDialog.setNegativeButton("Nao",{_,_->

            } )
            alertDialog.show()


        }

    }



}