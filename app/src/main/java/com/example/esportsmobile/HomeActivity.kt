package com.example.esportsmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var topBar : FragmentContainerView

    private lateinit var leagueListView : RecyclerView
    private lateinit var leagueIconAdapter : LeagueIconAdapter



    private var leagueLeagueIconList : MutableList<LeagueIcon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        leagueListView = binding.recycleView
        leagueListView.setHasFixedSize(true)
        leagueListView.layoutManager = GridLayoutManager(this, 2)

        leagueLeagueIconList = ArrayList()
        addDataToList()

        leagueIconAdapter = LeagueIconAdapter(leagueLeagueIconList)
        leagueListView.adapter = leagueIconAdapter
    }

    private fun addDataToList(){
        leagueLeagueIconList.add(LeagueIcon(R.drawable.logo_cblol, "CBLOL"))
        leagueLeagueIconList.add(LeagueIcon(R.drawable.logo_lck, "LCK"))
        leagueLeagueIconList.add(LeagueIcon(R.drawable.logo_lcs, "LCS"))
        leagueLeagueIconList.add(LeagueIcon(R.drawable.logo_lec, "LEC"))
        leagueLeagueIconList.add(LeagueIcon(R.drawable.logo_lpl, "LPL"))
        leagueLeagueIconList.add(LeagueIcon(R.drawable.logo_worlds, "WORLDS"))
    }
}