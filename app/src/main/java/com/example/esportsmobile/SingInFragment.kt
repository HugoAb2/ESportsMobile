package com.example.esportsmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import com.example.esportsmobile.dao.UsersDataSource
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

    private val usersList : MutableList<User> = UsersDataSource.createUsersList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSingInBinding.bind(view)

        email = binding.emailTxt
        password = binding.passwordTxt
        connection = binding.connection
        singinButton = binding.singinButton
        googleButton = binding.googleButton

    }

    override fun onResume() {
        super.onResume()
        singinButton.setOnClickListener{
            val intent = Intent(requireContext(),HomeActivity::class.java)
            val user = findUser()
            if (user != null){
                intent.putExtra("user", user)
                requireActivity().startActivity(intent)
            }
        }
    }

    private fun findUser() : User?{
        val user = usersList.find { email.text.toString() == it.email }
        return if (user == null ) {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
            null
        } else {
            return if (password.text.toString() != user.password){
                    Toast.makeText(requireContext(),"Wrong password", Toast.LENGTH_SHORT).show()
                    null
                }
                else {
                    user
                }
        }
    }
}
