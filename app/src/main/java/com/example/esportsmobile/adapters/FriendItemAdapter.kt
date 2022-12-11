package com.example.esportsmobile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ItemFriendBinding
import com.example.esportsmobile.model.User

class FriendItemAdapter(private val friendItemList: MutableList<User>, private val onItemClicked: (User)->Unit) : RecyclerView.Adapter<FriendItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val friendItemView = ItemFriendBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return MyViewHolder(friendItemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val friend = friendItemList[position]
        holder.apply {
            friend.profile?.let { profile.setImageResource(it) }
            name.text = friend.name
            email.text = friend.email
            layout.setOnClickListener{
                onItemClicked(friend)
            }
        }
    }

    override fun getItemCount(): Int {
        return friendItemList.size
    }

    class MyViewHolder (binding : ItemFriendBinding): RecyclerView.ViewHolder(binding.root){
        val profile = binding.friendProfile
        val name = binding.friendName
        val email = binding.friendEmail
        val layout = binding.layout

    }

}