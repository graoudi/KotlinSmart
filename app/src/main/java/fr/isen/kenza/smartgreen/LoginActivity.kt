package fr.isen.kenza.smartgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import fr.isen.kenza.smartgreen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //redirection vers le bon layout

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)

        }
        //clique sur le bouton puis entre les donnes
        //utilisation base de donnee firebase
        binding.btnLogin.setOnClickListener{

            when{
                TextUtils.isEmpty(binding.etLoginEmail.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(this@LoginActivity, "rentrez une adresse mail", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(binding.etLoginPassword.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(this@LoginActivity, "rentrez un mot de pase", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = binding.etLoginEmail.text.toString().trim { it <= ' '}
                    val password: String = binding.etLoginPassword.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this@LoginActivity, "creation du compte réussi", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id",FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }


                }
            }

        }
    }
}