package com.example.esportsmobile

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ActivityHomeBinding
import com.example.esportsmobile.model.LeagueIcon
import com.example.esportsmobile.model.User
import com.example.esportsmobile.view.LeagueIconAdapter
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var toogle : ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var welcomeText : TextView
    private lateinit var leagueListView : RecyclerView
    private lateinit var leagueIconAdapter : LeagueIconAdapter

    private lateinit var user : User

    private var leagueIconList : MutableList<LeagueIcon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as User

        setWelcomeMessage()

        initRecyclerView()

        drawerLayout = binding.drawerLayout
        navView = binding.navView
        toogle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayShowHomeEnabled(true)
/*
        navView.setNavigationItemSelectedListener {
            https://www.youtube.com/watch?v=zQh-QGGKPw0&ab_channel=Foxandroid tempo 14:50
        }*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
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

    private fun setWelcomeMessage(){
        welcomeText = binding.emphasisMessage
        val welcomeMessage = "Welcome my Lord ${user.name}!"
        welcomeText.text = welcomeMessage
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



    private fun addDataToList(){
        leagueIconList.add(LeagueIcon(R.drawable.logo_cblol, "CBLOL"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lck, "LCK"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lcs, "LCS"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lec, "LEC"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lpl, "LPL"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_worlds, "WORLDS"))
    }
}