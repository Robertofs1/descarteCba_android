package com.example.descarteconcientecuiaba

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.descarteconcientecuiaba.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var address: String
    private lateinit var cep: String
    private lateinit var stateCity: String
    private lateinit var gender: String
    private lateinit var cpfCnpj: String
    private lateinit var password: String
    private lateinit var passwordAgain: String

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

            }
        }
    }

    private fun validatefields(): Boolean {
        name = binding.textEditName.text.toString()
        email = binding.textEditEmail.text.toString()
        address = binding.textEditAdress.text.toString()
        cpfCnpj = binding.textEditCPFCNPJ.text.toString()
        gender = binding.textEditGender.text.toString()
        stateCity = binding.textEditStateCity.text.toString()
        password = binding.textEditPassword.text.toString()
        passwordAgain = binding.textEditPasswordAgain.text.toString()


        if(name.isNotEmpty()){
            binding.textInputName.error = null

            if(email.isNotEmpty()){
                binding.textInputEmail.error = null

                if(address.isNotEmpty()){
                    binding.textInputAddres.error = null

                    if(cpfCnpj.isNotEmpty()){
                        binding.textInputCPForCPNJ.error = null

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
                        binding.textInputAddres.error = "Preencha o seu CPF/CNPJ!"
                        return false
                    }
                }
                else {
                    binding.textInputAddres.error = "Preencha o seu endereço!"
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