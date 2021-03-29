package com.diegolima.netflixclone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diegolima.netflixclone.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException


class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        TooBar()

        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem_erro = binding.mensagemErro
            if (email.isEmpty() || senha.isEmpty()) {
                mensagem_erro.setText("Preencha todos os campos")
            } else {
                CadastrarUsuario()

            }
        }
    }

    private fun CadastrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem_erro = binding.mensagemErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email,
            senha
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                mensagem_erro.setText("")
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
                mensagem_erro.setText("")
            }
        }.addOnFailureListener {
            var erro = it
            when {
                erro is FirebaseAuthWeakPasswordException -> mensagem_erro.setText("Digite uma senha com no mínimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> mensagem_erro.setText("Esta conta já foi cadastrada")
                erro is FirebaseNetworkException -> mensagem_erro.setText("Sem conexão com a internet")
                else -> mensagem_erro.setText("Erro ao cadastrar usuário!")
            }
        }
    }


    private fun TooBar() {
        val toobar = binding.toobarCadastro
        toobar.setBackgroundColor(getColor(R.color.white))
        toobar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
    }

}