package com.example.descarteconcientecuiaba

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.descarteconcientecuiaba.databinding.ActivitySignupBinding
import com.example.descarteconcientecuiaba.model.User
import com.example.descarteconcientecuiaba.utils.showMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var passwordAgain: String


    private val firebaseAuth by lazy{
        FirebaseAuth.getInstance()
    }

    private val firestore by lazy{
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        initializeToolBar()
        initializeClickEvent()
    }

    private fun initializeClickEvent() {
        binding.btnSignUp.setOnClickListener() {
            if(validatefields()){
                signUpUser(
                    name,
                    email,
                )

            }
        }
    }

    private fun signUpUser(
        name: String,
        email: String,
    )
    {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{res ->
            if(res.isSuccessful) {

                val idUser = res.result.user?.uid
                if(idUser != null){
                    val user = User(
                        idUser,
                        name,
                        email,
                    )
                    saveUserFirestore(user)
                }
            }
        }.addOnFailureListener{ error ->
            try {
                throw error
            }
            catch (errorWeakPassword: FirebaseAuthWeakPasswordException){
                showMessage("Senha muito fraca! Escolha uma com letras, número e caracteres especiais!")
            }
            catch (errorUserExists: FirebaseAuthUserCollisionException){
                showMessage("Email já pertence outro úsuario cadastrado!")
            }
            catch (errorCredential: FirebaseAuthInvalidCredentialsException){
                showMessage("Email inválido! Digite um email válido!")
            }

        }
    }

    private fun saveUserFirestore(user: User) {
        firestore
            .collection("users")
            .document(user.id)
            .set(user)
            .addOnSuccessListener {
                showMessage("Cadastro realizado com sucesso!")
                startActivity(
                    Intent(applicationContext, MainActivity::class.java)
                )
            }.addOnFailureListener{
                showMessage("Erro ao fazer seu cadastro!")
            }
    }


    private fun validatefields(): Boolean {
        name = binding.textEditName.text.toString()
        email = binding.textEditEmail.text.toString()
        password = binding.textEditPassword.text.toString()
        passwordAgain = binding.textEditPasswordAgain.text.toString()


        if(name.isNotEmpty()){
            binding.textInputName.error = null

            if(email.isNotEmpty()){
                binding.textInputEmail.error = null

                if(password.isNotEmpty()){
                    binding.textInputPassword.error = null

                    if(passwordAgain == password){
                        binding.textInputPasswordAgain.error = null
                        return true
                    }
                    else {
                        binding.textInputPasswordAgain.error = "As senhas não são iguais!"
                        return false
                    }
                }else {
                    binding.textInputPassword.error = "Preencha a sua senha!"
                    return false
                }

            }
            else {
                binding.textInputName.error = "Preencha o seu email!"
                return false
            }
        }
        else {
            binding.textInputName.error = "Preencha o seu nome!"
            return false
        }
    }

    private fun initializeToolBar() {
        val toolbar = binding.includeToolbar.tbPrincipal
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Faça o seu cadastro"
            setDisplayHomeAsUpEnabled(true)
        }

    }
}