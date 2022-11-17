package com.example.esportsmobile

import android.os.Bundle
import com.example.esportsmobile.dao.TeamDataSource
import com.example.esportsmobile.databinding.ActivityTeamBinding
import com.example.esportsmobile.model.Team
import com.example.esportsmobile.model.User

class TeamActivity : DrawerBaseActivity() {

    private lateinit var binding : ActivityTeamBinding

    private lateinit var user : User
    private lateinit var team : Team
    private lateinit var teamName : String

    private lateinit var teamList: MutableList<Team>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teamName = intent.getStringExtra("name") as String
        user = intent.getSerializableExtra("user") as User

        implementsNavDrawer(user, teamName)

        getTeamData()
        startPage()
    }

    private fun startPage(){
        binding.apply {
            teamLogo.setImageResource(team.logo)
            teamName.text = team.name
            region.text = team.region
            winRate.text = team.winRate
            averageGameKills.text = team.averageKillsGame
            averageGameDeath.text = team.averageDeathsGame
            averageGameTime.text = team.averageGameTime
        }
    }

    private fun getTeamData(){
        teamList = TeamDataSource.startTeamsDataSource()
        team = teamList.find { it.name == teamName } as Team
    }

    private fun implementsNavDrawer(user: User, tittle: String){
        allocateActivityTittle(tittle)
        updateNavUser(user)
    }
}