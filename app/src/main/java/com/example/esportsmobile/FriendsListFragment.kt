package com.example.esportsmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.dao.UsersDataSource
import com.example.esportsmobile.databinding.FragmentFriendsListBinding
import com.example.esportsmobile.model.User
import com.example.esportsmobile.view.FriendItemAdapter


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
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
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