package com.example.esportsmobile.view.friend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.FragmentFriendBinding

class FriendFragment : Fragment(R.layout.fragment_friend) {

    private lateinit var binding : FragmentFriendBinding

    private lateinit var profile : ImageView
    private lateinit var friendID : TextView
    private lateinit var friendName : TextView
    private lateinit var friendEmail : TextView
    private lateinit var friendAge : TextView
    private lateinit var friendCountry : TextView
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFriendBinding.bind(view)

        profile = binding.profilePic
        friendID = binding.friendID
        friendName = binding.friendName
        friendAge = binding.friendAge
        friendEmail = binding.friendEmail
        friendCountry = binding.friendCountry
        button = binding.button

        val id = arguments

        friendID.text = id?.getString("userID") as String

        opCheck(id.getBoolean("friend", true))
    }

    private fun opCheck(friend : Boolean){
        when(friend){
            true -> {
                button.apply {
                    text = "Delete"
                    setOnClickListener {
                        //firebase delete
                    }
                }
            }
            false -> {
                button.apply {
                    text = "Add"
                    setOnClickListener {
                        //firebase Add
                    }
                }
            }
        }
    }


}