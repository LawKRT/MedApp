package com.example.med

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.med.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity

class SignUp: AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            val intent= Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
        binding.Signup.setOnClickListener {
            val email=binding.editTextTextEmailAddress.text.toString()
            val pass=binding.editTextTextPassword.text.toString()
            val confirmpass=binding.editTextTextconfirmpassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()){
                if (pass==confirmpass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent= Intent(this,SigninActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                }else{
                    Toast.makeText(this,"password is not matching", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"empty fiels are not allowed!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}