package com.example.esportsmobile.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentSingUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore

class SingUpFragment : Fragment(R.layout.fragment_sing_up) {

    private lateinit var binding: FragmentSingUpBinding
    
    private lateinit var name : TextInputEditText
    private lateinit var age : TextInputEditText
    private lateinit var country : TextInputEditText
    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var thermsConditions: CheckBox
    private lateinit var singUpButton : Button
    private lateinit var googleAccount : ImageButton

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingUpBinding.bind(view)

        name = binding.nameTxt
        age = binding.age
        country = binding.countryTxt
        email = binding.emailTxt
        password = binding.passwordTxt
        thermsConditions = binding.thermsConditions
        singUpButton = binding.singupButton
        googleAccount = binding.googleButton

    }

    override fun onResume() {
        super.onResume()
        singUpButton.setOnClickListener{
            if (nullVerifier()){
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener {response ->
                        if (response.isSuccessful){
                            addUserData()
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            clearCamps()
                        }
                }.addOnFailureListener {exception ->
                    showErrorMessage(exception)
                }
            }
        }
    }

    private fun addUserData(){
        val userMap = hashMapOf(
            "name" to name.text.toString(),
            "age" to age.text.toString(),
            "country" to country.text.toString(),
            "email" to email.text.toString(),
            "password" to password.text.toString(),
            "comments" to ArrayList<String>(),
            "friends" to ArrayList<String>(),
            "profile" to null
        )
        db.collection("Users").document(auth.currentUser!!.uid).set(userMap)
    }

    private fun showErrorMessage(exception : Exception){
        val errorMessage = when(exception){
            is FirebaseAuthWeakPasswordException -> "Type a password with 6 or more digits"
            is FirebaseAuthInvalidCredentialsException -> "Type a valid e-mail"
            is FirebaseAuthUserCollisionException -> "This user was already registered"
            is FirebaseNetworkException -> "No Internet Connection"
            else -> "Error"
        }
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun nullVerifier(): Boolean{
        if (name.text.isNullOrEmpty()){
            toastMessage("User name")
            return false
        }
        if (age.text.isNullOrEmpty()){
            toastMessage("Age")
            return false
        }
        if (country.text.isNullOrEmpty()){
            toastMessage("Country")
            return false
        }
        if (email.text.isNullOrEmpty()){
            toastMessage("Email")
            return false
        }
        if (password.text.isNullOrEmpty()){
            toastMessage("Password")
            return false
        }
        if (!thermsConditions.isChecked){
            toastMessage("Therms and Conditions")
            return false
        }

        return true
    }

    private fun clearCamps(){
        name.setText("")
        age.setText("")
        country.setText("")
        email.setText("")
        password.setText("")
    }

    private fun toastMessage(input : String){
        Toast.makeText(requireContext(), "$input is empty" , Toast.LENGTH_SHORT).show()
    }
}