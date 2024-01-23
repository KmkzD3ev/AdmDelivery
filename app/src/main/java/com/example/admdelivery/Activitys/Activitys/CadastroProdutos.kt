package com.example.admdelivery.Activitys.Activitys

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.admdelivery.Activitys.DataSource.DB
import com.example.admdelivery.databinding.ActivityCadastroProdutosBinding

class CadastroProdutos : AppCompatActivity() {
    private var imgProduto : Uri? = null
    private var db = DB()
    lateinit var binding: ActivityCadastroProdutosBinding

    private var selecionarFotoGaleria = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            imgProduto = uri
            binding.imgProduto.setImageURI(imgProduto)

        }
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.selecFOTO.setOnClickListener{
                selecionarFotoGaleria.launch("image/*")

            }
            binding.btcadastroProduto.setOnClickListener{
                val nome = binding.editNomePRODUTO.text.toString()
                val preco = binding.editDescricaoPRODUTO.text.toString()
                val descricao = binding.editprecoPRODUTO.text.toString()
                val codigo = binding.editcodgoPRODUTO.text.toString()
                val tag = binding.editTagProduto.text.toString()

                if (nome.isEmpty() || preco.isEmpty() || descricao.isEmpty() || codigo.isEmpty()){
                    Toast.makeText(this, " Preencha todos os Campos ", Toast.LENGTH_SHORT).show()
                }else{
                    db.cadastroProdutos(imgProduto!!,nome,preco,descricao,codigo,tag,this,binding.editNomePRODUTO,binding.editprecoPRODUTO,binding.editDescricaoPRODUTO,binding.editcodgoPRODUTO)

                }



            }











    }
}