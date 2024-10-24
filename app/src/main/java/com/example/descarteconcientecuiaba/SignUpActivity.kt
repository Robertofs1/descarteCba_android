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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(binding.root)
        initializeToolBar()
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