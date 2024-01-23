package com.example.admdelivery.Activitys.Activitys

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.admdelivery.Activitys.DataSource.DB
import com.example.admdelivery.databinding.ActivityAtualizarProdutoBinding

class Atualizar_produto : AppCompatActivity() {
    private var imgProduto : Uri? = null
    private var db = DB()

    private lateinit var binding:ActivityAtualizarProdutoBinding



    private var selecionarFotoGaleria = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            imgProduto = uri
            binding.imgProduto.setImageURI(imgProduto)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codigo = intent.extras?.getString("codigo")
         val nome = intent.extras?.getString("nome")
         val foto = intent.extras?.getString("foto")
       val preco = intent.extras?.getString("preco")
        val descricao = intent.extras?.getString("descricao")



        binding.selecFOTO.setOnClickListener{
            selecionarFotoGaleria.launch("image/*")

        }

        binding.editNomePRODUTO.setText(nome)
        binding.editprecoPRODUTO.setText(preco)
        binding.editcodgoPRODUTO.setText(codigo)
        binding.editDescricaoPRODUTO.setText(descricao)
        Glide.with(this).load(foto).into(binding.imgProduto)


        binding.btAtualizarProduto.setOnClickListener{
            val nome = binding.editNomePRODUTO.text.toString()
            val preco = binding.editDescricaoPRODUTO.text.toString()
            val descricao = binding.editprecoPRODUTO.text.toString()
            val codigo = binding.editcodgoPRODUTO.text.toString()
            val tag = binding.editTagProduto.text.toString()

            if (nome.isEmpty() || preco.isEmpty() || descricao.isEmpty() || codigo.isEmpty()){
                Toast.makeText(this, " Preencha todos os Campos ", Toast.LENGTH_SHORT).show()
            }else if (nome.isNotEmpty() && preco.isNotEmpty() && descricao.isNotEmpty() && codigo.isNotEmpty() && imgProduto !=null && tag.isNotEmpty()){
                db.atualizarProdutoComFoto(imgProduto!!,nome,descricao,preco ,codigo,tag,this,)

            }
            else{
                db.atualizarProdutoSemFoto(nome,descricao,preco ,codigo,tag,this)

            }



        }









    }
}