package com.example.descarteconcientecuiaba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.descarteconcientecuiaba.databinding.ActivityLoginBinding
import com.example.descarteconcientecuiaba.utils.showMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {


    lateinit var buttonAbrir: Button
    lateinit var inputLabel: EditText
    private lateinit var email: String
    private lateinit var password: String

    private val firebaseAuth by lazy{
        FirebaseAuth.getInstance()
    }

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        initializeClickEvent()
        //firebaseAuth.signOut()

    }

    override fun onStart() {
        super.onStart()
        verifyUserOnApp()
    }

    private fun verifyUserOnApp() {
        val currentUser = firebaseAuth.currentUser
        if(currentUser != null){
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }


    private fun initializeClickEvent() {
        binding.textSignUp.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }
        binding.btnSignUp.setOnClickListener {
            if(validateFields()){
                loginUser()
            }
        }
    }

    private fun loginUser() {
        firebaseAuth.signInWithEmailAndPassword(
            email,
            password
        ).addOnSuccessListener{
            showMessage("Login realizado com sucesso!")
            startActivity(
                Intent(applicationContext, MainActivity::class.java)
            )
        }.addOnFailureListener{ error ->
            try {
                throw error
            }
            catch (errorCredential: FirebaseAuthInvalidUserException){
                showMessage("Email inválido! Digite um email válido!")
            }
            catch (errorCredential: FirebaseAuthInvalidCredentialsException){
                showMessage("Email ou senha inválidos! Digite novamente.")
            }
        }
    }

    private fun validateFields(): Boolean {
        email = binding.editLoginEmail.text.toString()
        password = binding.editLoginPassword.text.toString()

        if(email.isNotEmpty()){
            binding.textInputLayoutLoginEmail.error = null

            if(password.isNotEmpty()){
                binding.textInputLayoutLoginPassword.error = null
                return true
            }else {
                binding.textInputLayoutLoginPassword.error = "Preencha a sua senha!"
                return false
            }
        }
        else {
            binding.textInputLayoutLoginEmail.error = "Preencha o seu email!"
            return false
        }

    }

}