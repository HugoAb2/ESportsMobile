package com.example.esportsmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import com.example.esportsmobile.databinding.FragmentSingInBinding
import com.example.esportsmobile.model.User
import com.google.android.material.textfield.TextInputEditText

class SingInFragment : Fragment(R.layout.fragment_sing_in) {

    private lateinit var binding : FragmentSingInBinding

    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var connection : CheckBox
    private lateinit var singinButton : Button
    private lateinit var googleButton : ImageButton

    private val userList : MutableList<User> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingInBinding.bind(view)

        email = binding.emailTxt
        password = binding.passwordTxt
        connection = binding.connection
        singinButton = binding.singinButton
        googleButton = binding.googleButton

        userList.add(User("","admin", 1000, "","admin","123", (R.drawable.profile).hashCode()))
    }

    override fun onResume() {
        super.onResume()
        singinButton.setOnClickListener{
            val intent = Intent(requireContext(),HomeActivity::class.java)
            intent.putExtra("user", findUser())
            requireActivity().startActivity(intent)
        }
    }

    private fun findUser() : User{
        return when (val user = userList.find { email.text.toString() == it.email }) {
            null -> {
                //adicionar condição para quando o email do usuario informado nao existir
                User("","",0,"","","",null)
            }
            else -> {
                user
            }
        }
    }
}