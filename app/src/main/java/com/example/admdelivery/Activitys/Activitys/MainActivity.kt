package com.example.admdelivery.Activitys.Activitys

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.admdelivery.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btEntrar.setOnClickListener{view->
            val email = binding.AdmEmail.text.toString()
            val senha = binding.AdmSenha.text.toString()
            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Preencha todos os Campos ", Toast.LENGTH_SHORT).show()

            }else if(email.equals("adm@gmail.com")){
                autenticador(email,senha)

            }else {
                // Se o email não for igual ao email de administração
                Toast.makeText(this, "Email inválido ,Insira um Usuario cadastrado", Toast.LENGTH_SHORT).show()
            }
        }

            }





    private fun autenticador(email :String,senha :String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {autenticacao->
        if (autenticacao.isSuccessful){
            val intent = Intent(this, HomeAdm::class.java)
            startActivity(intent)

        }

        }
            .addOnFailureListener {exception->
                Toast.makeText(this, "Erro ao logar Usuario ", Toast.LENGTH_SHORT).show()

            }

    }

    override fun onResume() {
        super.onResume()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if (usuarioAtual !=  null) {
            val intent = Intent(this, HomeAdm::class.java)
            startActivity(intent)
            finish()
        }

    }



    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "canal_de_notificacao"
            val channelName = "Nome do Canal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Descrição do Canal"
            }

            // Registrar o canal no NotificationManager
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }




}





