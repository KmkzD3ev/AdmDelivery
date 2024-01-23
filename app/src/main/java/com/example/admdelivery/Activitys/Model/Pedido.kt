import android.util.Log
import com.example.admdelivery.Activitys.Model.Produto
import com.google.firebase.firestore.DocumentSnapshot



data class Pedido(

    val nomeUsuario: String? = null,
    val endereco: String? = null,
    val celular: String? = null,
    val listaprodutos: MutableList<Produto> = mutableListOf(),
    val status_pagamentos: String? = null,
    val pontoRefere: String? = null,
    val entregastatus: String? = null,
    val pedidoId: String? = null,
    val preco: String? = null, // Alterado para String
    val UsuarioId: String? = null

) {

    companion object {
        fun fromSnapshot(snapshot: DocumentSnapshot): Pedido {


            val nomUsuario  = snapshot.getString("nomeUsuario")
          //  Log.d("Pedido", "Endereço do Pedido: $nomUsuario")

            val endereco = snapshot.getString("endereco")
            //Log.d("Pedido", "Endereço do Pedido: $endereco")

            val celular = snapshot.getString("celular")
           // Log.d("Pedido", "telefone do Pedido: $celular")

            val status_pagamentos = snapshot.getString("status_pagamentos")
           // Log.d("Pedido", "Pgamento do Pedido: $status_pagamentos")
            val pontoRefere = snapshot.getString("pontoRefere")

            val entregastatus = snapshot.getString("entregastatus")

            val pedidoId = snapshot.getString("pedidoiD")
            //Log.d("Pedido", "Pedido ID TELA DE PEDIDOS: $pedidoId")

            val usuarioId = snapshot.getString("usuarioId")
            Log.d("Pedido", "USUARIO ID TELA DE PEDIDOS: $usuarioId")




            val produtosList = snapshot.get("listaprodutos") as List<Map<String, Any>>?
            val produtos = mutableListOf<Produto>()
           // Log.d("Pedido", "Produto TELA PEDIDO  entrada dos dados do produto no pedidos $produtosList")

            produtosList?.forEach { produtoMap ->
                val nome = produtoMap["nome"] as String? ?: ""
                val preco = produtoMap["preco"] as Double? ?: 0.0
                val status_pagamento = produtoMap["status_pagamentos"] as String? ?:""




                val produto = Produto("","","",nome,preco,entregastatus,status_pagamentos)
                produtos.add(produto)
              //  Log.d("Pedido", "Produto TELA PEDIDO montando a o pedido ${produto}")
            }

            return Pedido(
                nomUsuario,endereco, celular, produtos, status_pagamentos,
                pontoRefere, entregastatus, pedidoId, "",usuarioId,
            )
         Log.d("Pedido", "Produto TELA PEDIDO SAIDA ${Pedido}")
        }
    }
    override fun toString(): String {
        return "Pedido(endereco=$endereco, celular=$celular, listaprodutos=$listaprodutos, status_pagamentos=$status_pagamentos, pontoRefere=$pontoRefere, entrega_satatus=$entregastatus, pedidoId=$pedidoId, preco=$preco, UsuarioId=$UsuarioId)"
    }
}