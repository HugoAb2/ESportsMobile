package com.example.esportsmobile

import android.os.Bundle
import com.example.esportsmobile.databinding.ActivityLeagueBinding
import com.example.esportsmobile.model.User

class LeagueActivity : DrawerBaseActivity() {

    companion object{
        private const val TAG = "understanding"
    }
    
    private lateinit var binding : ActivityLeagueBinding

    private lateinit var user: User
    private lateinit var tittle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeagueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as User
        tittle = intent.getStringExtra("league_name") as String

        allocateActivityTittle(tittle)
        updateNavUser(user)

    }


}