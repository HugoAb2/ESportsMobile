package com.example.esportsmobile.view.league

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.R
import com.example.esportsmobile.view.TeamActivity
import com.example.esportsmobile.dao.LeagueTeamIconsDataSource
import com.example.esportsmobile.databinding.FragmentLeagueTeamsBinding
import com.example.esportsmobile.model.TeamIcon
import com.example.esportsmobile.adapters.TeamItemAdapter

class LeagueTeamsFragment : Fragment(R.layout.fragment_league_teams) {

    private lateinit var binding : FragmentLeagueTeamsBinding

    private lateinit var league : String

    private lateinit var teamItemAdapter: TeamItemAdapter
    private lateinit var teamListView : RecyclerView
    private var teamList : MutableList<TeamIcon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTeamList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLeagueTeamsBinding.bind(view)

        initRecyclerView()
        setItemTeamListener()
    }

    private fun setItemTeamListener(){
        teamItemAdapter = TeamItemAdapter(teamList){
            val intent = Intent(requireActivity(), TeamActivity::class.java)
            intent.putExtra("name", it.name)
            startActivity(intent)
        }

        teamListView.adapter = teamItemAdapter
    }

    private fun initRecyclerView(){

        teamListView = binding.teamsRecyclerView

        teamListView.setHasFixedSize(true)
        teamListView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initTeamList(){
        teamList = LeagueTeamIconsDataSource.createCBLOLDataSet()
    }

    fun receiveData(leagueName : String){
        league = leagueName
    }
}