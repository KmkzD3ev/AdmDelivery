package com.example.admdelivery.Activitys.Activitys

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.admdelivery.Activitys.fragments.PedidosFragment
import com.example.admdelivery.Activitys.fragments.ProdutosFragment
import com.example.admdelivery.R
import com.example.admdelivery.databinding.ActivityHomeAdmBinding
import com.google.firebase.auth.FirebaseAuth

class HomeAdm : AppCompatActivity() {
    lateinit var binding: ActivityHomeAdmBinding
    private var clicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentRender(R.id.containerFragmentsProdutos,ProdutosFragment())




        binding.CadastropRODUTOS.setOnClickListener{
            val intent = Intent(this, CadastroProdutos::class.java)
            startActivity(intent)
        }


        binding.bgProdutos.setOnClickListener{
            clicked = true
            if (clicked){
                 binding.bgProdutos.setBackgroundResource(R.drawable.bg_button_enable)
                binding.bgProdutos.setTextColor(Color.WHITE)
                binding.bgPedidos.setBackgroundResource(R.drawable.bg_button_disable)
                binding.bgPedidos.setTextColor(Color.BLACK)

                binding.containerFragmentsProdutos.visibility = View.VISIBLE
                binding.containerFragmentsPedidos.visibility = View.INVISIBLE
                fragmentRender(R.id.containerFragmentsProdutos, ProdutosFragment()) // Aqui está o problema


            }
        }
        binding.bgPedidos.setOnClickListener {
            clicked = true
            if (clicked){
                binding.bgPedidos.setBackgroundResource(R.drawable.bg_button_enable)
                binding.bgPedidos.setTextColor(Color.WHITE)
                binding.bgProdutos.setBackgroundResource(R.drawable.bg_button_disable)
                binding.bgProdutos.setTextColor(Color.BLACK)

                binding.containerFragmentsProdutos.visibility = View.INVISIBLE
                binding.containerFragmentsPedidos.visibility = View.VISIBLE
                fragmentRender(R.id.containerFragmentsPedidos,PedidosFragment())


            }
        }
        binding.txtsair.setOnClickListener {view->
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this , " Saindo da Sessao ", Toast.LENGTH_SHORT).show()



        }





    }
    private fun fragmentRender(containerId: Int, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Encontrar o fragmento atual, se existir
        val currentFragment = fragmentManager.findFragmentById(containerId)
        if (currentFragment != null) {
            fragmentTransaction.remove(currentFragment)
        }
        // Adicionar o novo fragmento
        fragmentTransaction.add(containerId, fragment)
        fragmentTransaction.commit()
    }








}