package com.example.esportsmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.esportsmobile.databinding.ActivityLeagueBinding

class LeagueActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLeagueBinding
    private lateinit var bundle : Bundle

    private lateinit var leagueName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeagueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bundle = intent.extras!!
        leagueName = bundle.getString("league_name").toString()
    }
}