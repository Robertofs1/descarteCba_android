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

class LoginActivity : AppCompatActivity() {


    lateinit var buttonAbrir: Button
    lateinit var inputLabel: EditText

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        initializeClickEvent()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.button_login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonAbrir = findViewById(R.id.button_login)
        buttonAbrir.setOnClickListener{

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            startActivity(intent)
        }
    }

    private fun initializeClickEvent() {
        binding.textSignUp.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }
}