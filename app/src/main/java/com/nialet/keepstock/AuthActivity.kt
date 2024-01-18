package com.nialet.keepstock
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Setup
        setup()
    }

    private fun setup() {
        title = "Autenticación"
        val signUpButton:Button = findViewById(R.id.signUpButton)
        val logInButton:Button = findViewById(R.id.logginButton)

        signUpButton.setOnClickListener {
            // Comprueba que los campos Email y Contraseña no esten vacios
            if (findViewById<EditText>(R.id.emailEditText).text.isNotEmpty() && findViewById<EditText>(R.id.passwordEditText).text.isNotEmpty()){

                // Crea un usuario con los campos emailEditText y passwordEditText en FireBase
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(findViewById<EditText>(R.id.emailEditText).text.toString(),
                        findViewById<EditText>(R.id.passwordEditText).text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                        }else {
                            showAlert()
                            Log.e("AuthActivity", "Error de autenticación: ${it.exception}")
                        }
                    }

            }
        }

        logInButton.setOnClickListener {
            // Comprueba que los campos Email y Contraseña no esten vacios
            if (findViewById<EditText>(R.id.emailEditText).text.isNotEmpty() && findViewById<EditText>(R.id.passwordEditText).text.isNotEmpty()){

                // Crea un usuario con los campos emailEditText y passwordEditText en FireBase
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(findViewById<EditText>(R.id.emailEditText).text.toString(),
                        findViewById<EditText>(R.id.passwordEditText).text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                        }else {
                            showAlert()
                        }
                    }
            }
        }

    }
    // Muestra una alerta en caso de no poder autenticar usuario
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    // Navega a la pantalla principal del Log
    private fun showHome(email:String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}