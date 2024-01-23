package com.example.admdelivery.Activitys.DataSource


import Pedido
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.admdelivery.Activitys.Activitys.Atualizar_Entrega
import com.example.admdelivery.Activitys.Activitys.HomeAdm
import com.example.admdelivery.Activitys.Adapter.PedidoAdapter
import com.example.admdelivery.Activitys.Adapter.ProdutoAdapter

import com.example.admdelivery.Activitys.Model.Produto
import com.example.admdelivery.R

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage


class DB {
    val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()


    fun cadastroProdutos(
        foto: Uri,
        nome: String,
        descricao: String,
        preco: String,
        codigo: String,
        tag: String,
        context: Context,
        editNomePRODUTO: EditText,
        editprecoPRODUTO: EditText,
        editDescricaoPRODUTO: EditText,
        editcodgoPRODUTO: EditText

    ) {
        val storageReferencia = storage.getReference("/Produtos/$codigo")
        storageReferencia.putFile(foto).addOnSuccessListener {
            storageReferencia.downloadUrl.addOnSuccessListener { uri ->


                var produtoMap = hashMapOf(
                    "nome" to nome,
                    "descricao" to descricao,
                    "preco" to preco,
                    "foto" to uri.toString(),
                    "codigo" to codigo,
                    "tag" to tag
                )

                db.collection("Produtos").document(codigo).set(produtoMap)
                    .addOnCompleteListener {
                        Toast.makeText(context, "Sucesso ao Cadastrar Produto", Toast.LENGTH_SHORT)
                            .show()
                        editNomePRODUTO.setText(" ")
                        editprecoPRODUTO.setText("")
                        editDescricaoPRODUTO.setText("")
                        editcodgoPRODUTO.setText("")

                    }.addOnFailureListener {
                        Toast.makeText(context, "Falha ao Cadastrar Produto", Toast.LENGTH_SHORT)
                            .show()
                        // Lógica de falha
                    }
            }
        }
    }


    fun getProdutos(listaPrdoutos: MutableList<Produto>, produtoAdapter: ProdutoAdapter) {
        db.collection("Produtos").orderBy("codigo", Query.Direction.ASCENDING)
            .get().addOnCompleteListener { document ->
                if (document.isSuccessful) {
                    for (doc in document.result) {
                        val produto = doc.toObject(Produto::class.java)
                        listaPrdoutos.add(produto)
                        produtoAdapter.notifyDataSetChanged()


                    }


                }


            }
    }

    fun atualizarProdutoComFoto(
        foto: Uri,
        nome: String,
        preco: String,
        descricao: String,
        codigo: String,
        tag: String,
        context: Context,

        ) {


        val storageReferencia = storage.getReference("/Produtos/$codigo")
        storageReferencia.putFile(foto).addOnSuccessListener {
            storageReferencia.downloadUrl.addOnSuccessListener { uri ->


                db.collection("Produtos").document(codigo).update(
                    "codigo", codigo,
                    "foto", uri.toString(),
                    "nome", nome,
                    "preco", preco,
                    "descricao", descricao,
                    "tag", tag

                )
                    .addOnCompleteListener {
                        Toast.makeText(context, "Sucesso ao Cadastrar Produto", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(context, HomeAdm::class.java)
                        context.startActivity(intent)
                    }.addOnFailureListener {
                        Toast.makeText(context, "Falha ao Cadastrar Produto", Toast.LENGTH_SHORT)
                            .show()
                        // Lógica de falha
                    }
            }
        }


    }

    fun atualizarProdutoSemFoto(
        nome: String,
        preco: String,
        descricao: String,
        codigo: String,
        tag: String,
        context: Context,

        ) {


        db.collection("Produtos").document(codigo).update(
            "codigo", codigo,
            "nome", nome,
            "preco", preco,
            "descricao", descricao,
            "tag", tag

        )
            .addOnCompleteListener {
                Toast.makeText(context, "Sucesso ao Cadastrar Produto", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, HomeAdm::class.java)
                context.startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(context, "Falha ao Cadastrar Produto", Toast.LENGTH_SHORT).show()
                // Lógica de falha
            }

    }

    fun deletarProduto(codigo: String) {
        db.collection("Produtos").document(codigo).delete().addOnCompleteListener {

        }

    }

    fun getPedidos(listaPedidos: MutableList<Pedido>, pedidoAdapter: PedidoAdapter) {
        db.collection("Pedidos_Adm").get().addOnCompleteListener { documento ->
            if (documento.isSuccessful) {
                for (doc in documento.result) {
                    val pedido =
                        Pedido.fromSnapshot(doc) // Usando a função fromSnapshot para criar o Pedido
                    listaPedidos.add(pedido)
                    Log.d(
                        "Database",
                        "Pedido obtido banco de dados entrada da lista na aplicaçao: $pedido"
                    ) // Adiciona um log para verificar os pedidos recuperados
                    pedidoAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    fun attEntregaPedido(
        entregastatus: String,
        usuarioId: String,
        pedidoId: String,
        context: Context
    ) {
        // Referência à coleção "Usuario_Pedidos"
        val usuarioPedidosCollection = db.collection("Usuario_Pedidos")
        Log.d("Acessando o Bnco", "entrando na coleçao :$usuarioPedidosCollection ")

        // Acessa o documento do usuário usando o ID do usuário
        val usuarioDocument = usuarioPedidosCollection.document(usuarioId)
        Log.d("Obtendo o usuario", "Usuario do pedido: $usuarioId")

        // Acessa a subcoleção "Pedidos" dentro do documento do usuário
        val pedidosCollection = usuarioDocument.collection("Pedidos")
        Log.d("Entrando nos Pedidos", "Coleçao pedidos: $pedidosCollection")

        // Acessa o documento do pedido usando o ID do pedido
        val pedidoDocument = pedidosCollection.document(pedidoId)
        Log.d("Obtendo o PedidoID", "pedido DO  Usuario : $pedidoId")

        // Atualiza o campo "entrega_satatus" com o valor fornecido
        pedidoDocument.update("entregastatus", entregastatus)
            .addOnSuccessListener {
                Log.d("pedidos", "Status de entrega atualizado com sucesso para $entregastatus")
            }
            .addOnFailureListener { e ->
                Log.w("pedidos", "Erro ao atualizar status de entrega: $e")
            }


    }


        fun attEntregaPedidoAdm(statusEntrega: String, pedidoId: String, context: Context) {
            db.collection("Pedidos_Adm").document(pedidoId)
                .update("entregastatus", statusEntrega).addOnCompleteListener {
                    Toast.makeText(context, " Alteraçao Realizada com Sucesso ", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(context, HomeAdm::class.java)
                    context.startActivity(intent)

                }.addOnFailureListener {

                }

        }
    }





























