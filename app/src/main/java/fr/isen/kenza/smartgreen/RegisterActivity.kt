package fr.isen.kenza.smartgreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import fr.isen.kenza.smartgreen.databinding.ActivityRegisterBinding

//activite pour s'enregistrer en utilisant firebase
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = fr.isen.kenza.smartgreen.databinding.ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnRegister.setOnClickListener{

            when{
                TextUtils.isEmpty(binding.RegisterLoginEmail.text.toString().trim { it <= ' '}) ->{
                Toast.makeText(this@RegisterActivity, "rentrez une adresse mail", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(binding.registerLoginPassword.text.toString().trim { it <= ' '}) ->{
                    Toast.makeText(this@RegisterActivity, "rentrez un mot de pase", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = binding.RegisterLoginEmail.text.toString().trim { it <= ' '}
                    val password: String = binding.registerLoginPassword.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(this@RegisterActivity, "creation du compte réussi", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@RegisterActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }


                }
            }
        }
    }
}