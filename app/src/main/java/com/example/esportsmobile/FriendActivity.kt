package com.example.esportsmobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.esportsmobile.databinding.ActivityFriendBinding

class FriendActivity : DrawerBaseActivity() {

    private lateinit var binding : ActivityFriendBinding

    companion object{
        private const val TAG = "amigos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: BeforeImplements")
        implementsNavDrawer()
        Log.d(TAG, "onCreate: AfterImplements")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: BeforeFragment")
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