package com.example.esportsmobile.view.friend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.R
import com.example.esportsmobile.dao.UsersDataSource
import com.example.esportsmobile.databinding.FragmentFriendsListBinding
import com.example.esportsmobile.model.User
import com.example.esportsmobile.adapters.FriendItemAdapter


class FriendsListFragment : Fragment(R.layout.fragment_friends_list) {

    private lateinit var binding : FragmentFriendsListBinding

    private lateinit var tittle : String

    private lateinit var friendItemAdapter: FriendItemAdapter
    private lateinit var friendsListView : RecyclerView
    private var friendsList : MutableList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFriendsList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFriendsListBinding.bind(view)

        initRecyclerView()
        setFriendItemListener()
    }


    private fun setFriendItemListener(){
        friendItemAdapter = FriendItemAdapter(friendsList){
            val friendFragment = FriendFragment()
            val bundle = bundleOf(
                "userID" to it.id
            )
            friendFragment.arguments = bundle
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, friendFragment).commit()
            }
        }

        friendsListView.adapter = friendItemAdapter
    }

    private fun initRecyclerView(){

        friendsListView = binding.recycleView
        friendsListView.setHasFixedSize(true)
        friendsListView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initFriendsList(){
        friendsList = UsersDataSource.createUsersList()
    }
}