package com.example.esportsmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import com.example.esportsmobile.dao.UsersDataSource
import com.example.esportsmobile.databinding.FragmentSingUpBinding
import com.example.esportsmobile.model.User
import com.google.android.material.textfield.TextInputEditText

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

    private lateinit var usersList: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersList = UsersDataSource.createUsersList()
    }

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
                Toast.makeText(requireContext(),newUser().toString(), Toast.LENGTH_SHORT).show()
                usersList.add(newUser())
            }
        }
    }

    private fun nullVerifier(): Boolean{
        if (name.text.isNullOrEmpty()){
            toastMessage("User name ")
            return false
        }
        if (age.text.isNullOrEmpty()){
            toastMessage("Age ")
            return false
        }
        if (country.text.isNullOrEmpty()){
            toastMessage("Country ")
            return false
        }
        if (email.text.isNullOrEmpty()){
            toastMessage("Email ")
            return false
        }
        if (password.text.isNullOrEmpty()){
            toastMessage("Password ")
            return false
        }

        return true
    }

    private fun newUser(): User {
        return User(generateID(),
            name.text.toString(),
            age.text.toString().toInt(),
            country.text.toString(),
            email.text.toString(),
            password.text.toString(),
            null
        )
    }

    private fun toastMessage(input : String){
        Toast.makeText(requireContext(), "$input is empty" , Toast.LENGTH_SHORT).show()
    }

    private fun generateID(): String{
        return (usersList.size + 1).toString()
    }

}