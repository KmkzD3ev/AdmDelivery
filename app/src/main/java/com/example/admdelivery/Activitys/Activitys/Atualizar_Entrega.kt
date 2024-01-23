package com.example.admdelivery.Activitys.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.admdelivery.Activitys.DataSource.DB
import com.example.admdelivery.databinding.ActivityAtualizarEntregaBinding

class Atualizar_Entrega : AppCompatActivity() {
    private lateinit var binding: ActivityAtualizarEntregaBinding
    private var status_Entrega = " "
    private var db = DB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarEntregaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pedidoId =intent.extras?.getString("pedidoiD").toString()
        val usuarioId =intent.extras?.getString("usuarioId").toString()

        Log.d("Atualizar_Entrega DADOS RECEBIDOS", "Pedido ID: $pedidoId, Usuário ID: $usuarioId")



        binding.btEmTrasnito.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                status_Entrega = " Status De Entrega : Em transito"
            }


        }
        binding.btEntrgue.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                status_Entrega = " Status De Entrega : Entregue ao Destinatario "
            }
        }

        binding.btAtualiazrEntrega.setOnClickListener {

                db.attEntregaPedido(
                    status_Entrega,
                    usuarioId,pedidoId,this
                )
                db.attEntregaPedidoAdm(
                   status_Entrega,pedidoId,this
                )






        }


    }
}




