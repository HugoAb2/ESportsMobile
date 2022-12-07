package com.example.esportsmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.esportsmobile.databinding.ActivityEditProfileBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var userID : String
    private lateinit var userDoc : DocumentReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userID = intent.getStringExtra("userID") as String
        userDoc = FirebaseFirestore.getInstance().collection("Users").document(userID)
        receiveUserData()

    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            updateProfile.setOnClickListener {
                updateUser()
                setResult(RESULT_OK)
                finish()
            }
            cancelButton.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
            backIcon.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }


    private fun updateUser(){
        userDoc.apply {
            update("name", binding.nameTxt.text.toString())
            update("age", binding.age.text.toString())
            update("country", binding.countryTxt.text.toString())
            update("email", binding.emailTxt.text.toString())
            //para redefinir o email implementar criação de novo usuario com o email e deletar o antigo
            update("password", binding.passwordTxt.text.toString())
            //para redefinir a senha implementar envio de email
        }
    }

    private fun receiveUserData() {
        userDoc.addSnapshotListener{user, _ ->
                binding.apply {
                    if (user != null) {
                        userId.text = userID
                        nameTxt.setText(user.getString("name"))
                        age.setText(user.getString("age"))
                        countryTxt.setText(user.getString("country"))
                        emailTxt.setText(user.getString("email"))
                        passwordTxt.setText(user.getString("password"))
                    }
            }
        }
    }

}