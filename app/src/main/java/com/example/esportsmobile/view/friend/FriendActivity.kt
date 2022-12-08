package com.example.esportsmobile.view.friend

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.esportsmobile.DrawerBaseActivity
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.ActivityFriendBinding

class FriendActivity : DrawerBaseActivity() {

    private lateinit var binding : ActivityFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        implementsNavDrawer()

        val operation = intent.getStringExtra("operation") as String
        fragmentManager(operation)

    }

    private fun fragmentManager(operation : String){
        when(operation){
            "list" -> {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<FriendsListFragment>(R.id.fragment_container)
                }
            }
            "friend" -> {
                val userID = intent.getStringExtra("userID") as String
                friendCheck(userID)
            }
        }
    }

    private fun friendCheck(userID : String){
        when(userID){
            "1" ->{
                val bundle = bundleOf(
                    "userID" to userID
                )
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<FriendFragment>(R.id.fragment_container, args = bundle)
                }
            }
            else -> {
                val bundle = bundleOf(
                    "userID" to userID,
                    "friend" to false
                )
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<FriendFragment>(R.id.fragment_container, args=bundle)
                }
            }
        }
    }


    private fun implementsNavDrawer(){
        allocateActivityTittle("Friends")
        updateNavUser()
    }
}