package com.example.esportsmobile

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ActivityHomeBinding
import com.example.esportsmobile.model.LeagueIcon
import com.example.esportsmobile.model.User
import com.example.esportsmobile.view.LeagueIconAdapter

class HomeActivity : DrawerBaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var leagueListView : RecyclerView
    private lateinit var leagueIconAdapter : LeagueIconAdapter

    private lateinit var user : User

    private var leagueIconList : MutableList<LeagueIcon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as User

        implementsNavDrawer(user)

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
                intent.putExtra("user", user)
                startActivity(intent)
            }
        })
    }

    private fun implementsNavDrawer(user: User){
        allocateActivityTittle("Home")
        updateNavUser(user)
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