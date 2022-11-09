package com.example.esportsmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import com.example.esportsmobile.databinding.FragmentSingInBinding
import com.google.android.material.textfield.TextInputEditText

class SingInFragment : Fragment(R.layout.fragment_sing_in) {

    private lateinit var binding : FragmentSingInBinding

    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var connection : CheckBox
    private lateinit var singinButton : Button
    private lateinit var googleButton : ImageButton

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
            startActivity(HomeActivity::class.java)
        }
    }

    private fun startActivity(clazz: Class<*>, name: String ="", args: Bundle = Bundle()){
        val intent = Intent(requireContext(),clazz).apply {
            if(!name.isNullOrEmpty() && args.isEmpty){
                putExtra(name, args)
            }
        }
        requireActivity().startActivity(intent)
    }
}