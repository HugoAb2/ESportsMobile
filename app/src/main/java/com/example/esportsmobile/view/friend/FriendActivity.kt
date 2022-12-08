package com.example.esportsmobile.view.friend

import android.os.Bundle
import android.util.Log
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

    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<FriendsListFragment>(R.id.fragment_container)
        }
    }

    private fun implementsNavDrawer(){
        allocateActivityTittle("Friends")
        updateNavUser()
    }
}