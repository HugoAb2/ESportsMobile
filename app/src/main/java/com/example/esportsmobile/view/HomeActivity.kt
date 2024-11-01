package com.example.esportsmobile.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.DrawerBaseActivity
import com.example.esportsmobile.view.league.LeagueActivity
import com.example.esportsmobile.R
import com.example.esportsmobile.databinding.ActivityHomeBinding
import com.example.esportsmobile.model.LeagueIcon
import com.example.esportsmobile.adapters.LeagueIconAdapter

class HomeActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var leagueListView : RecyclerView
    private lateinit var leagueIconAdapter : LeagueIconAdapter

    private var leagueIconList : MutableList<LeagueIcon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        implementsNavDrawer()

        initRecyclerView()

    }

    private fun initRecyclerView(){
        leagueListView = binding.recycleView
        leagueListView.setHasFixedSize(true)
        leagueListView.layoutManager = GridLayoutManager(this, 2)

        leagueIconList = ArrayList()
        addDataToList()

        leagueIconAdapter = LeagueIconAdapter(leagueIconList)
        leagueListView.adapter = leagueIconAdapter

    }

    override fun onResume() {
        super.onResume()

        leagueIconAdapter.setOnItemClickListener(object : LeagueIconAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@HomeActivity, LeagueActivity::class.java)
                val chosenLeague = leagueIconList[position]
                intent.putExtra("league_name", chosenLeague.name)
                startActivity(intent)
            }
        })
    }

    private fun implementsNavDrawer(){
        allocateActivityTittle("Home")
        initNavUser()
    }

    private fun addDataToList(){
        leagueIconList.add(LeagueIcon(R.drawable.league_logo_cblol, "CBLOL"))
        leagueIconList.add(LeagueIcon(R.drawable.league_logo_lck, "LCK"))
        leagueIconList.add(LeagueIcon(R.drawable.league_logo_lcs, "LCS"))
        leagueIconList.add(LeagueIcon(R.drawable.league_logo_lec, "LEC"))
        leagueIconList.add(LeagueIcon(R.drawable.league_logo_lpl, "LPL"))
        leagueIconList.add(LeagueIcon(R.drawable.league_logo_worlds, "WORLDS"))
    }
}