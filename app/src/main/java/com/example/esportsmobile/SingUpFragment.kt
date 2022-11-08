package com.example.esportsmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.viewbinding.ViewBinding
import com.example.esportsmobile.databinding.FragmentSingInBinding
import com.example.esportsmobile.databinding.FragmentSingUpBinding

class SingUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sing_up, container, false)
    }

    override fun onResume() {
        super.onResume()
    }


}