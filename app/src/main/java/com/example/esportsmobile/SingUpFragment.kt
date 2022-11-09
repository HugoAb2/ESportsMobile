package com.example.esportsmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.viewbinding.ViewBinding
import com.example.esportsmobile.databinding.FragmentSingInBinding
import com.example.esportsmobile.databinding.FragmentSingUpBinding
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

        }
    }


}